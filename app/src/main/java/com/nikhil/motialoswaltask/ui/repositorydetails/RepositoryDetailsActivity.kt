package com.nikhil.motialoswaltask.ui.repositorydetails

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.nikhil.motialoswaltask.R
import com.nikhil.motialoswaltask.data.local.db.entity.RepositoryEntity
import com.nikhil.motialoswaltask.databinding.ActivityMainBinding
import com.nikhil.motialoswaltask.databinding.ActivityRepositoryDetailsBinding
import com.nikhil.motialoswaltask.utility.Constants

class RepositoryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepositoryDetailsBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_repository_details)
        val bundle=intent.getBundleExtra(Constants.INTENT_KEY_BUNDLE)
        val repo:RepositoryEntity?=bundle.getParcelable<RepositoryEntity>(Constants.INTENT_KEY_REPOSITORY_DETAIL)

        if(repo!=null)
        {
            binding.repositoryItem=repo
            binding.imageUrl=repo.ownerInfo.avatarUrl
        }


        binding.btnVisit.setOnClickListener {
            try{
                val uri:Uri=Uri.parse(repo?.htmlUrl)
                startActivity(Intent(Intent.ACTION_VIEW,uri))
            }
            catch (e:Exception)
            {

            }
        }

    }
}