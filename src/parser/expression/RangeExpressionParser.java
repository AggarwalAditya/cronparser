package parser.expression;

public class RangeExpressionParser implements ExpressionParser{
    @Override
    public String parse(String expression, int min, int max) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] listElems = expression.split("-");
        if(listElems.length != 2){
            throw new RuntimeException("Invalid expression");
        }
        int rangeStart = Integer.parseInt(listElems[0]);
        int rangeEnd = Integer.parseInt(listElems[1]);
        for(int i = rangeStart; i <= rangeEnd; i++) {
            if(i>max || i<min){
                throw new RuntimeException("Invalid expression");
            }
            stringBuilder.append(i).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
