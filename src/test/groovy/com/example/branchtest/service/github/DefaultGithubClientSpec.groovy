package com.example.branchtest.service.github

import spock.lang.Specification

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate

import com.example.branchtest.model.github.User

class DefaultGithubClientSpec extends Specification {

    private static final String USER_NAME = 'user-foo'

    private RestTemplate mockRestTemplate = Mock()

    private DefaultGithubClient client = new DefaultGithubClient(restTemplate: mockRestTemplate)

    private User user = new User(
        name: 'name',
    )

    private ResponseEntity responseEntity = new ResponseEntity(user, HttpStatus.OK)


    def 'test - getUser - happy path'() {
        when:
        def result = client.getUser(USER_NAME)

        then:
        result == user

        and:
        1 * mockRestTemplate.exchange("/$USER_NAME", HttpMethod.GET, null, User) >> responseEntity
        0 * _
    }

    def 'test - getUser - over quota'() {
        given:
        def exception = new HttpStatusCodeException(HttpStatus.FORBIDDEN) {}

        when:
        client.getUser(USER_NAME)

        then:
        def e = thrown(NotFoundException)
        e.cause == exception
        e.message == 'access is over quota'

        and:
        1 * mockRestTemplate.exchange("/$USER_NAME", HttpMethod.GET, null, User) >> { throw exception }
        0 * _
    }


    // TODO - remaining tests here
}
