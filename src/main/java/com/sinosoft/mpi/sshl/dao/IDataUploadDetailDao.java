package com.sinosoft.mpi.sshl.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.mpi.sshl.model.DataUploadDetail;

public interface IDataUploadDetailDao extends IBaseDao<DataUploadDetail> {
	public List<Map<String, String>> findAll(String sql);
}
