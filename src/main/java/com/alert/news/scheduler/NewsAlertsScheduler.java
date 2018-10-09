package com.alert.news.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alert.news.service.NewsAlertsSenderService;

/**
 * This class contains <em>News Alerts </em> scheduled tasks.
 * 
 * @author Shivaji Pote
 *
 */
@Component
public class NewsAlertsScheduler {
	
	@Autowired
	private NewsAlertsSenderService newsAlertsSenderService;
	
	@Scheduled(cron="0 0 0 * * ?")
	public void scheduleNewsAlertsSender() {
		newsAlertsSenderService.sendAlerts();
	}
}
