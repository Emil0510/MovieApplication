package com.emilabdurahmanli.movieapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emilabdurahmanli.movieapplication.Constants
import com.emilabdurahmanli.movieapplication.R
import com.emilabdurahmanli.movieapplication.activity.MovieActivity
import com.emilabdurahmanli.movieapplication.model.Movie
import com.squareup.picasso.Picasso

class Adapter(var list : List<Movie>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var posterImage = itemView.findViewById<ImageView>(R.id.moviePosterImage)
        var movieTitle = itemView.findViewById<TextView>(R.id.movieTitle)
        var view = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movies_row,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(Constants.imageUrl+list[position].poster_path).into(holder.posterImage);
        holder.movieTitle.setText(list[position].title)
        holder.posterImage.setOnClickListener {
            val intent = Intent(holder.view.context,MovieActivity::class.java)
            intent.putExtra("Movie",list[position])
            holder.view.context.startActivity(intent)
        }
    }
}