package com.example.branchtest.service.github

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import com.example.branchtest.model.github.Repository
import com.example.branchtest.model.github.User

@Component
class DefaultGithubClient implements GithubClient{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGithubClient)

    @Qualifier("githubRestTemplate")
    @Autowired
    private RestTemplate restTemplate

    // TODO - consolidate duplicate code around exception handling

    @Override
    User getUser(final String username) throws NotFoundException, OverQuotaException {
        try {
            ResponseEntity<User> response = restTemplate.exchange("/$username", HttpMethod.GET, null, User)
            return response.body
        }
        catch(HttpStatusCodeException e) {
            if(e.statusCode == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("github user not found", e)
            }
            if(e.statusCode == HttpStatus.FORBIDDEN) {
                LOGGER.info("github access currently over quota")
                throw new NotFoundException("access is over quota", e)
            }
            LOGGER.error("unhandled error getting user {}", username)
            throw new RuntimeException("unhandled error getting github user", e)
        }
    }

    @Override
    List<Repository> getRepositories(final String username) throws NotFoundException, OverQuotaException {
        List<Repository> repos = []
        try {
            ResponseEntity<List> response = restTemplate.exchange("/$username/repos", HttpMethod.GET, null, List)
            // annoying handling because repos returns a raw list structure rather than an object
            // this could blow up in a lot of ways, with more time I'd have more robust handling here
            response.body.each { repo ->
                if(repo) {
                    repos << new Repository(name: repo['name'], url: repo['url'] )
                }
            }
        }
        catch(HttpStatusCodeException e) {
            if(e.statusCode == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("github user repos not found", e)
            }
            if(e.statusCode == HttpStatus.FORBIDDEN) {
                LOGGER.info("github access currently over quota")
                throw new NotFoundException("access is over quota", e)
            }
            LOGGER.error("unhandled error getting user repos for {}", username)
            throw new RuntimeException("unhandled error getting github user repos", e)
        }

        return repos
    }

}
