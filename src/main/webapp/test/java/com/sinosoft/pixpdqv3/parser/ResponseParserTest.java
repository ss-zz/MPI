package com.sinosoft.pixpdqv3.parser;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * com.sinosoft.pixpdqv3.parser.ResponseParserTest
 *
 * @author bysun
 *         13-4-24 下午6:38
 */
public class ResponseParserTest {
    @Test
    public void testParse() throws Exception {
        Map<String,Object> ctx = new TreeMap<String, Object>();
        ctx.put("msg","tttttttt");
        System.out.println(ResponseParser.parse(ResponseParser.ResponseType.ERROR,ctx));

        System.out.println(ResponseParser.parse(ResponseParser.ResponseType.SUCCESS,ctx));
    }
}
