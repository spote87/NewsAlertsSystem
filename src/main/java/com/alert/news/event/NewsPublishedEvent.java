package com.alert.news.event;

import com.alert.news.model.OrganisationCampaign;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * This is event class for published news.
 *
 * @author Shivaji Pote
 */
public class NewsPublishedEvent extends ApplicationEvent {

    /**
     * instance of {@link OrganisationCampaign}
     */
    @Getter
    @Setter
    private transient OrganisationCampaign campaign;

    /**
     * {@link NewsPublishedEvent} Constructor
     *
     * @param source   source object on which this event has occurred
     * @param campaign instance of {@link OrganisationCampaign} which needs to be sent to registered users
     */
    public NewsPublishedEvent(final Object source, final OrganisationCampaign campaign) {
        super(source);
        this.campaign = campaign;
    }
}
