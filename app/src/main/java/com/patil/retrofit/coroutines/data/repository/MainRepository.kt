package com.patil.retrofit.coroutines.data.repository

import com.patil.retrofit.coroutines.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getAcromine() = apiHelper.getAcromine()
}