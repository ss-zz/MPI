package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 合记录字段级别
 */
@Entity(name = "MPI_COMBINE_LEVEL")
public class MpiCombineLevel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Short combineRecLev;

	private String combineField;

	private Short srcLevel;

	public Short getCombineRecLev() {
		return combineRecLev;
	}

	public void setCombineRecLev(Short combineRecLev) {
		this.combineRecLev = combineRecLev;
	}

	public String getCombineField() {
		return combineField;
	}

	public void setCombineField(String combineField) {
		this.combineField = combineField;
	}

	public Short getSrcLevel() {
		return srcLevel;
	}

	public void setSrcLevel(Short srcLevel) {
		this.srcLevel = srcLevel;
	}

}