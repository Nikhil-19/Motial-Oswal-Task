package com.nikhil.motialoswaltask.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nikhil.motialoswaltask.data.remote.NetworkService
import com.nikhil.motialoswaltask.data.local.db.DatabaseService
import com.nikhil.motialoswaltask.data.local.db.entity.OwnerInfo
import com.nikhil.motialoswaltask.data.local.db.entity.RepositoryEntity
import com.nikhil.motialoswaltask.data.local.prefs.RepositoryDataPreferences
import com.nikhil.motialoswaltask.data.remote.repository.DataRepository
import com.nikhil.motialoswaltask.data.remote.response.Item
import com.nikhil.motialoswaltask.data.remote.response.RepositoryResponse
import com.redmango.couroutinespractise.apicoroutine.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.lang.Exception

class MainViewModel(
   private val repositoryDataPreferences: RepositoryDataPreferences,
    private val databaseService: DatabaseService
) : ViewModel() {


    fun getRepositories() = liveData<Resource<List<RepositoryEntity>>>(Dispatchers.IO) {
        emit(Resource.loading(null,"Fetching Details..."))
        try {
            val repositoryEntityList=databaseService.getDAO().getRepositoryDetails()
            if (repositoryEntityList.isNotEmpty()) {
                emit(Resource.success(repositoryEntityList))
            }
            else {
                emit(Resource.error(null, "Data Not Inserted"))
            }
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }
}