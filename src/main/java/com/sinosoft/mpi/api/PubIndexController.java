package com.sinosoft.mpi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.model.search.BizSearchResult;
import com.sinosoft.mpi.service.MpiIndexRegisterService;
import com.sinosoft.mpi.service.MpiIndexSearchService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.ws.domain.DataResult;
import com.sinosoft.mpi.ws.domain.MpiIndexSearchParams;

/**
 * 公共接口
 */
@RestController
@RequestMapping("/api/pub/index")
public class PubIndexController {

	@Autowired
	MpiIndexRegisterService mpiIndexRegisterService;
	@Autowired
	MpiIndexSearchService mpiIndexSearchService;

	/**
	 * 注册人员
	 * 
	 * @param personRegister
	 * @return
	 */
	@PostMapping("/register/person")
	public DataResult<Void> registerPerson(@RequestBody PersonRegister personRegister) {
		return mpiIndexRegisterService.registerPerson(personRegister);
	}

	/**
	 * 批量注册人员
	 * 
	 * @param personRegisters
	 * @return
	 */
	@PostMapping("/register/persons")
	public DataResult<Void> registerPersons(@RequestBody PersonRegister[] personRegisters) {
		return mpiIndexRegisterService.registerPersons(personRegisters);
	}

	/**
	 * 注册业务
	 * 
	 * @param bizRegister
	 * @return
	 */
	@PostMapping("/register/biz")
	public DataResult<Void> registerBiz(@RequestBody BizRegister bizRegister) {
		return mpiIndexRegisterService.registerBiz(bizRegister);
	}

	/**
	 * 批量注册业务
	 * 
	 * @param bizInfos
	 * @return
	 */

	@PostMapping("/register/bizs")
	public DataResult<Void> registerBizs(@RequestBody BizRegister[] bizInfos) {
		return mpiIndexRegisterService.registerBizs(bizInfos);
	}

	/**
	 * 根据人员主索引id查询主索引详情
	 * 
	 * @param mpiId
	 * @return
	 */
	@GetMapping("/person/{mpiId}")
	public DataResult<PersonIndex> getPersonIndexByMpiId(@PathVariable String mpiId) {
		return mpiIndexSearchService.getPersonIndexByMpiId(mpiId);
	}

	/**
	 * 分页查询主索引
	 * 
	 * @param params
	 * @param page
	 * @return
	 */
	@GetMapping("/search/person")
	public DataResult<Page<PersonIndex>> searchPersonIndex(MpiIndexSearchParams params, PageInfo page) {
		return mpiIndexSearchService.searchPersonIndex(params, page);
	}

	/**
	 * 根据主索引id查询业务信息
	 * 
	 * @param mpiId
	 * @return
	 */
	@GetMapping("/biz/bympiid/{mpiId}")
	public DataResult<List<BizSearchResult>> searchBizsByMpiId(@PathVariable String mpiId) {
		return mpiIndexSearchService.searchBizsByMpiId(mpiId);
	}

	/**
	 * 根据主索引id和业务系统唯一标识查询业务信息
	 * 
	 * @param mpiId
	 * @param systemId
	 * @return
	 */
	@GetMapping("/biz/bympiidandsystemid/{mpiId}/{systemId}")
	public DataResult<List<BizSearchResult>> searchBizsByMpiIdAndSystemId(@PathVariable String mpiId,
			@PathVariable String systemId) {
		return mpiIndexSearchService.searchBizsByMpiIdAndSystemId(mpiId, systemId);
	}

}
