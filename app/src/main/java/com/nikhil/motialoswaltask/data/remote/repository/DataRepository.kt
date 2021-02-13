package com.nikhil.motialoswaltask.data.remote.repository

import com.nikhil.motialoswaltask.data.remote.NetworkService

class DataRepository(val networkService: NetworkService) {


    suspend fun fetchGitHubUsers(argPageId: Int, repositoryType: String) =
        networkService.fetchRepositories(argPageId, repositoryType)

}