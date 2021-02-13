package com.nikhil.motialoswaltask.data.remote

import com.nikhil.motialoswaltask.data.remote.response.RepositoryResponse
import com.redmango.couroutinespractise.apicoroutine.data.remote.EndPoints
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

  @GET(EndPoints.REPOSITORIES)
  suspend fun fetchRepositories(
    @Query("page") pageId:Int,
    @Query("q") repositoryType:String
   ):RepositoryResponse

}