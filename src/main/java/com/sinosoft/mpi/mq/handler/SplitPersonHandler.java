package com.sinosoft.mpi.mq.handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.Constant;
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
 * 拆分主索引
 */
@Service
public class SplitPersonHandler {

	@Resource
	IndexIdentifierRelService indexIdentifierRelService;
	@Resource
	PersonIndexService personIndexService;
	@Resource
	PersonInfoService personInfoService;
	@Resource
	PersonIndexUpdateService personIndexUpdateService;
	@Resource
	MpiCombineRecService mpiCombineRecService;
	@Resource
	CommonHandlerService commonHanlderService;

	/**
	 * 拆分主索引
	 * 
	 * @param personinfo
	 *            人员信息
	 * @return 主索引id
	 */
	public String handleMessage(PersonInfo personinfo) {
		IndexIdentifierRel iir = indexIdentifierRelService.queryByFieldPK(personinfo.getRELATION_PK());
		String mpiPk = null;
		// 是否有对应的居民信息记录
		if (iir != null) {
			mpiPk = iir.getMPI_PK();
			List<IndexIdentifierRel> iirs = indexIdentifierRelService.queryByMpiPK(mpiPk);
			int lastMerg = -1;
			// 取当前信息合并记录的上一条
			for (IndexIdentifierRel temp : iirs) {
				lastMerg++;
				if (iir.getCOMBINE_NO().equals(temp.getCOMBINE_NO())) {
					break;
				}
			}
			if (lastMerg != 0) {
				// 取合并记录的对应主索引值,封装成主索引对象
				MpiCombineRec mpiCombineRec = mpiCombineRecService
						.queryByCombineNo(iirs.get(lastMerg - 1).getCOMBINE_NO());
				PersonIndex mergindex = mpiCombineRec.mpiCombineRecToPersonIndex();
				mergindex.setMPI_PK(iir.getMPI_PK());
				// 删除对应合并的记录之后的记录 级联清理合并关系，合并记录及字段合并级别记录
				indexIdentifierRelService.deleteRecurByCombinNo(iirs.get(lastMerg).getCOMBINE_NO());
				// 重新合并相关居民信息
				for (int i = lastMerg + 1; i < iirs.size(); i++) {
					PersonInfo mergInfo = personInfoService.getObject(iirs.get(i).getFIELD_PK());
					mergInfo.setDOMAIN_ID(iirs.get(i).getDOMAIN_ID());
					// 更新索引信息
					mergindex = personIndexUpdateService.updateIndex(mergindex, mergInfo);

					// 添加索引操作日志
					commonHanlderService.addIPersonIdxLogService(mergInfo.getFIELD_PK(), mergindex.getMPI_PK(),
							mergInfo.getDOMAIN_ID(), Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_AUTO_SPLIT,
							"[" + mergInfo.getNAME_CN() + "]重新合并到主索引[" + mergindex.getNAME_CN());
				}
			} else {
				// 删除主索引及相关记录
				// 级联清理当前合并记录后的合并关系，合并记录及字段合并级别记录
				indexIdentifierRelService.deleteRecurByCombinNo(iirs.get(lastMerg).getCOMBINE_NO());
				for (IndexIdentifierRel temp : iirs) {
					indexIdentifierRelService.delete(temp);
				}
				PersonIndex personindex = new PersonIndex();
				personindex.setMPI_PK(iir.getMPI_PK());
				personIndexService.delete(personindex);
				for (int i = lastMerg + 1; i < iirs.size(); i++) {
					PersonInfo p = personInfoService.queryPersonsByFieldPK(iirs.get(i).getFIELD_PK());
					p.setDOMAIN_ID(iirs.get(i).getDOMAIN_ID());
					commonHanlderService.savePersonIndex(p);
				}

			}

		} else {
			// 如果索引信息库不为空，则
			throw new RuntimeException("没有相对应要拆分的居民信息的记录");
		}
		return mpiPk;
	}

}
