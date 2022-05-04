package com.example.gitcompose.screens.home.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.gitcompose.features.github.GitHubRepository
import com.example.gitcompose.infrastructure.DEBOUNCE_PERIOD
import com.example.gitcompose.infrastructure.DEFAULT_PAGE_SIZE
import com.example.gitcompose.infrastructure.paging.PagingDataSource
import com.example.gitcompose.models.Page
import com.example.gitcompose.models.domainModels.RepositoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository,
    private val factory: PagingDataSource.Factory<RepositoryModel>,
) : ViewModel() {
    var searchQueryData = MutableStateFlow("qwe")

    var qlazySearchItems: LazyPagingItems<RepositoryModel>? = null

    init {
        viewModelScope.launch {
            searchQueryData
            searchQueryData.debounce(Duration.milliseconds(DEBOUNCE_PERIOD)).collect {
                qlazySearchItems?.refresh()
            }
        }
    }

    val pagingData: Flow<PagingData<RepositoryModel>> =
        Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
            factory.create() { page: Int, pageSize: Int ->
                val query = requireNotNull(searchQueryData.value)

                if (query.isNotBlank()) {
                    gitHubRepository.search(requireNotNull(searchQueryData.value), page, pageSize)
                } else Page.emptyPage()
            }
        }.flow.cachedIn(viewModelScope)

    fun onSearchQueryChanged(value: String, lazySearchItems: LazyPagingItems<RepositoryModel>) =
        viewModelScope.launch {
            searchQueryData.emit(value)
            qlazySearchItems = lazySearchItems
        }
}