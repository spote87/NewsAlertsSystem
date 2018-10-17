package com.alert.news.service.impl;

import com.alert.news.TestConfiguration;
import com.alert.news.exception.OrganisationCampaignException;
import com.alert.news.model.OrganisationCampaign;
import com.alert.news.repository.OrganisationCampaignRepository;
import com.alert.news.service.OrganisationCampaignService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shivaji Pote
 */
public class OrganisationCampainServiceImplTest extends TestConfiguration {

    private static final String STATUS_NEW = "New";

    @InjectMocks
    private OrganisationCampaignService organisationService = new OrganisationCampaignServiceImpl();

    @Mock
    private OrganisationCampaignRepository organisationCampaignRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Rule
    final public ExpectedException exception = ExpectedException.none();

    @Test
    public void testRegisterCampaignForValidaDataShouldCallInsertFromRepository() throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign("ABC Orgnisation",
                "LIC introducing new policy with name 'Jivan Anand'",
                "Jivan Anand will provide 5L sum assured if you pay 5k for 10 years", Arrays.asList("Policies", "Finance"));
        organisationService.registerCampaign(campaign);
        Mockito.verify(organisationCampaignRepository, Mockito.times(1)).insert(campaign);
        Mockito.verify(applicationEventPublisher, Mockito.times(1)).publishEvent(Mockito.any());
    }

    @Test
    public void testRegisterCampaignForNullCampaignObjectShouldThrowExceptionWithProperMessage()
            throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(
                "Organisation campaign cannot be null");
        organisationService.registerCampaign(null);
        Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
    }

    @Test
    public void testRegisterCampaignForEmptyOrganisationNameShouldThrowException()
            throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        campaign.setCampaignDescription("Test campaign");
        campaign.setCampaignTitle("Test campaign");
        campaign.setTaggedCategories(Arrays.asList("Policies", "Finance"));
        campaign.setOrgnisationName("");
        exception.expect(OrganisationCampaignException.class);
        exception.expectMessage(
                "Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
        organisationService.registerCampaign(campaign);
        Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
    }

    @Test
    public void testRegisterCampaignForNullOrganisationNameShouldThrowException() throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        campaign.setCampaignDescription("Test campaign");
        campaign.setCampaignTitle("Test campaign");
        campaign.setTaggedCategories(Arrays.asList("Policies", "Finance"));
        exception.expect(OrganisationCampaignException.class);
        exception.expectMessage(
                "Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
        organisationService.registerCampaign(campaign);
        Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
    }

    @Test
    public void testRegisterCampaignForEmptyCampaignTitleShouldThrowException() throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        campaign.setOrgnisationName("test Org");
        campaign.setCampaignDescription("Test campaign");
        campaign.setCampaignTitle("");
        campaign.setTaggedCategories(Arrays.asList("Policies", "Finance"));
        exception.expect(OrganisationCampaignException.class);
        exception.expectMessage(
                "Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
        organisationService.registerCampaign(campaign);
        Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
    }

    @Test
    public void testRegisterCampaignForNullCampaignTitleShouldThrowException() throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        campaign.setOrgnisationName("test Org");
        campaign.setCampaignDescription("Test campaign");
        campaign.setTaggedCategories(Arrays.asList("Policies", "Finance"));
        exception.expect(OrganisationCampaignException.class);
        exception.expectMessage(
                "Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
        organisationService.registerCampaign(campaign);
        Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
    }

    @Test
    public void testRegisterCampaignForEmptyTaggedCategoriesShouldThrowException()
            throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        campaign.setOrgnisationName("test Org");
        campaign.setCampaignDescription("Test campaign");
        campaign.setCampaignTitle("Test title");
        campaign.setTaggedCategories(Arrays.asList());
        exception.expect(OrganisationCampaignException.class);
        exception.expectMessage(
                "Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
        organisationService.registerCampaign(campaign);
        Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
    }

    @Test
    public void testRegisterCampaignForNullTaggedCategoriesShouldThrowException() throws OrganisationCampaignException {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        campaign.setOrgnisationName("test Org");
        campaign.setCampaignDescription("Test campaign");
        campaign.setCampaignTitle("");
        exception.expect(OrganisationCampaignException.class);
        exception.expectMessage(
                "Invalid campaign object. Please make sure object is not empty or all mandatory fields are specified.");
        organisationService.registerCampaign(campaign);
        Mockito.verify(organisationCampaignRepository, Mockito.times(0)).insert(campaign);
    }

    @Test
    public void testGetCampaignByStatusReturnsCampaignsWithStatusNew() {
        final OrganisationCampaign campaign = new OrganisationCampaign();
        final List<OrganisationCampaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        Mockito.when(organisationCampaignRepository.findByStatus(STATUS_NEW)).thenReturn(campaigns);
        Assert.assertNotNull(organisationService.getCampaignByStatus(STATUS_NEW));
        Assert.assertSame(organisationService.getCampaignByStatus(STATUS_NEW), campaigns);
    }

    @Test
    public void testGetCampaignByStatusShouldReturnIllegalArgumentExceptionWhenStatusIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Status cannot be null.");
        organisationService.getCampaignByStatus(null);
    }

    @Test
    public void testUpdateCampaignStatusShouldCallUpdateStatusFromRepositoryWhenValidDataIsPassed() {
        organisationService.updateCampaignStatus(100L, STATUS_NEW);
        Mockito.verify(organisationCampaignRepository, Mockito.times(1)).updateStatus(Mockito.anyLong(), Mockito.anyString());
    }

    @Test
    public void testUpdateCampaignShouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id cannot be null.");
        organisationService.updateCampaignStatus(null, STATUS_NEW);
    }

    @Test
    public void testUpdateCampaignShouldThrowIllegalArgumentExceptionWhenStatusIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("status cannot be null.");
        organisationService.updateCampaignStatus(100L, null);
    }

}
