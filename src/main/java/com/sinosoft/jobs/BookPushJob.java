package com.sinosoft.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sinosoft.book.push.BookPushService;

/**
 * 定时任务-推送消息
 * 
 * @author sinosoft
 *
 */
@Component
public class BookPushJob {

	@Autowired
	BookPushService bookPushService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void bookPush() {
		bookPushService.pushMassage();
	}

}
