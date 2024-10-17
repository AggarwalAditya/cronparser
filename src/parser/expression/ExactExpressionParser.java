package parser.expression;

public class ExactExpressionParser implements ExpressionParser{
    @Override
    public String parse(String expression, int min, int max) {
        if(Integer.parseInt(expression) > max || Integer.parseInt(expression) < min) {
            throw new RuntimeException("Invalid expression");
        }
        return expression;
    }
}
