package com.sinosoft.mpi.model.biz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 业务主索引
 */
@Entity
public class MpiBizIndex extends MpiBizInfo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue
	private String id;

}
