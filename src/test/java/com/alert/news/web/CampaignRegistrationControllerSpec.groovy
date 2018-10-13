package com.alert.news.web

import com.alert.news.service.OrganisationCampaignService
import groovy.json.JsonOutput
import org.apache.http.HttpStatus
import org.apache.http.entity.ContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.mock.DetachedMockFactory


@WebMvcTest(controllers = [CampainRegistrationController])
class CampaignRegistrationSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private OrganisationCampaignService organisationCampaignService

    def "/registercampaign rest endpoint should return success message and status OK when valid data is passed"() {
        given:
        final Map request = [
                orgnisationName    : 'Test Org',
                campaignTitle      : 'Test title',
                campaignDescription: 'Test description',
                taggedCategories   : ['Finance', 'categories']
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/registercampaign").contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == HttpStatus.SC_OK
        and:
        response.contentAsString == "Campaign registered successful"
        and:
        1 * organisationCampaignService.registerCampaign(_)
    }

    def "/registercampaign rest endpoint should return throw error when mandatory parameter 'organisationName' is not passed"() {
        given:
        final Map request = [
                campaignTitle      : 'Test title',
                campaignDescription: 'Test description',
                taggedCategories   : ['Finance', 'categories']
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/registercampaign").contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == HttpStatus.SC_BAD_REQUEST
        and:
        0 * organisationCampaignService.registerCampaign(_)
    }

    def "/registercampaign rest endpoint should throw error when mandatory parameter 'campaignDescriotion' is not passed"() {
        given:
        final Map request = [
                orgnisationName : 'Test Org',
                campaignTitle   : 'Test title',
                taggedCategories: ['Finance', 'categories']
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/registercampaign").contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == HttpStatus.SC_BAD_REQUEST
        and:
        0 * organisationCampaignService.registerCampaign(_)
    }

    def "/registercampaign rest endpoint should throw error when mandatory parameter 'taggedCategories' is not passed"() {
        given:
        final Map request = [
                orgnisationName    : 'Test Org',
                campaignTitle      : 'Test title',
                campaignDescription: 'Test description',
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/registercampaign").contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == HttpStatus.SC_BAD_REQUEST
        and:
        0 * organisationCampaignService.registerCampaign(_)
    }


    @TestConfiguration
    static class StubConfig {
        final DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        OrganisationCampaignService organisationCampaignService() {
            return detachedMockFactory.Mock(OrganisationCampaignService.class)
        }

    }

}