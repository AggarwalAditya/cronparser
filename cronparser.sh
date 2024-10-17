#!/bin/bash
clear
cd src || exit
javac CronParser.java
java CronParser "$1"
find . -type f -name "*.class" -delete