package com.example.branchtest.model.github

import groovy.transform.Canonical
import groovy.transform.ToString
import com.fasterxml.jackson.annotation.JsonProperty

@Canonical
@ToString(includePackage=false, includeNames=true)
class GithubResponse {
    @JsonProperty("user_name")
    String username

    @JsonProperty("display_name")
    String displayName

    String avatar

    @JsonProperty("geo_location")
    String location

    String email

    String url

    @JsonProperty("created_at")
    String createDateTime

    List<Repository> repos = []
}
