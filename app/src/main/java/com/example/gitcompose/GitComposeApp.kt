package com.example.gitcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gitcompose.models.domainModels.RepositoryModel
import com.example.gitcompose.screens.home.repoDetails.RepositoryDetailsBody
import com.example.gitcompose.screens.home.search.SearchBody
import com.example.gitcompose.ui.theme.GitComposeTheme
import com.example.gitcompose.utils.ArgsKeeper

@Composable
fun GitComposeApp(argsKeeper: ArgsKeeper) {

    val navController = rememberNavController()

    GitComposeTheme {
        NavHost(navController = navController, startDestination = Routes.Search.route) {
            composable(route = Routes.Search.route) {
                SearchBody(navController)
            }
            composable(route = "RepositoryDetails") {
                val args = argsKeeper.getParcelable<RepositoryModel>(Routes.ARGS_KEY)
                RepositoryDetailsBody(args!!, navController)
            }
        }
    }
}