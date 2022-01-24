package com.samsul.moviecatalogue.ui.fragment.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.databinding.FilmItemBinding
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovie = ArrayList<DataLocalMovie>()
    private var itemClickCallback: ItemClickCallback? = null

    fun setOnItemClickCallback(itemClick: ItemClickCallback) {
        this.itemClickCallback = itemClick
    }

    inner class MovieViewHolder(private val binding: FilmItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataLocalMovie) {
            with(binding) {
                tvTitle.text = data.titleMovie
                Picasso.get()
                    .load("http://image.tmdb.org/t/p/w500${data.imagePoster}")
                    .placeholder(R.drawable.ic_refresh)
                    .error(R.drawable.ic_error)
                    .into(roundedImage)

                itemView.setOnClickListener {
                    itemClickCallback?.onClickCallback(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val view = FilmItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    @SuppressLint("NotifyDataSetChanged")
    fun setListMovie(listMovie: List<DataLocalMovie>) {
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
        notifyDataSetChanged()
    }

    interface ItemClickCallback {
        fun onClickCallback(data: DataLocalMovie)
    }

}