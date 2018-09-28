/**
 * Copyright : No copyright
 */
package com.alert.news.service;

import java.util.Optional;

import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.repository.OrganisationCampaignRepository;

/**
 * This is interface for organization campaign service
 * 
 * @author Shivaji Pote
 *
 */
public interface OrganisationCampaignService {

	/**
	 * This method calls
	 * {@link OrganisationCampaignRepository#insert(OrganisationCampaign)} which
	 * internally stores campaign instance to database.
	 * 
	 * @param optionalCampaign instance of {@link OrganisationCampaign} wrapped in
	 *                         {@link Optional}
	 * @throws OrganisationCampaignException is something goes wrong while inserting
	 *                                       campaign into database
	 */
	void registerCampaign(final Optional<OrganisationCampaign> optionalCampaign) throws OrganisationCampaignException;

}
