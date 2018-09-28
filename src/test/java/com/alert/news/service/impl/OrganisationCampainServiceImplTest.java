/**
 * 
 */
package com.alert.news.service.impl;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.repository.OrganisationCampaignRepository;
import com.alert.news.service.OrganisationCampaignService;

/**
 * @author Shivaji Pote
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganisationCampainServiceImplTest {
	
	@InjectMocks
	OrganisationCampaignService organisationService = new OrganisationCampaignServiceImpl();

	@Mock
	OrganisationCampaignRepository organisationCampaignRepository;
	
	@Rule
	final public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testRegisterCampaignForValidaDataShouldCallInsertFromRepository() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign("ABC Orgnisation", "LIC introducing new policy with name 'Jivan Anand'", "Jivan Anand will provide 5L sum assured if you pay 5k for 10 years", "Policies");
		organisationService.registerCampaign(Optional.of(campaign));
		Mockito.verify(organisationCampaignRepository, Mockito.times(1)).insert(campaign);
	}
	
	@Test
	public void testRegisterCampaignForEmptyOptionalCampaignObjectShouldThrowExceptionWithProperMessage() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign();
		exception.expect(OrganisationCampaignException.class);
		exception.expectMessage("Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		organisationService.registerCampaign(Optional.empty());
		Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
	}
	
	@Test
	public void testRegisterCampaignForEmptyOrganisationNameShouldThrowException() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign();
		campaign.setCampaignDescription("Test campaign");
		campaign.setCampaignTitle("Test campaign");
		campaign.setTaggedCategories("Policies, Finance");
		campaign.setOrgnisationName("");
		exception.expect(OrganisationCampaignException.class);
		exception.expectMessage("Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		organisationService.registerCampaign(Optional.of(campaign));
		Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
	}
	
	@Test
	public void testRegisterCampaignForNullOrganisationNameShouldThrowException() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign();
		campaign.setCampaignDescription("Test campaign");
		campaign.setCampaignTitle("Test campaign");
		campaign.setTaggedCategories("Policies, Finance");
		exception.expect(OrganisationCampaignException.class);
		exception.expectMessage("Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		organisationService.registerCampaign(Optional.of(campaign));
		Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
	}
	
	@Test
	public void testRegisterCampaignForEmptyCampaignTitleShouldThrowException() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign();
		campaign.setOrgnisationName("test Org");
		campaign.setCampaignDescription("Test campaign");
		campaign.setCampaignTitle("");
		campaign.setTaggedCategories("Policies, Finance");
		exception.expect(OrganisationCampaignException.class);
		exception.expectMessage("Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		organisationService.registerCampaign(Optional.of(campaign));
		Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
	}
	
	@Test
	public void testRegisterCampaignForNullCampaignTitleShouldThrowException() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign();
		campaign.setOrgnisationName("test Org");
		campaign.setCampaignDescription("Test campaign");
		campaign.setTaggedCategories("Policies, Finance");
		exception.expect(OrganisationCampaignException.class);
		exception.expectMessage("Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		organisationService.registerCampaign(Optional.of(campaign));
		Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
	}
	
	@Test
	public void testRegisterCampaignForEmptyTaggedCategoriesShouldThrowException() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign();
		campaign.setOrgnisationName("test Org");
		campaign.setCampaignDescription("Test campaign");
		campaign.setCampaignTitle("Test title");
		campaign.setTaggedCategories("");
		exception.expect(OrganisationCampaignException.class);
		exception.expectMessage("Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		organisationService.registerCampaign(Optional.of(campaign));
		Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
	}
	
	@Test
	public void testRegisterCampaignForNullTaggedCategoriesShouldThrowException() throws OrganisationCampaignException {
		final OrganisationCampaign campaign = new OrganisationCampaign();
		campaign.setOrgnisationName("test Org");
		campaign.setCampaignDescription("Test campaign");
		campaign.setCampaignTitle("");
		exception.expect(OrganisationCampaignException.class);
		exception.expectMessage("Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
		organisationService.registerCampaign(Optional.of(campaign));
		Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
	}
	
	
}
