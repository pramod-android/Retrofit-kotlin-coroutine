package com.patil.retrofit.coroutines.data.api

import com.patil.retrofit.coroutines.data.model.Acromine
import retrofit2.http.GET

interface ApiService {

    @GET("dictionary.py?sf=HMM")
    suspend fun getAcromine(): Acromine
}