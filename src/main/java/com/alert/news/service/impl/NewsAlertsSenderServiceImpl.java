package com.alert.news.service.impl;

import com.alert.news.model.OrganisationCampaign;
import com.alert.news.model.User;
import com.alert.news.service.NewsAlertsSenderService;
import com.alert.news.service.OrganisationCampaignService;
import com.alert.news.service.UserService;
import com.alert.news.sms.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Shivaji Pote
 */
@Service
public class NewsAlertsSenderServiceImpl implements NewsAlertsSenderService {

    /**
     * Campaign status new
     */
    private static final String STATUS_NEW = "new";

    /**
     * Campaign status completed
     */
    private static final String STATUS_COMPLETED = "completed";

    @Autowired
    private SmsSender twilioSmsSender;

    @Autowired
    private OrganisationCampaignService organisationCampaignService;

    @Autowired
    private UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendAlerts(@NotNull final OrganisationCampaign campaign) {
        final List<User> users = userService.getAllUsers();
        users.forEach(user -> {
            for (final String category : campaign.getTaggedCategories()) {
                if (user.getSubscriptionCategories().contains(category)) {
                    sendAlert(user, campaign);
                }
            }
        });
        //once messages are sent, mark campaign as complete
        organisationCampaignService.updateCampaignStatus(campaign.getCampaignId(), STATUS_COMPLETED);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void sendAlerts() {
        final List<OrganisationCampaign> campaigns = organisationCampaignService.getCampaignByStatus(STATUS_NEW);
        if (!campaigns.isEmpty()) {
            //get the users who are registered for these categories
            final List<User> users = userService.getAllUsers();
            users.forEach(user -> {
                for (final OrganisationCampaign camp : campaigns) {
                    for (final String category : camp.getTaggedCategories()) {
                        if (user.getSubscriptionCategories().contains(category)) {
                            sendAlert(user, camp);
                        }
                    }
                }
            });
            //once messages are sent, mark campaign as complete
            campaigns.forEach(campaign -> organisationCampaignService.updateCampaignStatus(campaign.getCampaignId(), STATUS_COMPLETED));
        }
    }

    /**
     * Calls {@link com.alert.news.sms.twilio.TwilioSmsSender#send(User, OrganisationCampaign)} which internally sends sms to specified user.
     *
     * @param user     person to whom this campaign needs to send
     * @param campaign organisation campaign
     */
    private void sendAlert(final User user, final OrganisationCampaign campaign) {
        twilioSmsSender.send(user, campaign);
    }
}