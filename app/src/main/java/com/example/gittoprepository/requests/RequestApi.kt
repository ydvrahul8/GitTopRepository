package com.example.gittoprepository.requests

import com.example.gittoprepository.models.Contributor
import com.example.gittoprepository.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RequestApi {

    @GET("repositories")
    fun getRepositories(): Call<MutableList<Repository>>

    @GET("repos/{repo_name}/contributors")
    fun getContributors(
        @Path(value = "repo_name", encoded = true) name: String,
        @Query("page") pageNo: Int,
        @Query("access_token") accessToken: String
    ): Call<MutableList<Contributor>>
}