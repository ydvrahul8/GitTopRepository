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
import com.bumptech.glide.Glide
import com.example.gittoprepository.R
import com.example.gittoprepository.adapters.ContributorAdapter
import com.example.gittoprepository.adapters.RepositoryAdapter
import com.example.gittoprepository.models.Contributor
import com.example.gittoprepository.models.Repository
import com.example.gittoprepository.utils.ACCESS_TOKEN
import com.example.gittoprepository.utils.DATA
import com.example.gittoprepository.viewmodels.ContributorsViewModel
import com.example.gittoprepository.viewmodels.UserRepositoryViewModel
import kotlinx.android.synthetic.main.activity_all_repositories.*
import kotlinx.android.synthetic.main.activity_contibutor.*
import kotlinx.android.synthetic.main.activity_user_repository.*

class UserRepositoryActivity : AppCompatActivity() {
    private val TAG = "UserRepositoryActivity"
    private var viewModel: UserRepositoryViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_repository)
        viewModel = ViewModelProvider(this).get(UserRepositoryViewModel::class.java)
        val data = intent.getParcelableExtra<Contributor>("data")
        setData(data)
    }

    private fun setData(data: Contributor?) {
        Glide.with(this)
            .load(data?.avatar_url)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView_avatar)

        textView_name.text = data?.login
        viewModel?.getUserRepositories(data?.login!!)
        userRepositoryObserver()
    }

    private fun userRepositoryObserver() {
        viewModel?.userRepository?.observe(this, Observer {
            Log.e(TAG, "userRepositoryObserver: ${it.size} and " + it.toString())
            if (!it.isNullOrEmpty()) {
                progressbar_user.visibility = View.GONE
                recyclerView_repositories.layoutManager = LinearLayoutManager(this)
                recyclerView_repositories.setHasFixedSize(true)
                recyclerView_repositories.adapter = RepositoryAdapter(it) {
                }
            } else
                Toast.makeText(this, "Sorry no data found...", Toast.LENGTH_SHORT).show()
        })
    }
}