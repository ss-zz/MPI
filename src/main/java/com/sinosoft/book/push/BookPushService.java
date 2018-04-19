package com.sinosoft.book.push;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.BookLog;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.service.IBookLogService;
import com.sinosoft.mpi.service.IIdentifierDomainService;
import com.sinosoft.mpi.util.DateUtil;

@Service
public class BookPushService implements IBookPushService {
	private Logger logger = Logger.getLogger(BookPushService.class);
	@Resource
	private IBookLogService bookLogService;
	@Resource
	private IIdentifierDomainService identifierDomainService;

	@Override
	public void pushMassage() {
		// logger.debug("向身份域推送数据定时任务启动,@"+System.currentTimeMillis());
		// 取得所有要推送的身份域
		/*
		 * List<IdentifierDomain> domains = identifierDomainService.queryPushDomain();
		 * //logger.debug("取得推送身份域个数:"+domains.size()); // 迭代域 for(IdentifierDomain
		 * domain : domains){ // 查询需要发送的数据 List<BookLog> list =
		 * bookLogService.queryDatas(domain);
		 * //logger.debug("身份域["+domain.getDOMAIN_DESC()+"],取得推送数据:"+list.size()+"条");
		 * if(list!=null && !list.isEmpty()){ sendMassage(domain,list); }
		 * 
		 * }
		 */

	}

	private void sendMassage(IdentifierDomain domain, List<BookLog> list) {
		/*
		 * List<BookMassage> msgList = new ArrayList<BookMassage>(list.size()); for
		 * (BookLog log : list) { // 组织数据 BookMassage msg = new
		 * BookMassage(log.getIndexId(), log.getUniqueSign(), log.getPersonIdentifier(),
		 * log.getEventType()); msgList.add(msg); }
		 * 
		 * // 调用发送 try { logger.debug("开始向身份域["+domain.getDomainDesc()+"]推送数据"); //
		 * 取得发送的service IBookPushHandler handler = getPushHandler(domain);
		 * handler.receiveMessage(msgList.toArray(new BookMassage[msgList.size()])); //
		 * 成功操作 修改日志信息 updateLogResult(list,"1");
		 * logger.debug("成功向身份域["+domain.getDomainDesc()+"]推送数据."); } catch (Exception
		 * e) { updateLogResult(list,"2");
		 * logger.debug("向身份域["+domain.getDomainDesc()+"]推送数据失败.",e); }
		 */
		// logger.debug("向身份域["+domain.getDOMAIN_DESC()+"]推送数据暂时取消");
	}

	private void updateLogResult(List<BookLog> list, String result) {
		for (BookLog log : list) {
			log.setOpResult(result);
			log.setOpTime(DateUtil.getTimeNow(new Date()));
			// 回写处理结果
			bookLogService.update(log);
		}
	}

	private IBookPushHandler getPushHandler(IdentifierDomain domain) {
		// TODO ben 这里取得要调用的远程连接地址 暂未实现 方式待定
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setServiceClass(IBookPushHandler.class);
		// 从domain信息中取得 调用地址
		factoryBean.setAddress(domain.getPUSH_ADDR());

		logger.debug("根据身份域[" + domain.getDOMAIN_DESC() + "]的推送地址[" + domain.getPUSH_ADDR() + "]获取远程推送接口.");

		IBookPushHandler client = (IBookPushHandler) factoryBean.create();
		return client;
	}

	public void setBookLogService(IBookLogService bookLogService) {
		this.bookLogService = bookLogService;
	}

	public void setIdentifierDomainService(IIdentifierDomainService identifierDomainService) {
		this.identifierDomainService = identifierDomainService;
	}

}
