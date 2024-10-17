package parser.expression;

import enums.ParsingType;

public class ParsingUtil {

    private static ParsingType getParsingType(String expression) {
        if(expression.equals("*")) {
            return ParsingType.ANY;
        } else if(expression.matches(".*,.*")) {
            return ParsingType.LIST;
        } else if(expression.matches(".*\\/.*")) {
            return ParsingType.STEP;
        } else if(expression.matches("[0-9]+-[0-9]+")) {
            return ParsingType.RANGE;
        } else if(expression.matches("^[0-9]+$")) {
            return ParsingType.EXACT;
        } else {
            return ParsingType.ANY;
        }
    }

    public static String parse(String expression, int min, int max) {
        ParsingType parsingType = getParsingType(expression);
        switch (parsingType) {
            case LIST: return new ListExpressionParser().parse(expression, min, max);
            case STEP: return new StepExpressionParser().parse(expression, min, max);
            case RANGE: return new RangeExpressionParser().parse(expression, min, max);
            case EXACT: return new ExactExpressionParser().parse(expression, min, max);
            case ANY: return new AnyExpressionParser().parse(expression, min, max);
            default: throw new RuntimeException("Invalid argument");
        }
    }
}
