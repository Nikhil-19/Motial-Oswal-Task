package com.nikhil.motialoswaltask.data.remote.response


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("default_branch")
    var defaultBranch: String,
    @SerializedName("deployments_url")
    var deploymentsUrl: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("disabled")
    var disabled: Boolean,
    @SerializedName("forks_count")
    var forksCount: Int,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("git_url")
    var gitUrl: String,
    @SerializedName("has_downloads")
    var hasDownloads: Boolean,
    @SerializedName("has_issues")
    var hasIssues: Boolean,
    @SerializedName("has_pages")
    var hasPages: Boolean,
    @SerializedName("has_projects")
    var hasProjects: Boolean,
    @SerializedName("has_wiki")
    var hasWiki: Boolean,
    @SerializedName("hooks_url")
    var hooksUrl: String,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("language")
    var language: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("open_issues")
    var openIssues: Int,
    @SerializedName("open_issues_count")
    var openIssuesCount: Int,
    @SerializedName("owner")
    var owner: Owner,
    @SerializedName("private")
    var `private`: Boolean,
    @SerializedName("score")
    var score: Double,
    @SerializedName("size")
    var size: Int,
    @SerializedName("ssh_url")
    var sshUrl: String,
    @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @SerializedName("svn_url")
    var svnUrl: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("watchers")
    var watchers: Int,
    @SerializedName("watchers_count")
    var watchersCount: Int
)