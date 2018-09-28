/**
 * Copyright : No copyright
 */
package com.alert.news.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.repository.OrganisationCampaignRepository;
import com.alert.news.service.OrganisationCampaignService;

/**
 * This is implementation of organization service.
 * 
 * @author Shivaji Pote
 *
 */
@Service
public class OrganisationCampaignServiceImpl implements OrganisationCampaignService {

	@Autowired
	OrganisationCampaignRepository campaignRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerCampaign(final Optional<OrganisationCampaign> optionalCampaign)
			throws OrganisationCampaignException {
		if (optionalCampaign.isPresent() && isValid(optionalCampaign.get())) {
			campaignRepository.insert(optionalCampaign.get());
		} else {
			throw new OrganisationCampaignException(
					"Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		}
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
				&& !StringUtils.isEmpty(organisationCampaign.getTaggedCategories()));
	}

}
