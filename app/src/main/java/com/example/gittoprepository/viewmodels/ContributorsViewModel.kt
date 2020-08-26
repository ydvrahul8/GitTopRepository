package com.example.gittoprepository.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gittoprepository.models.Contributor
import com.example.gittoprepository.requests.RequestApi
import com.example.gittoprepository.requests.RequestApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributorsViewModel : ViewModel() {
    private val TAG = "ContributorsViewModel"

    private val _apiInterface = RequestApiClient.getClient()?.create(RequestApi::class.java)

    private val _contributors = MutableLiveData<MutableList<Contributor>>()
    val contributors: LiveData<MutableList<Contributor>> get() = _contributors

    fun getContributors(repoName: String, pageNumber: Int, accessToken: String) {
        val call: Call<MutableList<Contributor>>? =
            _apiInterface?.getContributors(repoName, pageNumber, accessToken)

        call?.enqueue(object : Callback<MutableList<Contributor>> {
            override fun onFailure(call: Call<MutableList<Contributor>>, t: Throwable) {
                t.printStackTrace()
                _contributors.value = null
                call.cancel()
            }

            override fun onResponse(
                call: Call<MutableList<Contributor>>,
                response: Response<MutableList<Contributor>>
            ) {
                Log.e(TAG, "run: success data is ${response.body()}")
                val list: MutableList<Contributor> = response.body() as MutableList<Contributor>
                _contributors.value = list
            }
        })
    }
}