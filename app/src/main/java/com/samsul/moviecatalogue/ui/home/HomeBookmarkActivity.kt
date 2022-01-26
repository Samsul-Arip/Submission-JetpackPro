package com.samsul.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.databinding.ActivityHomeBookmarkBinding
import com.samsul.moviecatalogue.ui.fragment.bookmark.MovieBookmarkFragment
import com.samsul.moviecatalogue.ui.fragment.bookmark.TvShowsBookmarkFragment
import com.samsul.moviecatalogue.ui.home.SectionPagerAdapter

class HomeBookmarkActivity : AppCompatActivity() {

    private val binding: ActivityHomeBookmarkBinding by lazy {
        ActivityHomeBookmarkBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager2()

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setViewPager2() {
        val fragmentList = listOf(MovieBookmarkFragment(), TvShowsBookmarkFragment())
        val tabTitle = listOf(resources.getString(R.string.movies), resources.getString(R.string.tv_show))
        binding.viewPager2.adapter = SectionPagerAdapter(fragmentList, supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabsBookmark, binding.viewPager2){tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}