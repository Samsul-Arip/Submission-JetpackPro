package com.samsul.moviecatalogue.ui.fragment.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.databinding.FilmItemBinding
import com.squareup.picasso.Picasso

class TvShowsAdapter: PagedListAdapter<DataLocalTvShow, TvShowsAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    private var itemClickCallBack: ItemClickCallBack? = null

    fun setOnItemClickCallBack(itemClickCallBack: ItemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack
    }

    inner class TvShowViewHolder(private val binding: FilmItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataLocalTvShow) {
            with(binding){
                tvTitle.text = data.titleTvShow
                Picasso.get()
                    .load("http://image.tmdb.org/t/p/w500${data.imageTvShow}")
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataLocalTvShow>() {
            override fun areItemsTheSame(
                oldItem: DataLocalTvShow,
                newItem: DataLocalTvShow
            ): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(
                oldItem: DataLocalTvShow,
                newItem: DataLocalTvShow
            ): Boolean {
                return  oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemBinding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemBinding)
    }

    interface ItemClickCallBack {
        fun onItemClicked(data: DataLocalTvShow)
    }
}