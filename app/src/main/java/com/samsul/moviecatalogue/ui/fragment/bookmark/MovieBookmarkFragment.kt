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
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.databinding.FragmentMovieBookmarkBinding
import com.samsul.moviecatalogue.ui.detail.DetailActivity
import com.samsul.moviecatalogue.utils.ViewModelFactory


class MovieBookmarkFragment : Fragment() {

    private var _binding: FragmentMovieBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkViewModel by lazy {
        val factory = ViewModelFactory.getInstance(requireContext())
        ViewModelProvider(requireActivity(), factory)[BookmarkViewModel::class.java]
    }

    private lateinit var bookmarkAdapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBookmarkBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookmarkAdapter = BookmarkAdapter()
        binding.rvBookmarkMovie.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = bookmarkAdapter
        }

        bookmarkViewModel.getMovieBookmark().observe(viewLifecycleOwner, {
            if(it != null) {
                bookmarkAdapter.submitList(it)
            } else {
                Toast.makeText(requireActivity(), "Data Kosong", Toast.LENGTH_SHORT).show()
            }
        })

        bookmarkAdapter.setOnItemClickCallBack(object : BookmarkAdapter.ItemClickCallBack {
            override fun onItemClicked(data: DataLocalMovie) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.NAME, "movie")
                intent.putExtra(DetailActivity.ID_MOVIE, data.movieId)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        })


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}