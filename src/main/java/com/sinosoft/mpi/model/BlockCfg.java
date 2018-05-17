package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;

/**
 * 初筛规则
 */
@Entity(name = "MPI_BLOCK_CFG")
public class BlockCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String bolckId;

	/**
	 * 初筛描述
	 */
	private String blockDesc;

	/**
	 * 分组数量
	 */
	private Integer groupCount;

	/**
	 * 创建日期
	 */
	private String createDate;

	/**
	 * 生效状态(STATE) 0 - 无效 1 - 有效
	 */
	private String state;

	/**
	 * 字段匹配信息
	 */
	@Transient
	private Map<Integer, List<BlockGroup>> groups;

	public BlockCfg() {
		super();
	}

	public BlockCfg(BlockConfig blockConfig) {
		groups = new HashMap<Integer, List<BlockGroup>>(blockConfig.getBlockRounds().size());
		int i = 0;
		for (BlockRound rount : blockConfig.getBlockRounds()) {
			List<BlockGroup> list = new ArrayList<BlockGroup>(rount.getBlockFields().size());
			groups.put(Integer.valueOf(i), list);
			for (BlockField field : rount.getBlockFields()) {
				BlockGroup group = new BlockGroup(field);
				list.add(group);
			}
			i++;
		}
	}

	public String getBolckId() {
		return bolckId;
	}

	public void setBolckId(String bolckId) {
		this.bolckId = bolckId;
	}

	public String getBlockDesc() {
		return blockDesc;
	}

	public void setBlockDesc(String blockDesc) {
		this.blockDesc = blockDesc;
	}

	public Integer getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<Integer, List<BlockGroup>> getGroups() {
		return groups;
	}

	public void setGroups(Map<Integer, List<BlockGroup>> groups) {
		this.groups = groups;
	}

}
