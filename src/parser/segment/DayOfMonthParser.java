package parser.segment;

import parser.expression.ParsingUtil;

public class DayOfMonthParser extends Parser {

    @Override
    public String parse(String input) {
        int min = 1;
        int max = 31;
        return ParsingUtil.parse(input, min, max);
    }
}
