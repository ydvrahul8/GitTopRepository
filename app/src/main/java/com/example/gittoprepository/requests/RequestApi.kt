package com.example.gittoprepository.requests

import com.example.gittoprepository.models.Repository
import retrofit2.Call
import retrofit2.http.GET


interface RequestApi {

    @GET("repositories")
    fun getRepositories(): Call<MutableList<Repository>>

}