package parser.expression;

public class AnyExpressionParser implements ExpressionParser {
    @Override
    public String parse(String expression, int min, int max) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int val = min; val <= max; val++) {
            stringBuilder.append(val).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
