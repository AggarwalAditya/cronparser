package parser.expression;

public class StepExpressionParser implements ExpressionParser{
    @Override
    public String parse(String expression, int min, int max) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] listElems = expression.split("/");
        int adder = Integer.parseInt(listElems[1]);
        stringBuilder.append(min).append(" ");
        int val = min + adder;
        while(val<=max) {
            stringBuilder.append(val).append(" ");
            val = adder + val;
        }
        return stringBuilder.toString().trim();
    }
}
