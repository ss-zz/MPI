package com.sinosoft.index.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.index.exception.RegisterBizException;
import com.sinosoft.index.exception.RegisterCheckException;
import com.sinosoft.index.model.BizCommonFieldConfigModel;
import com.sinosoft.index.model.BizConfigModel;
import com.sinosoft.index.model.BizFieldConfigModel;
import com.sinosoft.index.model.IndexRegister;

/**
 * mpi 注册服务
 * 
 * @author sinosoft
 *
 */
@Service
public class MpiRegisterService {

	@Autowired
	MpiIndexService mpiIndexService;
	@Autowired
	BizConfigService bizConfigService;
	@Autowired
	BizFieldConfigService bizFieldConfigService;
	@Autowired
	BizCommonFieldConfigService bizCommonFieldConfigService;
	@Autowired
	MqService mqService;

	private ObjectMapper om = new ObjectMapper();

	/**
	 * 主索引注册
	 * 
	 * @param index
	 *            注册数据
	 * @throws RegisterCheckException
	 *             注册异常
	 */
	public void register(IndexRegister index) throws RegisterCheckException, RegisterBizException {
		if (index == null) {
			throw new RegisterCheckException("注册数据不能为空");
		}

		// 类型
		String type = index.getType();
		if (!validateType(type)) {
			throw new RegisterCheckException("注册类型[type]错误，可选值为【" + getTypeStr() + "】");
		}
		// 默认类型为新增
		if (type == null) {
			type = IndexRegisterType.ADD.getType();
			index.setType(type);
		}

		// 原始id
		String srcId = index.getDataId();
		if (isNull(srcId)) {
			throw new RegisterCheckException("数据原始唯一标示[dataId]不能为空");
		}

		// 注册数据内容
		String jsonData = index.getJsonData();
		if (isNull(jsonData)) {
			throw new RegisterCheckException("注册数据内容[jsonData]不能为空");
		}
		try {
			om.readValue(jsonData, new TypeReference<HashMap<String, String>>() {
			});
		} catch (IOException e) {
			throw new RegisterCheckException("注册数据内容[jsonData]解析失败，请确定是合法的json格式");
		}

		// 业务id
		String bizKey = index.getBizKey();
		if (isNull(bizKey)) {
			throw new RegisterCheckException("业务唯一标识[bizKey]不能为空");
		}
		// 获取业务配置
		List<BizConfigModel> bizConfigs = bizConfigService.getByKey(bizKey);
		if (bizConfigs == null || bizConfigs.size() == 0) {
			throw new RegisterCheckException("业务[" + bizKey + "]未配置，请在管理系统中添加对应业务");
		}
		BizConfigModel bizConfig = bizConfigs.get(0);
		String bizConfigId = bizConfig.getId();
		// 获取业务字段配置
		List<BizFieldConfigModel> bizFieldConfigs = bizFieldConfigService.getByBizConfigId(bizConfigId);
		if (bizFieldConfigs == null || bizFieldConfigs.size() == 0) {
			throw new RegisterCheckException("业务[" + bizKey + "]对应字段未配置，请在管理系统中配置业务对应的字段");
		}

		// 获取通用业务字段配置
		List<BizCommonFieldConfigModel> bizCommonFieldConfigs = bizCommonFieldConfigService.getAll();
		if (bizCommonFieldConfigs == null || bizCommonFieldConfigs.size() == 0) {
			throw new RegisterCheckException("通用业务字段未配置，请在管理系统中配置通用业务字段");
		}

		// 将待处理数据发送到mq
		mqService.sendMessageToIndex(index);

	}

	/**
	 * 检验类型
	 * 
	 * @param type
	 *            类型
	 * @return 是否校验通过
	 */
	private boolean validateType(String type) {
		if (type != null) {
			for (IndexRegisterType typeItem : IndexRegisterType.values()) {
				if (type == typeItem.getType()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取type类型字符串
	 * 
	 * @return
	 */
	private String getTypeStr() {
		String ret = "";
		for (IndexRegisterType typeItem : IndexRegisterType.values()) {
			ret += typeItem.getType() + "|";
		}
		return ret;
	}

	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNull(String str) {
		return str == null || str.trim().equals("") ? true : false;
	}

}
