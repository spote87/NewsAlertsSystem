package com.alert.news.service;

import java.util.List;

import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.repository.OrganisationCampaignRepository;

/**
 * This is interface for organization campaign service
 *
 * @author Shivaji Pote
 */
public interface OrganisationCampaignService {

    /**
     * This method calls
     * {@link OrganisationCampaignRepository#insert(Object)} which
     * internally stores campaign instance to database.
     *
     * @param campaign instance of {@link OrganisationCampaign}
     * @throws OrganisationCampaignException is something goes wrong while inserting
     *                                       campaign into database
     */
    void registerCampaign(final OrganisationCampaign campaign) throws OrganisationCampaignException;

    /**
     * This method calls {@link OrganisationCampaignRepository#updateStatus(Long, String)} to update status of specified camoaign.
     *
     * @param id     id of the campaign
     * @param status status to update
     */
    void updateCampaignStatus(final Long id, final String status);

    /**
     * This method retrieves campaigns by calling {@link OrganisationCampaignRepository#findByStatus(String)} method.
     *
     * @param status campaign status to look for
     * @return {@link List} of {@link OrganisationCampaign} with specified status
     */
    List<OrganisationCampaign> getCampaignByStatus(final String status);
}
