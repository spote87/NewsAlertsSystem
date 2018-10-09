package com.alert.news.event;

import com.alert.news.service.NewsAlertsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * This class will listen to {@link NewsPublishedEvent}.
 *
 * @author Shivaji Pote
 */
@Component
public class NewsPublishedEventListener implements ApplicationListener<NewsPublishedEvent> {

    @Autowired
    private NewsAlertsSenderService newsAlertsSenderService;


    /**
     * This method sends news alerts when {@link NewsPublishedEvent} occurs.
     *
     * @param event instance of {@link NewsPublishedEvent}
     */
    @Override
    public void onApplicationEvent(final NewsPublishedEvent event) {
        newsAlertsSenderService.sendAlerts(event.getCampaign());
    }
}
