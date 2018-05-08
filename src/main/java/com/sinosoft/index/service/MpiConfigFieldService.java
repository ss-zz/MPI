package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.MpiConfigFieldRepository;
import com.sinosoft.index.entity.MpiConfigField;
import com.sinosoft.index.exception.ServiceException;

/**
 * 主索引字段配置服务
 */
@Service
public class MpiConfigFieldService {

	@Autowired
	MpiConfigFieldRepository mpiConfigFieldRepository;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<MpiConfigField> getAll() {
		return mpiConfigFieldRepository.findAll();
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public MpiConfigField getById(String id) {
		return mpiConfigFieldRepository.findOne(id);
	}

	/**
	 * 字段名是否存在
	 * 
	 * @param propertyName
	 *            字段名唯一标识
	 * @return 字段名是否存在
	 */
	public boolean isExist(String propertyName) {
		return mpiConfigFieldRepository.countByPropertyName(propertyName) == 0 ? false : true;
	}

	/**
	 * 保存
	 * 
	 * @param item
	 *            保存对象
	 * @return
	 * @throws ServiceException
	 *             业务异常
	 */
	public String save(MpiConfigField item) {
		if (item == null) {
			return null;
		}
		if (item.getId() == null) {// 新增
			if (isExist(item.getPropertyName())) {
				throw new ServiceException("字段名已存在");
			}
		}
		return mpiConfigFieldRepository.save(item).getId();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            id
	 */
	public void del(String id) {
		mpiConfigFieldRepository.delete(id);
	}

}
