package com.sinosoft.pixpdqv3.iti44;

import com.sinosoft.pixpdqv3.TextFileReader;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * iti44 测试
 */
@ContextConfiguration(locations={"classpath:/testApplicationContext-hl7v3.xml"})
public class Iti44PixPortTypeImplTest extends AbstractJUnit4SpringContextTests {
    @Resource(name ="iti44Client")
    private Iti44PixPortType iti44PixPortType;

    @Test
    public void testRecordAdded() throws Exception {
        String string = TextFileReader.read("D:\\IdeaProjects\\mpi\\src\\test\\resources\\model\\PRPA_IN201301UV02.xml");

        String result = iti44PixPortType.recordAdded(string);
        System.out.println(result);
    }

    @Test
    public void testRecordRevised() throws Exception {
        String string = TextFileReader.read("D:\\IdeaProjects\\mpi\\src\\test\\resources\\model\\PRPA_IN201302UV02.xml");

        String result = iti44PixPortType.recordRevised(string);
        System.out.println(result);
    }

    @Test
    public void testDuplicatesResolved() throws Exception {
        String string = TextFileReader.read("D:\\IdeaProjects\\mpi\\src\\test\\resources\\model\\PRPA_IN201304UV02.xml");

        String result = iti44PixPortType.duplicatesResolved(string);
        System.out.println(result);
    }
}
