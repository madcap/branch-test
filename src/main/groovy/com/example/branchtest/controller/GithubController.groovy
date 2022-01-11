package com.example.branchtest.controller

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import com.example.branchtest.model.github.Repository
import com.example.branchtest.model.github.User
import com.example.branchtest.model.github.GithubResponse
import com.example.branchtest.service.github.GithubClient

@RestController
@RequestMapping("/github/")
class GithubController {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    @Autowired
    private GithubClient githubClient

    @RequestMapping(value = "/v1/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody GithubResponse getGithubUserAndRepos(
        @PathVariable("userId") String userId
    ) {
        // TODO - catch not found or over quota, use custom status code instead of returning 500 (controller advice)
        User user = githubClient.getUser(userId)
        List<Repository> repositories = githubClient.getRepositories(userId)

        GithubResponse response = new GithubResponse()
        response.username = user.login
        response.displayName = user.name
        response.avatar = user.avatarUrl
        response.location = user.location
        response.email = user.email
        response.url = user.url
        if(user.createDateTimeStamp) {
            // format date time to client desired format
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(user.createDateTimeStamp)
            response.createDateTime = zonedDateTime.format(DATE_TIME_FORMATTER)
        }
        response.repos = repositories
        return response
    }

}
