package com.sinosoft.block.model;

import java.io.Serializable;
import java.util.List;

public class BlockRound implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8982497977093158488L;
	
	private List<BlockField> blockFields;

	public List<BlockField> getBlockFields() {
		return blockFields;
	}

	public void setBlockFields(List<BlockField> blockFields) {
		this.blockFields = blockFields;
	}
	
	
}
