package com.example.gittoprepository.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gittoprepository.R
import com.example.gittoprepository.adapters.RepositoryAdapter
import com.example.gittoprepository.viewmodels.AllRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_all_repositories.*

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
            Log.e(TAG, "repositoryObserver: ${it.size} and " + it.toString())
            if (!it.isNullOrEmpty()) {
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = RepositoryAdapter(it) { position ->
                    Toast.makeText(this, "Data is $position", Toast.LENGTH_SHORT).show()
                }
            } else
                Toast.makeText(this, "Sorry no data found...", Toast.LENGTH_SHORT).show()
        }
        )
    }
}