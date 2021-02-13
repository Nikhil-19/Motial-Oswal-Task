package com.nikhil.motialoswaltask.data.local.db.entity

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide

@Entity(tableName = "repository_details")
data class RepositoryEntity(
        @PrimaryKey(autoGenerate = true)
        val _id: Long?,
        @ColumnInfo(name = "created_at")
        var createdAt: String,
        @ColumnInfo(name = "default_branch")
        var defaultBranch: String,

        @ColumnInfo(name = "deployments_url")
        var deploymentsUrl: String,

        var description: String?,
        var disabled: Boolean,

        @ColumnInfo(name = "forks_count")
        var forksCount: Int,

        @ColumnInfo(name = "full_name")
        var fullName: String,

        @ColumnInfo(name = "git_url")
        var gitUrl: String,

        @ColumnInfo(name = "has_downloads")
        var hasDownloads: Boolean,

        @ColumnInfo(name = "has_issues")
        var hasIssues: Boolean,

        @ColumnInfo(name = "has_pages")
        var hasPages: Boolean,

        @ColumnInfo(name = "has_projects")
        var hasProjects: Boolean,

        @ColumnInfo(name = "has_wiki")
        var hasWiki: Boolean,

        @ColumnInfo(name = "hooks_url")
        var hooksUrl: String,

        @ColumnInfo(name = "html_url")
        var htmlUrl: String,

        var id: Int,
        var language: String?,
        var name: String,
        @ColumnInfo(name = "node_id")
        var nodeId: String,

        @ColumnInfo(name = "open_issues")
        var openIssues: Int,

        @ColumnInfo(name = "open_issues_count")
        var openIssuesCount: Int,

        @Embedded val ownerInfo: OwnerInfo,

        @ColumnInfo(name = "is_private")
        var isPrivate: Boolean,

        @ColumnInfo(name = "score")
        var score: Double,

        @ColumnInfo(name = "size")
        var size: Int,

        @ColumnInfo(name = "ssh_url")
        var sshUrl: String,

        @ColumnInfo(name = "stargazers_count")
        var stargazersCount: Int,

        @ColumnInfo(name = "svn_url")
        var svnUrl: String,

        @ColumnInfo(name = "updated_at")
        var updatedAt: String,

        @ColumnInfo(name = "url")
        var url: String,

        @ColumnInfo(name = "watchers")
        var watchers: Int,

        @ColumnInfo(name = "watchers_count")
        var watchersCount: Int

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt() ,
            parcel.readInt(),
            parcel.readParcelable(OwnerInfo.javaClass.classLoader)!!,
            parcel.readByte() != 0.toByte(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readInt()) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(_id)
        parcel.writeString(createdAt)
        parcel.writeString(defaultBranch)
        parcel.writeString(deploymentsUrl)
        parcel.writeString(description)
        parcel.writeByte(if (disabled) 1 else 0)
        parcel.writeInt(forksCount)
        parcel.writeString(fullName)
        parcel.writeString(gitUrl)
        parcel.writeByte(if (hasDownloads) 1 else 0)
        parcel.writeByte(if (hasIssues) 1 else 0)
        parcel.writeByte(if (hasPages) 1 else 0)
        parcel.writeByte(if (hasProjects) 1 else 0)
        parcel.writeByte(if (hasWiki) 1 else 0)
        parcel.writeString(hooksUrl)
        parcel.writeString(htmlUrl)
        parcel.writeInt(id)
        parcel.writeString(language)
        parcel.writeString(name)
        parcel.writeString(nodeId)
        parcel.writeInt(openIssues)
        parcel.writeInt(openIssuesCount)
        parcel.writeParcelable(ownerInfo,flags)
        parcel.writeByte(if (isPrivate) 1 else 0)
        parcel.writeDouble(score)
        parcel.writeInt(size)
        parcel.writeString(sshUrl)
        parcel.writeInt(stargazersCount)
        parcel.writeString(svnUrl)
        parcel.writeString(updatedAt)
        parcel.writeString(url)
        parcel.writeInt(watchers)
        parcel.writeInt(watchersCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryEntity> {
        override fun createFromParcel(parcel: Parcel): RepositoryEntity {
            return RepositoryEntity(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryEntity?> {
            return arrayOfNulls(size)
        }

        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                    .load(imageUrl)
                    .into(view)
        }
    }
}

data class OwnerInfo(
        @ColumnInfo(name = "avatar_url")
        var avatarUrl: String,
        @ColumnInfo(name = "gravatar_id")
        var gravatarId: String,
        @ColumnInfo(name = "owner_html_url")
        var htmlUrl: String,
        var login: String,
        @ColumnInfo(name = "owner_node_id")
        var nodeId: String,
        @ColumnInfo(name = "repos_url")
        var reposUrl: String,
        @ColumnInfo(name = "site_admin")
        var siteAdmin: Boolean,
        @ColumnInfo(name = "type")
        var type: String,
        @ColumnInfo(name = "owner_url")
        var url: String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()?:"",
            parcel.readString()?:"",
            parcel.readString()?:"",
            parcel.readString()?:"",
            parcel.readString()?:"",
            parcel.readString()?:"",
            parcel.readByte() != 0.toByte(),
            parcel.readString()?:"",
            parcel.readString()?:"") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatarUrl)
        parcel.writeString(gravatarId)
        parcel.writeString(htmlUrl)
        parcel.writeString(login)
        parcel.writeString(nodeId)
        parcel.writeString(reposUrl)
        parcel.writeByte(if (siteAdmin) 1 else 0)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OwnerInfo> {
        override fun createFromParcel(parcel: Parcel): OwnerInfo {
            return OwnerInfo(parcel)
        }

        override fun newArray(size: Int): Array<OwnerInfo?> {
            return arrayOfNulls(size)
        }
    }
}