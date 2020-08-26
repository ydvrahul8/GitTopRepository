package com.example.gittoprepository.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gittoprepository.R
import com.example.gittoprepository.models.Contributor
import com.example.gittoprepository.models.Repository

class ContributorAdapter(
    val list: MutableList<Contributor>,
    var listener: (Int) -> Unit
) : RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>() {
    inner class ContributorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, listener: (Int) -> Unit) {
            Glide.with(itemView)
                .load(list[position].avatar_url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(image)
            name.text = list[position].login
            layout.setOnClickListener { listener(position) }
        }

        var image = view.findViewById<ImageView>(R.id.imageView_avatar)
        var name = view.findViewById<TextView>(R.id.textView_name)
        var layout = view.findViewById<ConstraintLayout>(R.id.view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contributor, parent, false)
        return ContributorViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(position, listener)
    }

}