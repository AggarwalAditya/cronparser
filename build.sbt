import sbt._
import Keys._

val sparkVersion = "2.4.8"

name := "cas-data-pipelines-dags_" + sparkVersion.replace(".","-")
organization := "com.groupon"

version := "2.2.64"

ThisBuild / useCoursier := false

assembly / artifact := {
  val art = (assembly / artifact).value
  art.withClassifier(Some("assembly"))
}

addArtifact(artifact in(Compile, assembly), assembly)

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)

scalaVersion := "2.12.8"

mainClass := Some("KafkaToFileJobMain")

resolvers ++= Seq(
  Resolver.mavenLocal,
  "Typesafe repository releases" at "https://repo.typesafe.com/typesafe/releases/",
  "Sonatype Groupon Releases" at "http://nexus-app1-dev.snc1/content/repositories/releases/",
  "Groupon Internal" at "http://nexus-app1-dev.snc1/content/groups/public/",
  "Groupon Internal Snapshot" at "http://nexus-dev/content/groups/public-snapshots/"
)

libraryDependencies ++= Seq(
  //Common Dependencies
  "com.groupon.cde" %% "cde-jobframework" % "0.17.12",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.twitter" %% "util-core" % "6.40.0",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.6.7",

  //Spark Dependencies
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided",

  //Spark Streaming Dependencies
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.kafka" %% "kafka" % "0.10.1.1",

  //Janus
  "com.groupon.data-engineering" % "janus-thin-mapper" % "2.3",
  "com.groupon.dse" % "kafka-message-serde" % "2.2",
  "org.apache.spark" %% "spark-avro" % sparkVersion % "provided",

  // Postgres
  "org.postgresql" % "postgresql" % "42.2.6",

  "com.github.scopt" %% "scopt" % "3.5.0",
  "org.apache.kafka" % "kafka-clients" % "2.8.1",
  "com.groupon.dse" % "kafka-message-serde" % "2.2",
  "com.groupon.jtier.metrics" % "metrics-sma" % "4.4.0",
  "com.groupon.jtier.metrics" % "metrics-sma-influxdb" % "4.4.0"

)

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.6.7"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.6.7"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.7.1"
dependencyOverrides += "org.slf4j" % "slf4j-api" % "1.7.12"
dependencyOverrides += "org.slf4j" % "slf4j-log4j12" % "1.7.12"
dependencyOverrides += "org.apache.commons" % "commons-lang3" % "3.8.1"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case x => MergeStrategy.first
}

// see https://tpolecat.github.io/2017/04/25/scalac-flags.html for scalacOptions descriptions
scalacOptions ++= Seq(
  "-deprecation",     //emit warning and location for usages of deprecated APIs
  "-unchecked",       //enable additional warnings where generated code depends on assumptions
  "-explaintypes",    //explain type errors in more detail
  "-Ywarn-dead-code", //warn when dead code is identified
  "-Xfatal-warnings"  //fail the compilation if there are any warnings
)

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishTo := {
  val nexus = "http://nexus-dev.snc1/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "content/repositories/releases")
}

assemblyShadeRules in assembly ++= Seq(
  ShadeRule.rename("org.apache.commons.codec.binary.**" -> "keyspace_codec.@1").inAll,
  ShadeRule.rename("com.google.common.**" -> "repackaged.com.google.common.@1").inAll,
  ShadeRule.rename("com.google.protobuf.**" -> "repackaged.com.google.protobuf.@1").inAll
)

// A merge strategy helps speed up sbt assembly:
// If multiple files share the same path, pick the first
// If the file is metadata info, simply discard
assembly / assemblyMergeStrategy := {
  case path if path.contains("META-INF/services") => MergeStrategy.concat
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

test in assembly := {}