package com.alert.news.repository;

import com.alert.news.model.OrganisationCampaign;
import org.apache.thrift.transport.TTransportException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Shivaji Pote
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrganisationCampaignIntegrationTest {

    @Autowired
    private OrganisationCampaignRepository campaignRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void startCassandraEmbedded() throws TTransportException, IOException, InterruptedException {
        CassandraUtils.startEmbeddedCassandraServer();
    }

    @Test
    public void testInsertCampaignDetailsIntoDatabase() {
        final OrganisationCampaign campaign = new OrganisationCampaign("Test Org", "Test campaign",
                "Test campaign description", Arrays.asList("Finance", "Policies"));
        campaignRepository.insert(campaign);

        Assert.assertNotNull(campaignRepository.findById(1L));
    }

    @Test
    public void testFindByStatusNewReturnsListOfNewOrganisations() {
        final OrganisationCampaign campaign = new OrganisationCampaign("Org1", "Ttile 1", "Description 1", Arrays.asList("Finance", "LIC"));
        campaignRepository.insert(campaign);

        final List<OrganisationCampaign> newCampaign = campaignRepository.findByStatus("new");
        assertNotNull(newCampaign);
        assertTrue(newCampaign.size() > 1);
    }

    @Test
    public void testFindByStatusNotNewShouldReturnEmptyList() {
        final OrganisationCampaign campaign = new OrganisationCampaign("Org1", "Ttile 1", "Description 1", Arrays.asList("finance", "LIC"));
        campaignRepository.insert(campaign);

        final List<OrganisationCampaign> newCampaign = campaignRepository.findByStatus("Not New");
        assertNotNull(newCampaign);
        assertEquals(0, newCampaign.size());
    }
}
