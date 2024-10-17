import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CronParserTest {


    private static void runTests() {
        List<Boolean> testResults = new ArrayList<>();
        testResults.add(testBasic());
        testResults.add(testBasic2());

        System.out.println("--- Test Results ---");
        for(boolean testResult : testResults) {
            System.out.println(testResult);
        }
    }

    private static boolean testBasic() {
        String cronExpression = "*/15 0 */3 * 3-7 /usr/bin/find";
        CronParser cronParser = new CronParser(cronExpression);
        String result = cronParser.getOutputTable();
        String expectedValue = "minute        0 15 30 45\n" +
                "hour          0\n" +
                "day of month  1 4 7 10 13 16 19 22 25 28 31\n" +
                "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week   3 4 5 6 7\n" +
                "command       /usr/bin/find\n";
        return result.equals(expectedValue);
    }

    private static boolean testBasic2() {
        String cronExpression = "*/45 0 */3 * 3-7 /usr/bin/find";
        CronParser cronParser = new CronParser(cronExpression);
        String result = cronParser.getOutputTable();
        String expectedValue = "minute        0 45\n" +
                "hour          0\n" +
                "day of month  1 4 7 10 13 16 19 22 25 28 31\n" +
                "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week   3 4 5 6 7\n" +
                "command       /usr/bin/find\n";
        return result.equals(expectedValue);
    }


    public static void main(String[] args) {

        runTests();

    }

}
