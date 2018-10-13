package com.alert.news.web


import com.alert.news.service.UserService
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

@WebMvcTest(controllers = [UserRegistrationController])
class UserRegistrationControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private UserService userService

    def "user registration rest service returns status ok and success message when passed correct data"() {
        given:
        Map request = [
                mobileNumber          : '+919370912801',
                userName              : 'shivaji.pote',
                subscriptionCategories: ["Finance", "Banking"]
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post('/registeruser').contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == HttpStatus.SC_OK
        and:
        response.contentAsString == "Registration successful"
    }

    def "user registration rest service should throw error when mandatory parameter 'mobileNumber' is not passed"() {
        given:
        Map request = [
                mobileNumber          : '+919370912801',
                subscriptionCategories: ["Finance", "Banking"]
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post('/registeruser').contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == 400
    }

    def "user registration rest service should throw error when mandatory parameter 'user name' is not passed"() {
        given:
        Map request = [
                mobileNumber: '+919370912801',
                userName    : 'shivaji.pote',
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post('/registeruser').contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == 400
    }

    def "user registration rest service should throw error when mandatory parameter 'subscription categories' is not passed"() {
        given:
        Map request = [
                userName              : 'shivaji.pote',
                subscriptionCategories: ["Finance", "Banking"]
        ]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post('/registeruser').contentType(ContentType.APPLICATION_JSON.toString()).content(JsonOutput.toJson(request))).andReturn().response

        then:
        response.status == 400
    }

    @TestConfiguration
    static class StubConfig {
        final DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        UserService userService() {
            return detachedMockFactory.Stub(UserService)
        }
    }
}