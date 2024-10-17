package parser.segment;

import parser.expression.ParsingUtil;

public class MonthParser extends Parser {

    @Override
    public String parse(String input) {
        int min = 1;
        int max = 12;
        return ParsingUtil.parse(input, min, max);
    }
}
