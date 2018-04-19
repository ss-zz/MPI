package com.sinosoft.mpi.sshl.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.mpi.sshl.model.DataUpload;

public interface IDataUploadDao extends IBaseDao<DataUpload> {
	public List<Map<String, String>> findAll(String sql);
}
