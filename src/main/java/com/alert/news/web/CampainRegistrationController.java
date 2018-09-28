/**
 * Copyright : No copyright
 */
package com.alert.news.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.service.OrganisationCampaignService;

/**
 * This is controller class which contains endpoint definition for registering
 * campaign.
 * 
 * @author Shivaji Pote
 *
 */
@RestController
public class CampainRegistrationController {

	@Autowired
	private OrganisationCampaignService organisationCampaign;

	/**
	 * <em>POST</em> mapping for registering campaign.
	 * 
	 * @param campain JSON body which maps to {@link OrganisationCampaign} instance
	 * @return instance of {@link ResponseEntity} which contains message and status
	 *         code
	 * @throws OrganisationCampaignException
	 */
	@PostMapping("/registercampain")
	public ResponseEntity<String> registerOrganisationCampain(@RequestBody OrganisationCampaign campain)
			throws OrganisationCampaignException {
		organisationCampaign.registerCampaign(Optional.of(campain));
		return new ResponseEntity<>("Campain registered successful", HttpStatus.OK);
	}
}
