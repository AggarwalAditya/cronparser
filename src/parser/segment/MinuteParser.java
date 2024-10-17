package parser.segment;

import parser.expression.ParsingUtil;

public class MinuteParser extends Parser {

    @Override
    public String parse(String input) {
        int min = 0;
        int max = 59;
        return ParsingUtil.parse(input, min,max);
    }
}
