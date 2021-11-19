package com.example.gitcompose.infrastructure.networking

import com.example.gitcompose.models.apiDto.FileDto
import com.example.gitcompose.models.apiDto.RepositoriesPagedDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query(value = "q", encoded = true) query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): RepositoriesPagedDto

    @GET("repos/{owner}/{repo}/readme")
    suspend fun fetchReadMeForRepository(
        @Path("owner") owner: String,
        @Path("repo") repositoryName: String
    ): Response<FileDto>

}