package com.example.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.databinding.ItemMovieBinding

class MovieAdapter(private val movie: List<Movies>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val mv = movie[position]
        holder.binding.movieTitle.text = mv.title
        holder.binding.tvTime.text = mv.running_time + " min "

        Glide.with(holder.binding.movieImage.context).load(mv.image).into(holder.binding.movieImage)
    }

    override fun getItemCount() = movie.size
}
