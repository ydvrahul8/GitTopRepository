package com.example.gittoprepository.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gittoprepository.R
import com.example.gittoprepository.utils.ACCESS_TOKEN
import com.example.gittoprepository.viewmodels.ContributorsViewModel

class ContibutorActivity : AppCompatActivity() {
    private val TAG = "ContibutorActivity"
    private var viewModel:ContributorsViewModel ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contibutor)
        viewModel = ViewModelProvider(this).get(ContributorsViewModel::class.java)
        viewModel?.getContributors("mojombo/grit",1, ACCESS_TOKEN)
        contributorsObserver()
    }

    private fun contributorsObserver() {
        viewModel?.contributors?.observe(this, Observer {
            Log.e(TAG, "contributorsObserver: ${it.size} and " + it.toString())
        })
    }
}