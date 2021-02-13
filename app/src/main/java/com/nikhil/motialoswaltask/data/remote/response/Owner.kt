package com.nikhil.motialoswaltask.data.remote.response


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("gravatar_id")
    var gravatarId: String,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("login")
    var login: String,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("repos_url")
    var reposUrl: String,
    @SerializedName("site_admin")
    var siteAdmin: Boolean,
    @SerializedName("type")
    var type: String,
    @SerializedName("url")
    var url: String
)