package com.nikhil.motialoswaltask.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.nikhil.motialoswaltask.R
import com.nikhil.motialoswaltask.backgroundservices.FetchRepositoriesWorker
import com.nikhil.motialoswaltask.data.local.db.DatabaseService
import com.nikhil.motialoswaltask.data.local.db.entity.RepositoryEntity
import com.nikhil.motialoswaltask.data.local.prefs.RepositoryDataPreferences
import com.nikhil.motialoswaltask.databinding.ActivityMainBinding
import com.nikhil.motialoswaltask.main.MotilalTaskApp
import com.nikhil.motialoswaltask.ui.adapters.RepositoriesAdapter
import com.nikhil.motialoswaltask.ui.repositorydetails.RepositoryDetailsActivity
import com.nikhil.motialoswaltask.utility.Constants
import com.redmango.couroutinespractise.apicoroutine.Status
import com.redmango.couroutinespractise.apicoroutine.data.remote.Networking
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var workManager: WorkManager;
    private lateinit var repositoriesAdapter: RepositoriesAdapter
    private lateinit var repositoryEntityList: MutableList<RepositoryEntity>
    private lateinit var binding: ActivityMainBinding;
    private lateinit var repositoryDataPreferences: RepositoryDataPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val applicationInstance: MotilalTaskApp = application as MotilalTaskApp
        repositoryDataPreferences = applicationInstance.repositoryDataPreferences
        mainViewModelFactory =
            MainViewModelFactory(repositoryDataPreferences, applicationInstance.databaseService)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        initiateWorkManger()
        setAdapters();
        checkIfDataisLoadedForFirstTime()
    }

    private fun checkIfDataisLoadedForFirstTime() {
        if (repositoryDataPreferences.getPageCount() < 1)
        {
            binding.progressBar2.visibility=View.VISIBLE
        }
        else{
            setObservers()
        }
    }

    private fun setAdapters() {
        repositoryEntityList = mutableListOf()
        repositoriesAdapter = RepositoriesAdapter(repositoryEntityList)
        binding.rvRepository.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.rvRepository.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = repositoriesAdapter
        }

        repositoriesAdapter.setOnItemClickListener(object :
            RepositoriesAdapter.OnItemClickListener {
            override fun onItemClick(repositoryEntity: RepositoryEntity) {
                Log.d("On Item Click", repositoryEntity.fullName)
                navigateToRepositoryDetails(repositoryEntity);
            }

        })

    }

    private fun navigateToRepositoryDetails(repositoryEntity: RepositoryEntity) {

        val bundle = Bundle().apply {
            putParcelable(Constants.INTENT_KEY_REPOSITORY_DETAIL, repositoryEntity)
        }
        Intent(this, RepositoryDetailsActivity::class.java).apply {
            putExtra(Constants.INTENT_KEY_BUNDLE, bundle)
            startActivity(this)
        }

    }

    private fun updateRepositoryDetails(data: List<RepositoryEntity>) {
        Log.d("Main Activity ", "updateRepositoryDetails")
        if (repositoryEntityList.isNotEmpty()) {
            repositoryEntityList.clear()
        }
        repositoryEntityList.addAll(data)
        repositoriesAdapter.notifyDataSetChanged()
    }


    private fun setObservers() {
        viewModel.getRepositories().observe(this, {
            it?.let {

                when (it.status) {
                    Status.SUCCESS -> {
                        binding.progressBar2.visibility = View.GONE
                        Log.d("MainActivity", it.data?.get(0)?.fullName)
                        if (it.data != null) {
                            updateRepositoryDetails(it.data)
                        } else {
                            toast(getString(R.string.error_msg))
                        }
                    }
                    Status.LOADING -> {
                        Log.d("MainActivity", "Data is Loading")
                        binding.progressBar2.visibility = View.VISIBLE
                    }

                    Status.ERROR -> {
                        binding.progressBar2.visibility = View.GONE
                        Log.d("MainActivity", it?.message ?: "Error Message")
                        toast(it?.message ?: getString(R.string.error_msg))
                    }
                    else -> {
                        binding.progressBar2.visibility = View.GONE
                        toast(getString(R.string.error_msg))

                    }
                }
            }
        })
    }

    private fun initiateWorkManger() {
        workManager = WorkManager.getInstance(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequest.Builder(FetchRepositoriesWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        workManager.enqueueUniquePeriodicWork(
            "Work Mangaer",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )

        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this, {

            if (it != null) {
                when (it.state) {
                    WorkInfo.State.ENQUEUED -> {
                        Log.d("Main Activity", "Enqueue State")
                        setObservers()
                    }
                    WorkInfo.State.RUNNING -> Log.d("Main Activity", "Running State")
                    WorkInfo.State.SUCCEEDED -> Log.d("Main Activity", "Scuceed State")
                    WorkInfo.State.FAILED -> Log.d("Main Activity", "Failed State")
                    WorkInfo.State.CANCELLED -> Log.d("Main Activity", "Cancelled State")
                }
            }

        })
    }

    private fun Context.toast(argMessage: String) {
        Toast.makeText(this, argMessage, Toast.LENGTH_SHORT).show()
    }


}