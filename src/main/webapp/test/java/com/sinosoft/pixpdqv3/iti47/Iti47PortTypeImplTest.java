package com.sinosoft.pixpdqv3.iti47;

import com.sinosoft.pixpdqv3.TextFileReader;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * iti47 测试
 */
@ContextConfiguration(locations={"classpath:/testApplicationContext-hl7v3.xml"})
public class Iti47PortTypeImplTest extends AbstractJUnit4SpringContextTests {
    @Resource(name = "iti47Client")
    private Iti47PortType iti47PortType;
    @Test
    public void testOperation() throws Exception {
        String string = TextFileReader.read("D:\\IdeaProjects\\mpi\\src\\test\\resources\\model\\PRPA_IN201305UV02.xml");

        String result = iti47PortType.operation(string);
        System.out.println(result);
    }
}
