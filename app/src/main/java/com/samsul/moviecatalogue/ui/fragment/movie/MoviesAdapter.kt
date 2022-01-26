package com.samsul.moviecatalogue.ui.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.databinding.FilmItemBinding
import com.squareup.picasso.Picasso

class MoviesAdapter: PagedListAdapter<DataLocalMovie, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    private var itemClickCallBack: ItemClickCallBack? = null

    fun setOnItemClickCallBack(itemClickCallBack: ItemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack
    }

    inner class MoviesViewHolder(private val binding: FilmItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(data: DataLocalMovie) {
                with(binding) {
                    tvTitle.text = data.titleMovie
                    Picasso.get()
                        .load("http://image.tmdb.org/t/p/w500${data.imagePoster}")
                        .placeholder(R.drawable.ic_refresh)
                        .error(R.drawable.ic_error)
                        .into(roundedImage)
                    itemView.setOnClickListener {
                        itemClickCallBack?.onItemClicked(data)
                    }
                }
            }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataLocalMovie>() {
            override fun areItemsTheSame(
                oldItem: DataLocalMovie,
                newItem: DataLocalMovie
            ): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(
                oldItem: DataLocalMovie,
                newItem: DataLocalMovie
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { data ->
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val itemBinding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemBinding)
    }


    interface ItemClickCallBack {
        fun onItemClicked(data: DataLocalMovie)
    }
}