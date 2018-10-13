package com.alert.news.web;

import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.service.OrganisationCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * This is controller class which contains endpoint definition for registering
 * campaign.
 *
 * @author Shivaji Pote
 */
@RestController
public class CampainRegistrationController {

    @Autowired
    private OrganisationCampaignService organisationCampaign;

    /**
     * <em>POST</em> mapping for registering campaign.
     *
     * @param campaign JSON body which maps to {@link OrganisationCampaign} instance
     * @return instance of {@link ResponseEntity} which contains message and status
     * code
     * @throws OrganisationCampaignException is something goes wrong while registering campaign
     */
    @PostMapping("/registercampaign")
    public ResponseEntity<String> registerOrganisationCampain(@Valid @NotNull @RequestBody OrganisationCampaign campaign)
            throws OrganisationCampaignException {
        organisationCampaign.registerCampaign(campaign);
        return new ResponseEntity<>("Campaign registered successful", HttpStatus.OK);
    }
}
