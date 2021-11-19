package com.example.gitcompose.screens.repoDetails

import androidx.lifecycle.ViewModel
import com.example.gitcompose.features.github.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

}