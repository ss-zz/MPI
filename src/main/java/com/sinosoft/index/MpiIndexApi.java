package com.sinosoft.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.index.exception.RegisterBizException;
import com.sinosoft.index.exception.RegisterCheckException;
import com.sinosoft.index.model.IndexRegister;
import com.sinosoft.index.model.IndexRegisterResult;
import com.sinosoft.index.model.IndexRegisterResultStatus;
import com.sinosoft.index.service.MpiRegisterService;

/**
 * mpi接口-主索引
 * 
 * @author sinosoft
 *
 */
@RestController
@RequestMapping("/api/index")
public class MpiIndexApi {

	@Autowired
	MpiRegisterService mpiRegisterService;

	/**
	 * 主索引注册
	 * 
	 * @param index
	 * @return
	 */
	@PostMapping("/register")
	public IndexRegisterResult register(IndexRegister index) {
		try {
			mpiRegisterService.register(index);
			return new IndexRegisterResult(IndexRegisterResultStatus.SUCCESS);
		} catch (RegisterCheckException e) {
			e.printStackTrace();
			return new IndexRegisterResult(IndexRegisterResultStatus.FAIL_CHECK.getCode(), e.getMessage());
		} catch (RegisterBizException e) {
			e.printStackTrace();
			return new IndexRegisterResult(IndexRegisterResultStatus.FAIL_BIZ.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new IndexRegisterResult(IndexRegisterResultStatus.FAIL_OTHER);
		}
	}

}
