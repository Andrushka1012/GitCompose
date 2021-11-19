package com.example.gitcompose.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.gitcompose.features.github.GitHubRepository
import com.example.gitcompose.infrastructure.DEFAULT_PAGE_SIZE
import com.example.gitcompose.infrastructure.paging.PagingDataSource
import com.example.gitcompose.models.Page
import com.example.gitcompose.models.domainModels.RepositoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository,
    private val factory: PagingDataSource.Factory<RepositoryModel>,
) : ViewModel() {
    var searchQueryData = MutableLiveData("")
    private var currentSearchQuery: String? = null

    val pagingData: StateFlow<PagingData<RepositoryModel>> =
        Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
            factory.create() { page: Int, pageSize: Int ->
                val query = requireNotNull(searchQueryData.value)

                if (query.isNotBlank()) {
                    currentSearchQuery = query
                    gitHubRepository.search(requireNotNull(searchQueryData.value), page, pageSize)
                } else Page.emptyPage()
            }
        }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}