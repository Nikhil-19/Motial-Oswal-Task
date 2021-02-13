package com.nikhil.motialoswaltask.backgroundservices

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nikhil.motialoswaltask.data.local.db.DatabaseService
import com.nikhil.motialoswaltask.data.local.db.entity.OwnerInfo
import com.nikhil.motialoswaltask.data.local.db.entity.RepositoryEntity
import com.nikhil.motialoswaltask.data.remote.repository.DataRepository
import com.nikhil.motialoswaltask.data.remote.response.Item
import com.nikhil.motialoswaltask.utility.Constants
import com.redmango.couroutinespractise.apicoroutine.data.remote.Networking
import com.nikhil.motialoswaltask.data.local.prefs.RepositoryDataPreferences

class FetchRepositoriesWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val databaseService = DatabaseService.getDatabaseService(applicationContext)
    private val networkService = Networking.create()
    private val dataRepository = DataRepository(networkService)

    private  val preferences=context.getSharedPreferences(Constants.SHARED_PREFERNCE_NAME, MODE_PRIVATE)
    private val repositoryDataPreferences= RepositoryDataPreferences(preferences)

    override suspend fun doWork(): Result {
        return try {
            var pageCount:Int=repositoryDataPreferences.getPageCount()
            pageCount++
            Log.d("Worker Data ", "do Work")
            val repositoryResponse = dataRepository.fetchGitHubUsers(pageCount, "android")
            val itemList: List<Item> = repositoryResponse.items
            if (itemList.isNotEmpty()) {
                val repositoryEntityList: List<RepositoryEntity> = mapApiResponseToDb(itemList)
                databaseService.getDAO().insertAll(repositoryEntityList)
                repositoryDataPreferences.setPageCount(pageCount)
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Log.d("Work Manager", "Exception Occured ${e.localizedMessage}")
            Result.failure()
        }
    }


    private fun mapApiResponseToDb(argItem: List<Item>): List<RepositoryEntity> {
        val repositoryEntityList = mutableListOf<RepositoryEntity>()
        for (data in argItem) {
            repositoryEntityList.add(
                RepositoryEntity(
                    null,
                    data.createdAt,
                    data.defaultBranch,
                    data.deploymentsUrl,
                    data.description,
                    data.disabled,
                    data.forksCount,
                    data.fullName,
                    data.gitUrl,
                    data.hasDownloads,
                    data.hasIssues,
                    data.hasPages,
                    data.hasProjects,
                    data.hasWiki,
                    data.hooksUrl,
                    data.htmlUrl,
                    data.id,
                    data.language,
                    data.name,
                    data.nodeId,
                    data.openIssues,
                    data.openIssuesCount,
                    OwnerInfo(
                        data.owner.avatarUrl,
                        data.owner.gravatarId,
                        data.owner.htmlUrl,
                        data.owner.login,
                        data.owner.nodeId,
                        data.owner.reposUrl,
                        data.owner.siteAdmin,
                        data.owner.type,
                        data.owner.url
                    ),
                    data.private,
                    data.score,
                    data.size,
                    data.sshUrl,
                    data.stargazersCount,
                    data.svnUrl,
                    data.updatedAt,
                    data.url,
                    data.watchers,
                    data.watchersCount,
                )
            )
        }
        return repositoryEntityList
    }
}