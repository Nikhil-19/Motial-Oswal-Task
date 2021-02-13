package com.nikhil.motialoswaltask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.motialoswaltask.R
import com.nikhil.motialoswaltask.data.local.db.entity.RepositoryEntity
import com.nikhil.motialoswaltask.databinding.RepositoryHolderBinding

class RepositoriesAdapter(private val repositoryEntityList: List<RepositoryEntity>): RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>() {
    private var onItemClickListener:OnItemClickListener?=null

   inner class RepositoryViewHolder(private val holderBinding: RepositoryHolderBinding) :RecyclerView.ViewHolder(holderBinding.root){

       fun bindRepositoryItem(argRepositoryEntity: RepositoryEntity){
            holderBinding.repositoryItem=argRepositoryEntity
            holderBinding.imageUrl=argRepositoryEntity.ownerInfo.avatarUrl
            holderBinding.executePendingBindings()
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
       val repositoryHolderBinding:RepositoryHolderBinding=DataBindingUtil.inflate (layoutInflater,R.layout.repository_holder,parent,false)
       return RepositoryViewHolder(repositoryHolderBinding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
         val repositoryEntity=repositoryEntityList.get(position)
         holder.bindRepositoryItem(repositoryEntity)
         holder.itemView.setOnClickListener {
             onItemClickListener?.onItemClick(repositoryEntity)
         }
    }

    override fun getItemCount(): Int {
      return repositoryEntityList.size
    }

    interface OnItemClickListener{
        fun onItemClick(repositoryEntity: RepositoryEntity)
    }

    fun setOnItemClickListener(param: OnItemClickListener) {
           onItemClickListener=param
    }

}