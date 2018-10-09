package com.alert.news.sms;


import com.alert.news.model.OrganisationCampaign;
import com.alert.news.model.User;

/**
 * This is interface for SMS Sender/s
 *
 * @author Shivaji Pote
 */
public interface SmsSender {

    /**
     * This method sends SMS to specified user on his/her mobile number.
     *
     * @param user     user to whom SMS needs to be sent
     * @param campaign organisation campaign
     */
    void send(final User user, final OrganisationCampaign campaign);
}
