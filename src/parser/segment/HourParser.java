package parser.segment;

import parser.expression.ParsingUtil;

public class HourParser extends Parser {

    @Override
    public String parse(String input) {
        int min = 0;
        int max = 23;
        return ParsingUtil.parse(input, min, max);
    }
}
