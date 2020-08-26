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

class UserRepositoryViewModel : ViewModel() {

    private val TAG = "UserRepositoryViewModel"

    private val _apiInterface = RequestApiClient.getClient()?.create(RequestApi::class.java)

    private val _userRepositories = MutableLiveData<MutableList<Repository>>()
    val userRepository: LiveData<MutableList<Repository>> get() = _userRepositories

    fun getUserRepositories(name:String) {
        val call: Call<MutableList<Repository>>? = _apiInterface?.getUserRepositories(name)

        call?.enqueue(object : Callback<MutableList<Repository>> {
            override fun onFailure(call: Call<MutableList<Repository>>, t: Throwable) {
                t.printStackTrace()
                _userRepositories.value = null
                call.cancel()
            }

            override fun onResponse(
                call: Call<MutableList<Repository>>,
                response: Response<MutableList<Repository>>
            ) {
                Log.e(TAG, "run: success data is ${response.body()}")
                val list: MutableList<Repository> = response.body() as MutableList<Repository>
                _userRepositories.value = list
            }
        })
    }
}