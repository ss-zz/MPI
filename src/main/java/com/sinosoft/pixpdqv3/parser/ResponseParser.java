package com.sinosoft.pixpdqv3.parser;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;

import java.io.StringWriter;
import java.util.Map;

/**
 * com.sinosoft.pixpdqv3.parser.ResponseParser
 *
 * @author bysun
 *         13-4-24 下午5:49
 */
public class ResponseParser {
    protected final static Logger log = Logger.getLogger(ResponseParser.class);
    public static String parse(ResponseType type, Map<String, Object> ctx){
        String result = "";
        try {
            // 初始模板引擎
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(Velocity.RESOURCE_LOADER, "class");
            ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            ve.init();
            // 初始模板
            Template t = ve.getTemplate(type.getTemplate());
            // 载入工具 date等
            ToolManager toolManager = new ToolManager();
            toolManager.configure("/hl7v3/toolbox.xml");
            ToolContext toolContext = toolManager.createContext();
            // 创建上下文
            VelocityContext context = new VelocityContext(ctx,toolContext);
            StringWriter writer = new StringWriter();
            // 合并模板
            t.merge(context, writer);
            // 生成合并后内容
            result = writer.toString();
        } catch (Exception e) {
            log.warn("生成返回消息错误",e);
        }
        return result;
    }

    public static enum ResponseType {
        SUCCESS("/hl7v3/resp/success.vm"),
        ERROR("/hl7v3/resp/error.vm"),
        PRPA_IN201306UV02("/hl7v3/resp/PRPA_IN201306UV02.vm"),
        PRPA_IN201310UV02("/hl7v3/resp/PRPA_IN201310UV02.vm");
        private String template;

        private ResponseType(String template) {
            this.template = template;
        }

        public String getTemplate() {
            return template;
        }
    }
}
