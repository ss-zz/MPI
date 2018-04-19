package com.sinosoft.book.pull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.sinosoft.book.model.BookMassage;
import com.sinosoft.mpi.model.BookLog;
import com.sinosoft.mpi.service.IBookLogService;
import com.sinosoft.mpi.util.DateUtil;

@WebService(endpointInterface = "com.sinosoft.book.pull.IBookPullService", serviceName = "BookPullService")
public class BookPullService implements IBookPullService {
	@Resource
	private IBookLogService bookLogService;

	@Override
	public BookMassage[] getMassages(String domainUniqueSign) {
		// 取得相应域的操作记录
		List<BookLog> list = bookLogService.queryDatasByDomain(domainUniqueSign);
		
		// 构建返回数据
		List<BookMassage> result = new ArrayList<BookMassage>(list.size());
		for (BookLog log : list) {
			BookMassage msg = new BookMassage(log.getMpipk(), log.getUniqueSign(),
					log.getPersonIdentifier(), log.getEventType());
			
			result.add(msg);
			// 成功操作 修改日志信息
			log.setOpResult("1");
			// 设置操作时间
			log.setOpTime(DateUtil.getTimeNow(new Date()));
			// 回写处理结果
			bookLogService.update(log);
		}
		
		return result.toArray(new BookMassage[result.size()]);
	}

	public void setBookLogService(IBookLogService bookLogService) {
		this.bookLogService = bookLogService;
	}
}
