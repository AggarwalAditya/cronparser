package parser.segment;

import parser.expression.ParsingUtil;

public class DayOfWeekParser extends Parser {

    @Override
    public String parse(String input) {
        return ParsingUtil.parse(input,1,7);
    }
}
