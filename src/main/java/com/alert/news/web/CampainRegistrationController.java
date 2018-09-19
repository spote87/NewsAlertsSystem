/**
 * 
 */
package com.alert.news.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alert.news.model.OrganisationCampaign;

/**
 * @author qx13ip
 *
 */
@RestController
public class CampainRegistrationController {

	@PostMapping("/registercampain")
	public ResponseEntity<String> registerOrganisationCampain(@RequestBody OrganisationCampaign campain) {
		//TODO implement me
		return new ResponseEntity<>("Campain registered successful", HttpStatus.OK);
	}
}
