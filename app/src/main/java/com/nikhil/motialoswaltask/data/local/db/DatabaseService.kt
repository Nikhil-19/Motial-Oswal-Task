package com.nikhil.motialoswaltask.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikhil.motialoswaltask.data.local.db.dao.RepositoryDAO
import com.nikhil.motialoswaltask.data.local.db.entity.RepositoryEntity

@Database(entities = [RepositoryEntity::class],version = 1,exportSchema = false)
abstract class DatabaseService:RoomDatabase() {

   companion object{
      private lateinit var databaseService: DatabaseService
       fun getDatabaseService(argContext: Context):DatabaseService{
          databaseService= Room.databaseBuilder(
               argContext,
               DatabaseService::class.java,
               "repository_db"
           ).build()
           return databaseService
       }
   }
   abstract fun getDAO():RepositoryDAO



}