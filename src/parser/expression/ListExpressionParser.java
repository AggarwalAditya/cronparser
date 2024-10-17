package parser.expression;

public class ListExpressionParser implements ExpressionParser{
    @Override
    public String parse(String expression, int min, int max) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] listElems = expression.split(",");
        if(listElems.length < 2){
            throw new RuntimeException("Invalid expression");
        }
        for(String elem : listElems) {
            if(Integer.parseInt(elem)>max || Integer.parseInt(elem)<min){
                throw new RuntimeException("Invalid expression");
            }
            stringBuilder.append(elem).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
