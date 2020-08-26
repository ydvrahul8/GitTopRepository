package com.example.gittoprepository.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gittoprepository.R
import com.example.gittoprepository.adapters.RepositoryAdapter
import com.example.gittoprepository.utils.DATA
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
                progressbar.visibility = View.GONE
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = RepositoryAdapter(it) { position ->
                   var intent = Intent(this,ContibutorActivity::class.java)
                    intent.putExtra(DATA,it[position])
                    startActivity(intent)
                }
            } else
                Toast.makeText(this, "Sorry no data found...", Toast.LENGTH_SHORT).show()
        }
        )
    }
}