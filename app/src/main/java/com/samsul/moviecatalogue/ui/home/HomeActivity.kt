package com.samsul.moviecatalogue.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.databinding.ActivityHomeBinding
import com.samsul.moviecatalogue.ui.fragment.movie.MovieFragment
import com.samsul.moviecatalogue.ui.fragment.tvshow.TvShowFragment

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager2()

        binding.imgCollection.setOnClickListener {
            startActivity(Intent(this@HomeActivity, HomeBookmarkActivity::class.java))
        }
    }

    private fun setViewPager2() {
        val fragmentList = listOf(MovieFragment(), TvShowFragment())
        val tabTitle = listOf(resources.getString(R.string.movies), resources.getString(R.string.tv_show))
        binding.viewPager2.adapter = SectionPagerAdapter(fragmentList, supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabs, binding.viewPager2){tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}