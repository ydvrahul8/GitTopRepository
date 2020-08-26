package com.example.gittoprepository.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gittoprepository.models.Repository
import com.example.gittoprepository.requests.RequestApi
import com.example.gittoprepository.requests.RequestApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllRepositoriesViewModel : ViewModel() {

    private val TAG = "AllRepositoriesViewMode"

    private val _apiInterface = RequestApiClient.getClient()?.create(RequestApi::class.java)

    private val _allRepositories = MutableLiveData<MutableList<Repository>>()
    val allRepository: LiveData<MutableList<Repository>> get() = _allRepositories

    fun getAllRepositories() {
        val call: Call<MutableList<Repository>>? = _apiInterface?.getRepositories()

        call?.enqueue(object : Callback<MutableList<Repository>> {
            override fun onFailure(call: Call<MutableList<Repository>>, t: Throwable) {
                t.printStackTrace()
                _allRepositories.value = null
                call.cancel()
            }

            override fun onResponse(
                call: Call<MutableList<Repository>>,
                response: Response<MutableList<Repository>>
            ) {
                Log.e(TAG, "run: success data is ${response.body()}")
                val list: MutableList<Repository> = response.body() as MutableList<Repository>
                _allRepositories.value = list
            }
        })
    }
}