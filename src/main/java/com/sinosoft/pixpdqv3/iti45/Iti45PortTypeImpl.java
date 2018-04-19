package com.sinosoft.pixpdqv3.iti45;

import com.sinosoft.mpi.exception.MpiException;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.service.IPersonInfoService;
import com.sinosoft.pixpdqv3.parser.ICdaParser;
import com.sinosoft.pixpdqv3.parser.ResponseParser;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 13-4-25
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class Iti45PortTypeImpl implements Iti45PortType {
    @Resource
    private IPersonInfoService personInfoService;
    @Resource
    private ICdaParser cdaParser;
    @Override
    public String getIdentifiers(@WebParam(partName = "Body", name = "PRPA_IN201309UV02", targetNamespace = "urn:hl7-org:v3") String request) {
        Map<String,Object> ctx = new TreeMap<String, Object>();
        String result=null;
        // 解析xml 生成PersonInfo对象
        try{
            PersonInfoSimple p = cdaParser.parser(request, ICdaParser.CdaType.PRPA_IN201309UV02, PersonInfoSimple.class);
            // 放置查询参数
            ctx.put("ps",p);
            // 取得人员索引
            PersonIndex pi = personInfoService.queryPersonIndexByPersonInfo(p.toPersonInfo());
            List<PersonInfo> list = Collections.emptyList();
            if(null != pi){
                list = personInfoService.queryPersonsByIndex(pi.getMPI_PK());
            }
            ctx.put("pl",list);
        } catch (Exception e) {
            ctx.put("err","error");
        }
        result= ResponseParser.parse(ResponseParser.ResponseType.PRPA_IN201310UV02,ctx);
        return result;
    }
}
