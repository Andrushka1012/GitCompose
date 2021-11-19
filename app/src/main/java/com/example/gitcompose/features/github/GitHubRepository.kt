package com.example.gitcompose.features.github

import android.util.Base64
import com.example.gitcompose.infrastructure.Mapper
import com.example.gitcompose.infrastructure.networking.GitHubService
import com.example.gitcompose.models.Page
import com.example.gitcompose.models.domainModels.RepositoryModel
import retrofit2.HttpException
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val gitHubService: GitHubService, private val mapper: Mapper) {
    suspend fun search(searchQuery: String, page: Int, pageSize: Int): Page<RepositoryModel> {
        val pagedData = gitHubService.searchRepositories(
            query = searchQuery,
            page = page,
            pageSize = pageSize
        )

        return mapper.map(
            pagedData = pagedData,
            page = page,
            pageSize = pageSize
        )
    }

    suspend fun getReadMeForRepository(owner: String, name: String): String {
        val response = gitHubService.fetchReadMeForRepository(
            owner = owner,
            repositoryName = name
        )

        if (response.isSuccessful) {
            val data = Base64.decode(response.body()?.content, Base64.DEFAULT)
            return String(data, Charsets.UTF_8)
        } else throw HttpException(response)
    }

}