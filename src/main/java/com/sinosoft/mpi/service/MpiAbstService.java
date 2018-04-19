package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.sinosoft.mpi.dao.IMpiAbstDao;
import com.sinosoft.mpi.dao.IPinyinDicDao;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.PingYin4J;

@Service("mpiAbstService")
public class MpiAbstService implements IMpiAbstService {
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(MpiAbstService.class);
	@Resource
	private IMpiAbstDao mpiAbstDao;
	@Resource
	private IPinyinDicDao pinyinDicDao;




	@Override
	public List<Map<String, Object>> queryForSplitPage(PersonIndex index,
			PageInfo page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForSplitPage(PersonIndex index,
			String fromIndexId, PageInfo page) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 生成主索引摘要信息
	 * @param PersonIndex 操作personIndex
	 *
	 */
	@Override
	public void save(PersonIndex personIndex) {
		if (personIndex == null) {
			return;
		}
		List<String> suggests = new ArrayList<String>(20);
		// 人员主键
		String pId = (String) personIndex.getMPI_PK();
		// 人员主键为空则终止操作
		if (pId == null || pId.isEmpty()) {
			logger.debug("addMpiAbst: pId is null {}");
			return;
		}

		// 姓名
		String pname = (String) personIndex.getNAME_CN();
		if (pname != null) {
			suggests.add(pname);
			// 拼音缩写（逗号分割）
			String fss = PingYin4J.converterToFirstSpell(pname);
			if (fss != null && !fss.isEmpty()) {
				suggests.addAll(Arrays.asList(fss.split(",")));
			}
			// 拼音（逗号分割）
			String ss = PingYin4J.converterToSpell(pname);
			if (ss != null && !ss.isEmpty()) {
				suggests.addAll(Arrays.asList(ss.split(",")));
			}
		}
		// 身份证号
		String idno = (String) personIndex.getID_NO();
		StringBuffer sb = new StringBuffer();
		sb.append("姓名：" + personIndex.getNAME_CN() + "\n");
		sb.append("性别：" + personIndex.getGENDER_CD() + "\n");
		sb.append("出生日期：" + personIndex.getBIRTH_DATE() + "\n");
		sb.append("地址：" + personIndex.getPROVINCE_NAME() + "*"
				+ personIndex.getCITY_NAME());
		sb.append("*" + personIndex.getAREA_NAME() + "*"
				+ personIndex.getSTREET_ID());
		sb.append("*" + personIndex.getVILLAGE_NAME() + "*"
				+ personIndex.getHOUSE_NO() + "\n");
		sb.append("联系人信息：{" + personIndex.getLINKMAN_NAME() + "*"
				+ personIndex.getLINKMAN_ADDR() + "*"
				+ personIndex.getLINKMAN_PHONE() + "};\n");
		String abst = sb.toString();
		
		// suggest为身份证号插入摘要表
		if (idno != null&&idno.length()>0) {
			mpiAbstDao.addMpiAbst(personIndex, idno, abst);
		}
		// suggest为拼音循环插入摘要表
		for (String suggest : suggests) {
			logger.debug("new suggest[{}] for {}=" + suggest);
			if (suggest == null || suggest.isEmpty()) {
				continue;
			}
			
			 // 为了保证拼音记录冗余信息不要过多，查询拼音字典表判断 该拼音摘要记录超过10次不插入
			mpiAbstDao.addMpiAbst(personIndex, suggest, abst);
			if (pinyinDicDao.isOverPinYinTimes(suggest)) {
				pinyinDicDao.updatePinyinTimes(suggest);
			}

		}

	}
	 /**
	 * 更新主索引摘要信息
	 * @param PersonIndex 操作personIndex
	 * 
	 */
	@Override
	public void update(PersonIndex t) {
		delete(t);
		save(t);
	}
	 /**
	 * 主索引摘要信息
	 * @param PersonIndex 操作personIndex
	 * 
	 */
	@Override
	public void delete(PersonIndex t) {
		mpiAbstDao.deleteById(t);
	}

	@Override
	public PersonIndex getObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonIndex> queryForPage(PersonIndex t, PageInfo page) {
		// TODO Auto-generated method stub
		return null;
	}
}
