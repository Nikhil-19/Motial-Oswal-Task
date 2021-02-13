package com.nikhil.motialoswaltask.data.local.prefs

import android.content.SharedPreferences

class RepositoryDataPreferences(val sharedPreferences: SharedPreferences) {

    companion object {
        val PREF_IS_DATA_SYNCED = "isDataSynced"
        val PAGE_NO = "pageNo"
    }

    fun getPageCount():Int=sharedPreferences.getInt(PAGE_NO,0)

    fun setPageCount(pageCount:Int)=sharedPreferences.edit().putInt(PAGE_NO,pageCount).apply()
}