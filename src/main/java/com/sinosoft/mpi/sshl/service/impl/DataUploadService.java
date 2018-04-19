package com.sinosoft.mpi.sshl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.sshl.dao.IDataUploadDao;
import com.sinosoft.mpi.sshl.model.DataUpload;
import com.sinosoft.mpi.sshl.service.IDataUploadService;
import com.sinosoft.mpi.util.CodeConvertUtils;
import com.sinosoft.mpi.util.PageInfo;

@Service("dataUploadService")
public class DataUploadService implements IDataUploadService{
	private Logger logger = Logger.getLogger(DataUploadService.class);
	@Resource
	private IDataUploadDao dataUploadDao;
	
	@Override
	public void save(DataUpload t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DataUpload t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DataUpload t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataUpload getObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataUpload> queryForPage(DataUpload t, PageInfo page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> queryForPageMap(DataUpload t, PageInfo page) {
		// TODO Auto-generated method stub
		StringBuffer sb  = new StringBuffer();
		sb.append(" SELECT Z.DD_ID ZONECODE,			");
		sb.append("    Z.DD_NAME ZONENAME,				");
		sb.append("    T.ORGCODE ORGCODE,				");
		sb.append("    B.ORGNAME ORGNAME,				");
		sb.append("    COUNT(DISTINCT(T.TAB_ID)) TABLESUM,			");
		sb.append("    SUM(T.UPLOAD_COUNT) TOTAL		");
		sb.append("  FROM EHR_UPLOAD_DATA T				");
		sb.append("  LEFT JOIN EHR_TABLENAMECODE A ON A.TABLE_ID = T.TAB_ID	");
		sb.append("  LEFT JOIN COM_HN_ORGANISE B ON B.ORGCODE = T.ORGCODE	");
		sb.append("  LEFT JOIN V_ZONECODE Z ON B.APANAGECODE = Z.DD_ID		");
		sb.append("  WHERE A.BS_TYPE = '" + t.getBs_type() + "'				");
		if (t.getZonecode() != null && !t.getZonecode().isEmpty() && !t.getZonecode().equals("0000")) {
			sb.append("  AND Z.DD_ID = '" + t.getZonecode() + "'		");
		}
		if (t.getOrgcode() != null && !t.getOrgcode().isEmpty()) {
			sb.append(" AND T.ORGCODE LIKE '" + t.getOrgcode() + "%' 	");
		}
		if (t.getOrgname() != null && !t.getOrgname().isEmpty()) {
			sb.append(" AND B.ORGNAME LIKE '%"
					+ CodeConvertUtils.decodeURI2UTF8(t.getOrgname()) + "%' ");
		}
		sb.append(" AND T.UPLOAD_DATE BETWEEN  TO_DATE('" + t.getStDate()
				+ "','YYYY-MM-DD')" + " AND TO_DATE('" + t.getEndDate()
				+ "','YYYY-MM-DD') ");
		sb.append("  GROUP BY Z.DD_ID, Z.DD_NAME, T.ORGCODE, B.ORGNAME	order by total desc	");
		String sql = sb.toString();
		String countSql = page.buildCountSql(sql);
		page.setTotal(dataUploadDao.getCount(countSql, new Object[]{}));
		logger.debug("Execute sql:["+countSql+"],params[]");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:["+querySql+"],params[]");
		return dataUploadDao.findAll(querySql);	
	}
}
