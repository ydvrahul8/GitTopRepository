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
import com.example.gittoprepository.models.Repository
import com.example.gittoprepository.utils.ACCESS_TOKEN
import com.example.gittoprepository.utils.DATA
import com.example.gittoprepository.viewmodels.ContributorsViewModel
import kotlinx.android.synthetic.main.activity_all_repositories.*
import kotlinx.android.synthetic.main.activity_contibutor.*

class ContibutorActivity : AppCompatActivity() {
    private val TAG = "ContibutorActivity"
    private var viewModel: ContributorsViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contibutor)
        viewModel = ViewModelProvider(this).get(ContributorsViewModel::class.java)
        val data = intent.getParcelableExtra<Repository>("data")
        setData(data)
    }

    private fun setData(data: Repository?) {
        Glide.with(this)
            .load(data?.owner?.avatar_url)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView_avatar)

        textView_name.text = data?.name
        textView_fullName.text = data?.full_name
        textView_description.text = data?.description
        viewModel?.getContributors(data?.full_name!!, 1, ACCESS_TOKEN)
        contributorsObserver()
    }

    private fun contributorsObserver() {
        viewModel?.contributors?.observe(this, Observer {
            Log.e(TAG, "contributorsObserver: ${it.size} and " + it.toString())
            if (!it.isNullOrEmpty()) {
                progressbar_contributor.visibility = View.GONE
                recyclerView_contributors.setHasFixedSize(true)
                recyclerView_contributors.adapter = ContributorAdapter(it) { position ->
                    var intent = Intent(this,UserRepositoryActivity::class.java)
                    intent.putExtra(DATA,it[position])
                    startActivity(intent)
                }
            } else
                Toast.makeText(this, "Sorry no data found...", Toast.LENGTH_SHORT).show()
        })
    }
}