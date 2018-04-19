package com.sinosoft.pixpdqv3.parser;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * com.sinosoft.pixpdqv3.parser.CdaParser
 *
 * @author bysun
 *         13-4-22 下午4:36
 */
@Component("cdaParser")
public class CdaParser implements ICdaParser {
    protected static Logger log = Logger.getLogger(CdaParser.class);

    @Override
    public <T> T parser(String body, CdaType type, Class<T> clz) throws Exception {
        ParserConfig cfg = ParserConfigReader.read(type.getConfig());
        T t = clz.newInstance();
        Map<String, String> ns = new TreeMap<String, String>();
        ns.put("n1", "urn:hl7-org:v3");
        SAXReader reader = new SAXReader();
        reader.getDocumentFactory().setXPathNamespaceURIs(ns);

        Document document = reader.read(new StringReader(body));
        // 设置命名空间

        for (Map<String, String> field : cfg.getFields()) {
            String name = field.get("FIELD");
            String path = field.get("PATH");
            String ftype = field.get("TYPE");
            XPath xPath = document.createXPath(path);
            Node node = xPath.selectSingleNode(document);
            if (null != node) {
                String val = node.getStringValue();
                log.debug(path + "=" + val);
                Object v = corvent(val, ftype);
                if (null != v) {
                    log.debug("set property [" + name + "]=[" + v + "]");
                    BeanUtils.setProperty(t, name, v);
                }
            } else {
                log.debug("can't get path:" + path);
            }
        }
        return t;
    }

    private final static Map<Integer, String> fmtStr = new TreeMap<Integer, String>();

    static {
        fmtStr.put(6, "yyyyMM");
        fmtStr.put(8, "yyyyMMdd");
        fmtStr.put(12, "yyyyMMddhhmm");
        //fmtStr.put(19,"yyyyMMddhhmmssZ");
        fmtStr.put(18, "yyyy-M-d h:m:s");
    }

    private Object corvent(String val, String type) throws ParseException {
        if (StringUtils.isBlank(val)) {
            return null;
        }

        if ("java.util.Date".equals(type)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d h:m:s");
            Date date = null;
            try {
                date = sdf.parse(val);
            } catch (ParseException e) {

            }
                /*if(fmtStr.get(val.length())!=null){
                    SimpleDateFormat sdf = new SimpleDateFormat(fmtStr.get(val.length()));
                    date = sdf.parse(val);
                }*/
            return date;
        }
        return val;
    }
}
