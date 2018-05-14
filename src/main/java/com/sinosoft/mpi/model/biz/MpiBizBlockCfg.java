package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sinosoft.bizblock.config.BizBlockConfig;
import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;

@Entity()
public class MpiBizBlockCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private String blockId;

	/**
	 * 初筛描述
	 */
	@Column
	private String blockDesc;

	/**
	 * 分组数量
	 */
	@Column
	private Integer groupCount;

	@Column
	private Date createDate;

	/**
	 * 状态：0 - 无效,1 - 有效
	 */
	@Column
	private String state;

	/* 字段匹配信息 */
	private Map<Integer, List<MpiBizBlockGroup>> groups;

	public MpiBizBlockCfg() {
		super();
	}

	public MpiBizBlockCfg(BizBlockConfig blockConfig) {
		setGroups(new HashMap<Integer, List<MpiBizBlockGroup>>(blockConfig.getBlockRounds().size()));
		int i = 0;
		for (BlockRound rount : blockConfig.getBlockRounds()) {
			List<MpiBizBlockGroup> list = new ArrayList<MpiBizBlockGroup>(rount.getBlockFields().size());
			getGroups().put(Integer.valueOf(i), list);
			for (BlockField field : rount.getBlockFields()) {
				MpiBizBlockGroup group = new MpiBizBlockGroup(field);
				list.add(group);
			}
			i++;
		}
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<Integer, List<MpiBizBlockGroup>> getGroups() {
		return groups;
	}

	public void setGroups(Map<Integer, List<MpiBizBlockGroup>> groups) {
		this.groups = groups;
	}

}
