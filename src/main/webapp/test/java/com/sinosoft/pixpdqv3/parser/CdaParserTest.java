package com.sinosoft.pixpdqv3.parser;

import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.pixpdqv3.TextFileReader;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * com.sinosoft.pixpdqv3.parser.CdaParserTest
 *
 * @author bysun
 *         13-4-23 下午6:17
 */
public class CdaParserTest {
    @Test
    public void testParser() throws Exception {
        String string = TextFileReader.read("D:\\IdeaProjects\\mpi\\src\\test\\resources\\model\\PRPA_IN201301UV02.xml");
        ICdaParser parser = new CdaParser();
        PersonInfo p = parser.parser(string, ICdaParser.CdaType.PRPA_IN201301UV02, PersonInfo.class);
        ParserConfig cfg = ParserConfigReader.read(ICdaParser.CdaType.PRPA_IN201301UV02.getConfig());
        for(Map<String,String> c : cfg.getFields()){
            String field = c.get("FIELD");
            String  val = BeanUtils.getProperty(p, field);
            System.out.println(field+"=="+val);
        }

    }
}
