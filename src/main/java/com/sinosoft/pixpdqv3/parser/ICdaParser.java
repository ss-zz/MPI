package com.sinosoft.pixpdqv3.parser;

/**
 * cda解析
 * @author bysun
 * 2013-4-22 16:36:42
 */
public interface ICdaParser {
    /**
     * 解析消息
     * @param body 消息xml
     * @param type 消息类型
     * @param clz 生成对象类型
     * @return 解析得到的对象(组)
     * @throws Exception
     */
    <T> T parser(String body,CdaType type,Class<T> clz) throws Exception;

    /**
     * Cda文档类型
     */
    public static enum CdaType{
        PRPA_IN201301UV02("PRPA_IN201301UV02.xml"),
        PRPA_IN201302UV02("PRPA_IN201302UV02.xml"),
        PRPA_IN201304UV02("PRPA_IN201304UV02.xml"),
        PRPA_IN201305UV02("PRPA_IN201305UV02.xml"),
        PRPA_IN201309UV02("PRPA_IN201309UV02.xml");
        /** 配置文件 */
        private String config;

        private CdaType(String config) {
            this.config = config;
        }

        /**
         * 取得配置文件
         * @return 配置文件名
         */
        public String getConfig() {
            return config;
        }
    }
}
