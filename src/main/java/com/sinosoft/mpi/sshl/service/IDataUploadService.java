package com.sinosoft.mpi.sshl.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.service.IService;
import com.sinosoft.mpi.sshl.model.DataUpload;
import com.sinosoft.mpi.util.PageInfo;

public interface IDataUploadService extends IService<DataUpload>{
	public List<Map<String, String>> queryForPageMap(DataUpload t, PageInfo page);
}
