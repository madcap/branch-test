package com.example.branchtest.controller

import spock.lang.Specification
import com.example.branchtest.model.github.GithubResponse
import com.example.branchtest.model.github.Repository
import com.example.branchtest.model.github.User
import com.example.branchtest.service.github.GithubClient

class GithubControllerSpec extends Specification {

    private static final String USERNAME = 'user-foo'

    private GithubClient mockGithubClient = Mock()

    private GithubController controller = new GithubController(githubClient: mockGithubClient)

    private User user = new User(
        login: 'login',
        name: 'name',
        avatarUrl: 'avatarUrl',
        location: 'location',
        email: 'email',
        url: 'url',
        createDateTimeStamp: '2011-03-01T20:47:34Z',
    )

    private List<Repository> repositories = [
        new Repository(
            name: 'repo1',
            url: 'url1'
        )]

    private GithubResponse expectedGithubResponse = new GithubResponse(
        username: 'login',
        displayName: 'name',
        avatar: 'avatarUrl',
        location: 'location',
        email: 'email',
        url: 'url',
        createDateTime: '2011-03-01 20:47:34',
        repos: [
            new Repository(
                name: 'repo1',
                url: 'url1'
            ),
        ],
        )

    def 'test - getGithubUserAndRepos'() {
        when:
        def result = controller.getGithubUserAndRepos(USERNAME)

        then:
        result == expectedGithubResponse

        and:
        1 * mockGithubClient.getUser(USERNAME) >> user
        1 * mockGithubClient.getRepositories(USERNAME) >> repositories
        0 * _
    }

    def 'test - getGithubUserAndRepos - missing create date'() {
        given:
        user.createDateTimeStamp = null
        expectedGithubResponse.createDateTime = null

        when:
        def result = controller.getGithubUserAndRepos(USERNAME)

        then:
        result == expectedGithubResponse

        and:
        1 * mockGithubClient.getUser(USERNAME) >> user
        1 * mockGithubClient.getRepositories(USERNAME) >> repositories
        0 * _
    }

}
