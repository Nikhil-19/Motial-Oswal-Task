package com.nikhil.motialoswaltask.main

import android.app.Application
import android.content.SharedPreferences
import com.facebook.stetho.Stetho
import com.nikhil.motialoswaltask.data.local.db.DatabaseService
import com.nikhil.motialoswaltask.data.local.prefs.RepositoryDataPreferences
import com.nikhil.motialoswaltask.data.remote.NetworkService
import com.nikhil.motialoswaltask.utility.Constants
import com.redmango.couroutinespractise.apicoroutine.data.remote.Networking

class MotilalTaskApp:Application() {
    lateinit var databaseService:DatabaseService
    lateinit var networkService: NetworkService
    lateinit var preferences: SharedPreferences
    lateinit var repositoryDataPreferences: RepositoryDataPreferences

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
         databaseService = DatabaseService.getDatabaseService(applicationContext)
         networkService = Networking.create()
         preferences=getSharedPreferences(Constants.SHARED_PREFERNCE_NAME, MODE_PRIVATE)
         repositoryDataPreferences= RepositoryDataPreferences(preferences)
    }
}