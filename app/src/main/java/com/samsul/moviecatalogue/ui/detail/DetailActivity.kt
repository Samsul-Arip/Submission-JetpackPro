package com.samsul.moviecatalogue.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.databinding.ActivityDetailBinding
import com.samsul.moviecatalogue.utils.ViewModelFactory
import com.samsul.moviecatalogue.vo.Status
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_MOVIE = "id_movie"
        const val ID_TV_SHOW = "id_tv_show"
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_DATA2 = "extra_data2"
        const val NAME = "name"
    }

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModelDetail by lazy {
        val factory = ViewModelFactory.getInstance(applicationContext)
        ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    var movieId: Int? = null
    var tvShowId: Int? = null

    private var menu: Menu? = null

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
        setSupportActionBar(binding.toolbarDetail)


        movieId = intent.getIntExtra(ID_MOVIE, 0)
        tvShowId = intent.getIntExtra(ID_TV_SHOW, 0)

        if (movieId != 0) {
            viewModelDetail.setDetailSelectedMovie(movieId!!)
            viewModelDetail.getMovie().observe(this, { detailMovie ->
                if (detailMovie != null) {
                    when (detailMovie.status) {
                        Status.LOADING -> {
                            setLoading(true)
                        }
                        Status.SUCCESS -> {
                            setLoading(false)
                            detailMovie.data?.let { loadMovie(it) }
                        }
                        Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        if (tvShowId != 0) {
            viewModelDetail.setDetailSelectedTvShow(tvShowId!!)
            viewModelDetail.getTvShow().observe(this, { detailTv ->
                if (detailTv != null) {
                    when (detailTv.status) {
                        Status.LOADING -> {
                            setLoading(true)
                        }
                        Status.SUCCESS -> {
                            setLoading(false)
                            detailTv.data?.let { loadTv(it) }
                        }
                        Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        this.menu = menu

        if(intent.getStringExtra(NAME) == "movie") {
            val itemMovie = intent.getParcelableExtra<DataLocalMovie>(EXTRA_DATA) as DataLocalMovie
            setBookmarkState(itemMovie.favorite)
        }
        if(intent.getStringExtra(NAME) == "tvshow") {
            val itemTvShow = intent.getParcelableExtra<DataLocalTvShow>(EXTRA_DATA2) as DataLocalTvShow
            setBookmarkState(itemTvShow.favorite)
        }
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_favorite -> {
                if(binding.loadingContent.visibility == View.VISIBLE 
                    && binding.loadingImage.visibility == View.VISIBLE) {
                    Toast.makeText(this@DetailActivity, "Wait for loading to finish", Toast.LENGTH_SHORT).show()
                } else {
                    if(movieId != 0) {
                        val movie = intent.getParcelableExtra<DataLocalMovie>(EXTRA_DATA) as DataLocalMovie
                        val newState = !movie.favorite
                        message(newState)
                        viewModelDetail.setBookmarkMovie(movie, newState)
                        setBookmarkState(newState)
                    }
                    if(tvShowId != 0) {
                        val tvShow = intent.getParcelableExtra<DataLocalTvShow>(EXTRA_DATA2) as DataLocalTvShow
                        val newState = !tvShow.favorite
                        message(newState)
                        viewModelDetail.setBookmarkTvShow(tvShow, newState)
                        setBookmarkState(newState)
                    }
                }
                
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(favorite: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.menu_favorite)
        if (favorite) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_unfavorite)
        }
    }

    private fun message(state: Boolean) {
        if (state) {
            Toast.makeText(this, "Add to Favorite", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Delete to Favorite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadMovie(response: LocalDetailMovie) {
        setImageView("https://image.tmdb.org/t/p/w500${response.imagePoster}", binding.imagePreview)
        binding.tvTitle.text = response.titleMovie
        binding.tvDate.text = response.releaseDate
        binding.tvStar.text = response.rating.toString()
        binding.tvStatus.text = response.genre
        binding.tvTime.text = response.time
        binding.tvSinopsis.text = response.synopsis

    }

    @SuppressLint("SetTextI18n")
    private fun loadTv(response: LocalDetailTvShow) {
        setImageView("https://image.tmdb.org/t/p/w500${response.imageTvShow}", binding.imagePreview)
        binding.tvTitle.text = response.titleTvShow
        binding.tvDate.text = response.releaseDate
        binding.tvStar.text = response.rating
        binding.tvStatus.text = response.status
        binding.tvTime.text = response.time
        binding.tvSinopsis.text = response.synopsis
    }

    private fun setImageView(url: String, image: ImageView) {
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_error)
            .into(image)
    }

    private fun setLoading(loading: Boolean) {
        when (loading) {
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