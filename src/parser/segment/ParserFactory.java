package parser.segment;

import enums.ExpressionType;

public class ParserFactory {

    public static Parser getParser(ExpressionType expressionType) {
        switch (expressionType) {
            case MINUTE: return new MinuteParser();
            case HOUR: return new HourParser();
            case DAY_OF_MONTH: return new DayOfMonthParser();
            case DAY_OF_WEEK: return new DayOfWeekParser();
            case MONTH: return new MonthParser();
            case COMMAND: return new CommandParser();
            default: throw new RuntimeException("Invalid argument");
        }
    }

    public static String getColumn(ExpressionType expressionType) {
        switch (expressionType) {
            case MINUTE: return "minute";
            case HOUR: return "hour";
            case DAY_OF_MONTH: return "day of month";
            case DAY_OF_WEEK: return "day of week";
            case MONTH: return "month";
            case COMMAND: return "command";
            default: throw new RuntimeException("Invalid argument");
        }
    }
}
