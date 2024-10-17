import enums.ExpressionType;
import parser.segment.ParserFactory;

import java.util.HashMap;
import java.util.Map;

public class CronParser {

    private final Map<ExpressionType, String> expressionSegments;
    private final Map<ExpressionType, String> parsedExpressionSegments;

    private static void greeting() {
        System.out.println("!!!!! Welcome to cron parser !!!!!");
        System.out.println();
    }

    public static void validateArgs(String[] args) {
        if(args.length!=1) {
            throw new RuntimeException("Please give an argument");
        }

        String[] argsSplit = args[0].split(" ");
        if(argsSplit.length!=6) {
            throw new RuntimeException("Please give the correct number of arguments");
        }
    }

    public CronParser(String argument) {
        expressionSegments = new HashMap<>();
        parsedExpressionSegments = new HashMap<>();
        breakIntoSegments(argument);
        parse();
    }

    private void breakIntoSegments(String arg) {
        String[] argsSplit = arg.split(" ");
        expressionSegments.put(ExpressionType.MINUTE, argsSplit[0]);
        expressionSegments.put(ExpressionType.HOUR, argsSplit[1]);
        expressionSegments.put(ExpressionType.DAY_OF_MONTH, argsSplit[2]);
        expressionSegments.put(ExpressionType.MONTH, argsSplit[3]);
        expressionSegments.put(ExpressionType.DAY_OF_WEEK, argsSplit[4]);
        expressionSegments.put(ExpressionType.COMMAND, argsSplit[5]);
    }

    private void parse() {
        for(Map.Entry<ExpressionType,String> entry: expressionSegments.entrySet()) {
            ExpressionType expressionType = entry.getKey();
            String expression = entry.getValue();

            String parsedExpression = ParserFactory.getParser(expressionType).parse(expression);
            parsedExpressionSegments.put(expressionType, parsedExpression);
        }
    }

    public String getOutputTable() {
        ExpressionType[] printOrder = {ExpressionType.MINUTE, ExpressionType.HOUR, ExpressionType.DAY_OF_MONTH, ExpressionType.MONTH, ExpressionType.DAY_OF_WEEK, ExpressionType.COMMAND};
        StringBuilder table = new StringBuilder();

        for(ExpressionType expressionType: printOrder) {
            StringBuilder row = new StringBuilder();
            String column = ParserFactory.getColumn(expressionType);
            row.append(column);
            int remainingWhitespace = 14-column.length();
            for(int i=0;i<remainingWhitespace;i++) {
                row.append(" ");
            }
            row.append(parsedExpressionSegments.get(expressionType));
            row.append(System.lineSeparator());
            table.append(row);
        }
        return table.toString();
    }

    public static void main(String[] args) {
        try {
            greeting();

            validateArgs(args);

            CronParser cronParser = new CronParser(args[0]);
            System.out.println(cronParser.getOutputTable());
        } catch (Exception re) {
            System.err.println("Invalid Argument");
        }
    }
}