package com.example.gitcompose.screens.home.repoDetails

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gitcompose.models.domainModels.RepositoryModel

@Composable
fun RepositoryDetailsBody(args: RepositoryModel, navController: NavHostController) {
    Log.d("qwe", args.name)
}