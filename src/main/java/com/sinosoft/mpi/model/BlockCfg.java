package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;

/**
 * MPI_BLOCK_CFG (初筛规则)
 */
public class BlockCfg implements Serializable {

	private static final long serialVersionUID = 2056899759888616783L;

	/* 初筛主键(BOLCK_ID) 初筛主键 */
	private String bolckId;

	/* 初筛描述(BLOCK_DESC) 初筛描述 */
	private String blockDesc;

	/* 分组数量(GROUP_COUNT) 分组数量 */
	private Integer groupCount;

	/* 创建日期(CREATE_DATE) 创建日期 */
	private String createDate;

	/* 生效状态(STATE) 0 - 无效 1 - 有效 */
	private String state;

	/* 字段匹配信息 */
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

	// =======================setter&getter
	/**
	 * 初筛主键(BOLCK_ID) 初筛主键
	 **/
	public String getBolckId() {
		return bolckId;
	}

	/**
	 * 初筛主键(BOLCK_ID) 初筛主键
	 **/
	public void setBolckId(String bolckId) {
		this.bolckId = bolckId;
	}

	/**
	 * 初筛描述(BLOCK_DESC) 初筛描述
	 **/
	public String getBlockDesc() {
		return blockDesc;
	}

	/**
	 * 初筛描述(BLOCK_DESC) 初筛描述
	 **/
	public void setBlockDesc(String blockDesc) {
		this.blockDesc = blockDesc;
	}

	/**
	 * 分组数量(GROUP_COUNT) 分组数量
	 **/
	public Integer getGroupCount() {
		return groupCount;
	}

	/**
	 * 分组数量(GROUP_COUNT) 分组数量
	 **/
	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}

	/**
	 * 创建日期(CREATE_DATE) 创建日期
	 **/
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 创建日期(CREATE_DATE) 创建日期
	 **/
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 生效状态(STATE) 0 - 无效 1 - 有效
	 **/
	public String getState() {
		return state;
	}

	/**
	 * 生效状态(STATE) 0 - 无效 1 - 有效
	 **/
	public void setState(String state) {
		this.state = state;
	}

	public Map<Integer, List<BlockGroup>> getGroups() {
		return groups;
	}

	public void setGroups(Map<Integer, List<BlockGroup>> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blockDesc == null) ? 0 : blockDesc.hashCode());
		result = prime * result + ((bolckId == null) ? 0 : bolckId.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((groupCount == null) ? 0 : groupCount.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockCfg other = (BlockCfg) obj;
		if (blockDesc == null) {
			if (other.blockDesc != null)
				return false;
		} else if (!blockDesc.equals(other.blockDesc))
			return false;
		if (bolckId == null) {
			if (other.bolckId != null)
				return false;
		} else if (!bolckId.equals(other.bolckId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (groupCount == null) {
			if (other.groupCount != null)
				return false;
		} else if (!groupCount.equals(other.groupCount))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BlockCfg [bolckId=" + bolckId + ", blockDesc=" + blockDesc + ", groupCount=" + groupCount
				+ ", createDate=" + createDate + ", state=" + state + "]";
	}

}
