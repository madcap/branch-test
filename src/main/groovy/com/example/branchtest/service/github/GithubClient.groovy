package com.example.branchtest.service.github

import com.example.branchtest.model.github.Repository
import com.example.branchtest.model.github.User

interface GithubClient {

    User getUser(String username) throws NotFoundException, OverQuotaException

    List<Repository> getRepositories(String username) throws NotFoundException, OverQuotaException

}
