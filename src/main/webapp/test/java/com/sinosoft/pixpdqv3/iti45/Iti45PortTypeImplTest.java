package com.sinosoft.pixpdqv3.iti45;

import com.sinosoft.pixpdqv3.TextFileReader;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * iti45测试
 */
@ContextConfiguration(locations={"classpath:/testApplicationContext-hl7v3.xml"})
public class Iti45PortTypeImplTest extends AbstractJUnit4SpringContextTests {
    @Resource(name="iti45Client")
    private Iti45PortType iti45PortType;
    @Test
    public void testGetIdentifiers() throws Exception {
        String string = TextFileReader.read("D:\\IdeaProjects\\mpi\\src\\test\\resources\\model\\PRPA_IN201309UV02.xml");

        String result = iti45PortType.getIdentifiers(string);
        System.out.println(result);
    }
}
