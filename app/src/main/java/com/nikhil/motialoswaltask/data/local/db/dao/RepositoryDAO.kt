package com.nikhil.motialoswaltask.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikhil.motialoswaltask.data.local.db.entity.RepositoryEntity

@Dao
interface RepositoryDAO {

    @Insert
    suspend fun insertAll(repositoryList: List<RepositoryEntity>)


    @Insert
    suspend fun insert(argRepositoryEntity:RepositoryEntity):Long

    @Query("select * from repository_details")
    suspend fun getRepositoryDetails():List<RepositoryEntity>


}