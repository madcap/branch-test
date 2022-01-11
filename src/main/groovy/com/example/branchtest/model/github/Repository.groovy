package com.example.branchtest.model.github

import groovy.transform.Canonical
import groovy.transform.ToString

import com.fasterxml.jackson.annotation.JsonProperty

@Canonical
@ToString(includePackage=false, includeNames=true)
class Repository {
    // not all the fields on a github users, just the ones we care about
    // curl --location --request GET 'https://api.github.com/users/octocat/repos'

    String name
    String url
}
