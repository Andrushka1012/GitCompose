package com.example.gitcompose

import android.os.Parcelable
import androidx.navigation.NavController
import com.example.gitcompose.models.domainModels.RepositoryModel
import com.example.gitcompose.utils.ArgsKeeper
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@EntryPoint
@InstallIn(SingletonComponent::class)
sealed class Routes(val route: String, private val args: Parcelable? = null) {
    object Search : Routes(route = "Search")
    class RepositoryDetails(repo: RepositoryModel) : Routes(route = "RepositoryDetails")

    @Inject
    lateinit var argsKeeper: ArgsKeeper

    fun navigate(navController: NavController) {
        args?.let { argsKeeper.putParcelable(ARGS_KEY, args) }

        navController.navigate(route)
    }

    companion object {
        const val ARGS_KEY = "ARGS_KEY"
    }
}