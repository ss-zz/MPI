package com.sinosoft.mpi.mq.handler.person;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dics.LogOpStyle;
import com.sinosoft.mpi.dics.LogOpType;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.IndexIdentifierRelService;
import com.sinosoft.mpi.service.MpiCombineRecService;
import com.sinosoft.mpi.service.PersonIndexService;
import com.sinosoft.mpi.service.PersonIndexUpdateService;
import com.sinosoft.mpi.service.PersonInfoService;

/**
 * 更新主索引
 */
@Service
public class UpdatePersonHandler {

	@Resource
	MpiCombineRecService mpiCombineRecService;
	@Resource
	IndexIdentifierRelService indexIdentifierRelService;
	@Resource
	PersonIndexService personIndexService;
	@Resource
	PersonInfoService personInfoService;
	@Resource
	PersonIndexUpdateService personIndexUpdateService;
	@Resource
	CommonHandlerService commonHanlderService;

	/**
	 * 更新主索引-根据关联的人员id
	 * 
	 * @param personinfo
	 *            人员信息
	 * @return 主索引id
	 */
	public String handleMessage(PersonInfo personinfo) {
		// 查询本次更新的原记录
		IndexIdentifierRel iir = indexIdentifierRelService.queryByFieldPK(personinfo.getRelationPk());
		String mpiPk = null;

		// 取原记录对应的主索引与居民的合并关系，并定位合并位置
		if (iir != null) {
			mpiPk = iir.getMpiPk();
			List<IndexIdentifierRel> iirs = indexIdentifierRelService.queryByMpiPK(mpiPk);
			int lastMerg = -1;
			// 查找出更新的居民信息的合并记录
			// combine_rec指代原合并上一条记录号
			for (IndexIdentifierRel temp : iirs) {
				lastMerg++;
				if (iir.getCombineNo().equals(temp.getCombineNo())) {
					break;
				}

			}
			// 如果更新信息相关索引关联不止一个人员信息需要更新当前人员信息并和其他关联人员信息再进行索引合并
			if (lastMerg != 0) {
				// 将主索引恢复到合并位置前的情况
				MpiCombineRec mpiCombineRec = mpiCombineRecService
						.queryByCombineNo(iirs.get(lastMerg - 1).getCombineNo());
				PersonIndex mergindex = mpiCombineRec.mpiCombineRecToPersonIndex();
				mergindex.setMpiPk(iir.getMpiPk());
				mergindex = personIndexUpdateService.updateIndex(mergindex, personinfo, LogOpType.MODIFY,
						LogOpStyle.AUTO_MERGE, null);
				// 级联清理当前合并记录后的合并关系，合并记录及字段合并级别记录
				indexIdentifierRelService.deleteRecurByCombinNo(iirs.get(lastMerg).getCombineNo(), LogOpType.MODIFY,
						LogOpStyle.AUTO_REMOVE);
				for (int i = lastMerg + 1; i < iirs.size(); i++) {
					PersonInfo mergInfo = personInfoService.getObject(iirs.get(i).getFieldPk());
					mergInfo.setDomainId(iirs.get(i).getDomainId());
					// 更新索引信息
					mergindex = personIndexUpdateService.updateIndex(mergindex, mergInfo, LogOpType.MODIFY,
							LogOpStyle.AUTO_MERGE, null);
					// 添加索引操作日志
					indexIdentifierRelService.saveIndexLog(mergInfo.getFieldPk(), mergindex.getMpiPk(),
							mergInfo.getDomainId(), LogOpType.MODIFY, LogOpStyle.AUTO_MERGE,
							"[" + mergInfo.getNameCn() + "]重新合并到主索引[" + mergindex.getNameCn());
				}
				mpiPk = commonHanlderService.savePersonIndex(personinfo);
			} else {
				// 删除主索引及相关记录
				// 级联清理主索引与人员关系
				indexIdentifierRelService.deleteRecurByCombinNo(iirs.get(lastMerg).getCombineNo(), LogOpType.MODIFY,
						LogOpStyle.AUTO_REMOVE);

				// 删除主索引
				personIndexService.deleteById(iir.getMpiPk());

				// 生成新主索引
				commonHanlderService.savePersonIndex(personinfo);

				for (int i = lastMerg + 1; i < iirs.size(); i++) {
					PersonInfo p = personInfoService.queryPersonsByFieldPK(iirs.get(i).getFieldPk());
					p.setDomainId(iirs.get(i).getDomainId());
					commonHanlderService.savePersonIndex(p);
				}
			}
		} else {
			// 如果索引信息库不为空，则
			throw new RuntimeException("没有相对应要合并居民信息的记录");
		}
		return mpiPk;
	}

}
