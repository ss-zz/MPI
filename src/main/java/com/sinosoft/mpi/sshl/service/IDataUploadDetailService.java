package com.sinosoft.mpi.sshl.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.service.IService;
import com.sinosoft.mpi.sshl.model.DataUploadDetail;
import com.sinosoft.mpi.util.PageInfo;

public interface IDataUploadDetailService extends IService<DataUploadDetail> {
	public List<Map<String, String>> queryForPageMap(DataUploadDetail t, PageInfo page);
}
