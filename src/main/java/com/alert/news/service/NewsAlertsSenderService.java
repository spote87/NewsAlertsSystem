package com.alert.news.service;

import com.alert.news.model.OrganisationCampaign;

/**
 * This is interface for news alerts sender service/s.
 *
 * @author Shivaji Pote
 */
public interface NewsAlertsSenderService {

    /**
     * This method sends all campaigns alerts to all the users which are in state <em>New</</em>.
     */
    void sendAlerts();

    /**
     * This method sends specified campaign to all registered users.
     *
     * @param campaign campaign to be sent to users
     */
    void sendAlerts(OrganisationCampaign campaign);
}
