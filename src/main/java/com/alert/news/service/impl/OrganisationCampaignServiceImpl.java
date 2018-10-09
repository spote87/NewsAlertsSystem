package com.alert.news.service.impl;

import java.util.List;

import com.alert.news.event.NewsPublishedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.repository.OrganisationCampaignRepository;
import com.alert.news.service.OrganisationCampaignService;

import javax.validation.constraints.NotNull;

/**
 * This is implementation of organization service.
 *
 * @author Shivaji Pote
 */
@Slf4j
@Service
public class OrganisationCampaignServiceImpl implements OrganisationCampaignService {

    /**
     * Invalid campaign error message
     */
    private static final String INVALID_CAMPAIGN_ERROR_MSG = "Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.";

    @Autowired
    private OrganisationCampaignRepository campaignRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * {@inheritDoc}
     *
     * @param campaign
     */
    @Override
    public void registerCampaign(final OrganisationCampaign campaign)
            throws OrganisationCampaignException {
        Assert.notNull(campaign, "Organisation campaign cannot be null");
        if (isValid(campaign)) {
            campaignRepository.insert(campaign);
            log.debug("Campaign saved successfully..");
            this.fireNewsPublishedEvent(campaign);
        } else {
            log.error(INVALID_CAMPAIGN_ERROR_MSG);
            throw new OrganisationCampaignException(INVALID_CAMPAIGN_ERROR_MSG);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCampaignStatus(@NotNull final Long id, @NotNull final String status) {
        Assert.notNull(id, "id cannot be null.");
        Assert.notNull(status, "status cannot be null.");
        campaignRepository.updateStatus(id, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrganisationCampaign> getCampaignByStatus(@NotNull final String status) {
        Assert.notNull(status, "Status cannot be null.");
        return campaignRepository.findByStatus(status);
    }

    /**
     * Fires {@link com.alert.news.event.NewsPublishedEvent}.
     *
     * @param campaign publised campaign
     */
    private void fireNewsPublishedEvent(final OrganisationCampaign campaign) {
        log.debug("Firing news published event..");
        final NewsPublishedEvent newsPublishedEvent = new NewsPublishedEvent(this, campaign);
        applicationEventPublisher.publishEvent(newsPublishedEvent);
        log.debug("Fired news published event..");
    }

    /**
     * Check weather given campaign is valid or not
     *
     * @param organisationCampaign instance of {@link OrganisationCampaign}
     * @return true if all mandatory fields are correct, false otherwise
     */
    private boolean isValid(final OrganisationCampaign organisationCampaign) {
        return (!StringUtils.isEmpty(organisationCampaign.getOrgnisationName())
                && !StringUtils.isEmpty(organisationCampaign.getCampaignTitle())
                && organisationCampaign.getTaggedCategories() != null && !organisationCampaign.getTaggedCategories().isEmpty());
    }

}
