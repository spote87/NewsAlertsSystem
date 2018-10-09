package com.alert.news.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * This class contains {@link TaskScheduler} configurations for scheduled jobs.
 * 
 * @author Shivaji Pote
 *
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

	/**
	 * thread pool size
	 */
	private static final int POOL_SIZE = 10;

	/**
	 * Thread name prefix
	 */
	private static final String THREAD_NAME_PREFIX = "news-alert-sender-thread-";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
		final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(POOL_SIZE);
		taskScheduler.setThreadNamePrefix(THREAD_NAME_PREFIX);
		taskScheduler.initialize();
		taskRegistrar.setTaskScheduler(taskScheduler);
	}

}
