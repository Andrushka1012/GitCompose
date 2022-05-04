package com.example.gitcompose.screens.home.search


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gitcompose.Routes
import com.example.gitcompose.screens.home.search.items.RepositoryItem


@Composable
fun SearchBody(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        content = {
            SearchForm(navController = navController, searchViewModel = searchViewModel)
        },
    )
}

@Composable
fun SearchForm(
    navController: NavHostController,
    searchViewModel: SearchViewModel,
) {
    val lazySearchItems = searchViewModel.pagingData.collectAsLazyPagingItems()
    val searchQueryState = searchViewModel.searchQueryData.collectAsState()

    Column() {
        TextField(
            value = searchQueryState.value,
            onValueChange = {
                searchViewModel.onSearchQueryChanged(it, lazySearchItems)
            },
            modifier = Modifier.fillMaxWidth(),
        )
        LazyColumn {
            items(lazySearchItems.itemCount) { index ->
                lazySearchItems[index]?.let {
                    RepositoryItem(
                        repository = it,
                        onItemSelected = { repository ->
                            Routes.RepositoryDetails(repository).navigate(navController)
                        },
                    )
                }
            }

            lazySearchItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = lazySearchItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = lazySearchItems.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}