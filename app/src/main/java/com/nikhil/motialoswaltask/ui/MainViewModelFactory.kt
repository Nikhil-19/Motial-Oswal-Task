package com.nikhil.motialoswaltask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.motialoswaltask.data.remote.NetworkService
import com.nikhil.motialoswaltask.data.local.db.DatabaseService
import com.nikhil.motialoswaltask.data.local.prefs.RepositoryDataPreferences
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    private val repositoryDataPreferences: RepositoryDataPreferences,
    private val databaseService: DatabaseService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repositoryDataPreferences, databaseService) as T
        }
        else {
          throw IllegalArgumentException("Unknown View Model")
        }
    }
}