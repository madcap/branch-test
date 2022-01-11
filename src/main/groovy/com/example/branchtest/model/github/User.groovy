package com.example.branchtest.model.github

import groovy.transform.Canonical
import groovy.transform.ToString

import com.fasterxml.jackson.annotation.JsonProperty

@Canonical
@ToString(includePackage=false, includeNames=true)
class User {
    // not all the fields on a github users, just the ones we care about
    // curl --location --request GET 'https://api.github.com/users/octocat'

    String login
    String name
    @JsonProperty('avatar_url')
    String avatarUrl
    String location
    String email
    String url
    @JsonProperty('created_at')
    String createDateTimeStamp

}
