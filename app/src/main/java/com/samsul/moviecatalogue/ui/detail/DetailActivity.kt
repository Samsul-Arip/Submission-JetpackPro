package com.samsul.moviecatalogue.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.databinding.ActivityDetailBinding
import com.samsul.moviecatalogue.utils.ViewModelFactory
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_MOVIE = "id_movie"
        const val ID_TV_SHOW = "id_tv_show"
    }

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.collapsingLayout.apply {
            setCollapsedTitleTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            setExpandedTitleColor(Color.TRANSPARENT)
        }
        binding.imgBackDetail.setOnClickListener {
            onBackPressed()
            finish()
        }

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        setLoading(true)
        val movieId = intent.getStringExtra(ID_MOVIE)
        val tvShowId = intent.getStringExtra(ID_TV_SHOW)
        if(!movieId.isNullOrEmpty()) {
            viewModel.getDetailMovie(intent.getStringExtra(ID_MOVIE)!!).observe(this, { responses ->
                if(responses != null) {
                    setLoading(false)
                    loadMovie(responses)
                }
            })
        }

        if(!tvShowId.isNullOrEmpty()) {
            viewModel.getDetailTv(intent.getStringExtra(ID_TV_SHOW)!!).observe(this, { responses ->
                if(responses != null) {
                    setLoading(false)
                    loadTv(responses)
                }
            })
        }

    }

    private fun loadMovie(response: DetailMovieResponse ) {
        setImageView("https://image.tmdb.org/t/p/w500${response.imagePath}", binding.imagePreview)
        binding.tvTitle.text = response.title
        binding.tvDate.text = response.releaseDate
        binding.tvStar.text = response.star.toString()
        val builderGenre = StringBuilder()
        for(genre in response.genre!!) {
            builderGenre.append(genre.name).append(",")
            binding.tvGenre.text = builderGenre.toString().substring(0, builderGenre.length - 1)
        }
        binding.tvTime.text = response.time
        binding.tvSinopsis.text = response.synopsis

    }

    @SuppressLint("SetTextI18n")
    private fun loadTv(response: DetailTvShowResponse) {
        setImageView("https://image.tmdb.org/t/p/w500${response.imagePath}", binding.imagePreview)
        binding.tvTitle.text = response.title
        binding.tvDate.text = "${response.firstRelease} - ${response.lastRelease}"
        binding.tvStar.text = response.star
        val builderGenre = StringBuilder()
        for(genre in response.genre!!) {
            builderGenre.append(genre.name).append(",")
            binding.tvGenre.text = builderGenre.toString().substring(0, builderGenre.length - 1)
        }
        binding.tvTime.text = response.episode
        binding.tvSinopsis.text = response.synopsis
    }

    private fun setImageView(url: String, image: ImageView) {
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_error)
            .into(image)
    }

    private fun setLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.loadingImage.visibility = View.VISIBLE
                binding.loadingContent.visibility = View.VISIBLE
                binding.layoutContent.visibility = View.INVISIBLE
            }
            false -> {
                binding.loadingImage.visibility = View.GONE
                binding.loadingContent.visibility = View.GONE
                binding.layoutContent.visibility = View.VISIBLE
            }
        }
    }

}