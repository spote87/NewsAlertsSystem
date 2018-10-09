package com.alert.news.sms.twilio;

import com.alert.news.model.OrganisationCampaign;
import com.alert.news.model.User;
import com.alert.news.sms.SmsSender;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * This class is implementation of {@link SmsSender}. It contains methods which sends SMS using <em>Twilio</em> APIs.
 *
 * @author Shivaji Pote
 */
@Slf4j
@Service
public class TwilioSmsSender implements SmsSender {

    /**
     * From mobile number property
     */
    private static final String TWILIO_FROM_MOBILE_NUMBER = "twilio.from.mobile.number";

    /**
     * Twilio account SID
     */
    private static final String TWILIO_ACCOUNT_SID = "TWILIO_ACCOUNT_SID";

    /**
     * Twilio auth token
     */
    private static final String TWILIO_AUTH_TOKEN = "TWILIO_AUTH_TOKEN";

    @Autowired
    private Environment environment;

    /**
     * from mobile number field
     */
    private String from;

    /**
     * account SID field
     */
    private String accountSid;

    /**
     * auth token field
     */
    private String authToken;

    /**
     * This method initialises properties from <em>application.properties</em> file and <em>environment variables</em>.
     */
    @PostConstruct
    public void initialiseProperties() {
        from = environment.getProperty(TWILIO_FROM_MOBILE_NUMBER);
        accountSid = environment.getProperty(TWILIO_ACCOUNT_SID);
        authToken = environment.getProperty(TWILIO_AUTH_TOKEN);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(final User user, final OrganisationCampaign campaign) {
        if (StringUtils.isEmpty(accountSid) || StringUtils.isEmpty(authToken)) {
            log.error("Account SID and/or auth token is invalid..");
            return;
        }
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber("+" + user.getMobileNumber().toString()), new PhoneNumber(from),
                campaign.getCampaignDescription()).create();
        logStatus();
    }

    /**
     * This method logs status of message delivery asynchronously.
     */
    private void logStatus() {
        final ListenableFuture<ResourceSet<Message>> future = Message.reader().readAsync();
        Futures.addCallback(future, new FutureCallback<ResourceSet<Message>>() {

            @Override
            public void onSuccess(final ResourceSet<Message> messages) {
                for (final Message message : messages) {
                    log.debug("Message sent to " + message.getTo() + " is  " + message.getStatus());
                }
            }

            @Override
            public void onFailure(final Throwable t) {
                log.error("Error occurred while sending message. Error:" + t.getMessage());
            }
        });
    }
}
