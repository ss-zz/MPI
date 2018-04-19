package com.sinosoft.mpi.sshl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.sshl.dao.IDataUploadDetailDao;
import com.sinosoft.mpi.sshl.model.DataUploadDetail;
import com.sinosoft.mpi.sshl.service.IDataUploadDetailService;
import com.sinosoft.mpi.util.CodeConvertUtils;
import com.sinosoft.mpi.util.PageInfo;

@Service("dataUploadDetailService")
public class DataUploadDetailService implements IDataUploadDetailService {

	private Logger logger = Logger.getLogger(DataUploadDetailService.class);

	@Resource
	private IDataUploadDetailDao dataUploadDetailDao;

	@Override
	public void save(DataUploadDetail t) {

	}

	@Override
	public void update(DataUploadDetail t) {

	}

	@Override
	public void delete(DataUploadDetail t) {

	}

	@Override
	public DataUploadDetail getObject(String id) {
		return null;
	}

	@Override
	public List<DataUploadDetail> queryForPage(DataUploadDetail t, PageInfo page) {
		return null;
	}

	@Override
	public List<Map<String, String>> queryForPageMap(DataUploadDetail t, PageInfo page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT T.ORGCODE ORGCODE,			");
		sb.append("    B.ORGNAME ORGNAME,				");
		sb.append("    T.TAB_ID TABLEID,				");
		sb.append("    A.TABLENAME_CN TABLENAME,		");
		sb.append("    SUM(T.UPLOAD_COUNT) TOTAL		");
		sb.append("    FROM EHR_UPLOAD_DATA T		");
		sb.append("  LEFT JOIN EHR_TABLENAMECODE A ON A.TABLE_ID = T.TAB_ID			");
		sb.append("  LEFT JOIN COM_HN_ORGANISE B ON B.ORGCODE = T.ORGCODE		");
		sb.append(" WHERE T.ORGCODE = '" + t.getOrgcode() + "'					");
		sb.append("  AND A.BS_TYPE = '" + t.getBs_type() + "'					");
		sb.append(" AND T.UPLOAD_DATE BETWEEN TO_DATE('" + t.getStDate() + "','YYYY-MM-DD') AND TO_DATE('"
				+ t.getEndDate() + "','YYYY-MM-DD') ");
		if (t.getTablename() != null && !t.getTablename().isEmpty()) {
			sb.append(" AND A.TABLENAME_CN LIKE '%" + CodeConvertUtils.decodeURI2UTF8(t.getTablename()) + "%' ");
		}
		sb.append("  GROUP BY T.ORGCODE, B.ORGNAME, T.TAB_ID, A.TABLENAME_CN	order by total desc		");
		String sql = sb.toString();
		String countSql = page.buildCountSql(sql);
		page.setTotal(dataUploadDetailDao.getCount(countSql, new Object[] {}));
		logger.debug("Execute sql:[" + countSql + "],params[]");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + querySql + "],params[]");
		return dataUploadDetailDao.findAll(querySql);
	}

}
