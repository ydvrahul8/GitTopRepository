package com.example.gittoprepository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.gittoprepository.viewmodels.AllRepositoriesViewModel

class AllRepositories : AppCompatActivity() {

    private val TAG = "AllRepositories"
    private var viewModel: AllRepositoriesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_repositories)
        viewModel = ViewModelProvider(this).get(AllRepositoriesViewModel::class.java)
        viewModel?.getAllRepositories()
        repositoryObserver()
    }

    private fun repositoryObserver() {
        viewModel?.allRepository?.observe(this, Observer {
            Log.e(TAG, "repositoryObserver: ${it.size} and "+it.toString() )
        })
    }
}