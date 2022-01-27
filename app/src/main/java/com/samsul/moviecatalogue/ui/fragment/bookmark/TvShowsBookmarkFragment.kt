package com.samsul.moviecatalogue.ui.fragment.bookmark

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.databinding.FragmentTvShowsBookmarkBinding
import com.samsul.moviecatalogue.ui.detail.DetailActivity
import com.samsul.moviecatalogue.utils.EspressoIdlingResource
import com.samsul.moviecatalogue.utils.ViewModelFactory

class TvShowsBookmarkFragment : Fragment() {

    private var _binding: FragmentTvShowsBookmarkBinding? = null
    private val binding get() = _binding!!

    private val viewModelTvShow by lazy {
        val factory = ViewModelFactory.getInstance(requireContext())
        ViewModelProvider(requireActivity(), factory)[BookmarkViewModel::class.java]
    }

    private lateinit var bookmarkAdapter: BookmarkTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowsBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarkAdapter = BookmarkTvShowAdapter()
        binding.rvBookmarkTvshow.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = bookmarkAdapter
        }


        viewModelTvShow.getTvShowBookmark().observe(viewLifecycleOwner, {
            if(it != null) {
                bookmarkAdapter.submitList(it)
            } else {
                Toast.makeText(requireContext(), "Data Kosong", Toast.LENGTH_SHORT).show()
            }
        })
        bookmarkAdapter.setOnItemClickCallBack(object : BookmarkTvShowAdapter.ItemClickCallBack {
            override fun onItemClicked(data: DataLocalTvShow) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.NAME, "tvshow")
                intent.putExtra(DetailActivity.ID_TV_SHOW, data.tvShowId)
                intent.putExtra(DetailActivity.EXTRA_DATA2, data)
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}