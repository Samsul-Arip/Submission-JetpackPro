package com.samsul.moviecatalogue.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(
    private val fragmentList: List<Fragment>,
    fm: FragmentManager,
    lifeCycle: Lifecycle
) : FragmentStateAdapter(fm,lifeCycle) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}