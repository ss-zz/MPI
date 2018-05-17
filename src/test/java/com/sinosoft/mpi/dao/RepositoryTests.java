package com.sinosoft.mpi.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sinosoft.mpi.dao.biz.MpiBizIndexDao;
import com.sinosoft.mpi.model.biz.MpiBizIndex;

/**
 * dao测试
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MpiBizIndexDao mpiBizIndexDao;

	@Test
	public void testExample() throws Exception {
		MpiBizIndex srcItem = this.entityManager.persist(new MpiBizIndex());
		MpiBizIndex newItem = this.mpiBizIndexDao.save(srcItem);
		assertNotNull(newItem);
	}

}
