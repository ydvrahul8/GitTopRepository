package com.example.gittoprepository.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gittoprepository.R
import com.example.gittoprepository.models.Repository

class RepositoryAdapter(
    val list: MutableList<Repository>,
    var listener: (Int) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    inner class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, listener: (Int) -> Unit) {
            Glide.with(itemView)
                .load(list[position].owner?.avatar_url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(image)
            name.text = list[position].name
            full_name.text = list[position].full_name
            layout.setOnClickListener { listener(position) }
        }

        var image = view.findViewById<ImageView>(R.id.imageView_avatar)
        var name = view.findViewById<TextView>(R.id.textView_name)
        var full_name = view.findViewById<TextView>(R.id.textView_fullName)
        var layout = view.findViewById<CardView>(R.id.view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(position, listener)
    }

}