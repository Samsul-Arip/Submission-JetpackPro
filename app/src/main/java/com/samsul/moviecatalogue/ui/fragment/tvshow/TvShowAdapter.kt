package com.samsul.moviecatalogue.ui.fragment.tvshow

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.databinding.FilmItemBinding
import com.squareup.picasso.Picasso

class TvShowAdapter(private val context: Context
) : RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {

    private val listMovie = ArrayList<DataTvShow>()
    private var itemClickCallback: ItemClickCallback? = null

    fun setOnItemClickCallBack(itemClick: ItemClickCallback) {
        this.itemClickCallback = itemClick
    }

    inner class TvViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataTvShow) {
            with(binding) {
                tvTitle.text = data.titleTv
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
    ): TvShowAdapter.TvViewHolder {
        val view = FilmItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    @SuppressLint("NotifyDataSetChanged")
    fun setListTvShow(listMovie: List<DataTvShow>) {
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
        notifyDataSetChanged()
    }

    interface ItemClickCallback {
        fun onClickCallback(data: DataTvShow)
    }

}