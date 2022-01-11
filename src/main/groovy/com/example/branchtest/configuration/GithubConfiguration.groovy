package com.example.branchtest.configuration

import java.time.Duration
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class GithubConfiguration {

    @Bean(name = 'githubRestTemplate')
    RestTemplate getGithubRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .rootUri('https://api.github.com/users')
            .setReadTimeout(Duration.ofSeconds(10))
            .setConnectTimeout(Duration.ofSeconds(10))
            .build()
    }

}
