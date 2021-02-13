package com.nikhil.motialoswaltask.data.remote.response


import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var items: List<Item>,
    @SerializedName("total_count")
    var totalCount: Int
)