package com.patil.retrofit.coroutines.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getAcromine() = apiService.getAcromine()
}