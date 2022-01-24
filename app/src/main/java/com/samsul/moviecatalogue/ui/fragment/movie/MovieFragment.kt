package com.samsul.moviecatalogue.ui.fragment.movie

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.databinding.FragmentMovieBinding
import com.samsul.moviecatalogue.ui.detail.DetailActivity
import com.samsul.moviecatalogue.utils.ViewModelFactory
import com.samsul.moviecatalogue.vo.Status

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter
    private var listMovie: List<DataLocalMovie> = listOf()

    private val movieViewModel by lazy {
        val factory = ViewModelFactory.getInstance(requireContext())
        ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            movieAdapter = MovieAdapter(requireContext())

            movieViewModel.listMovie().observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), "Test", Toast.LENGTH_SHORT).show()
                if(it != null) {
                    when(it.status) {
                        Status.LOADING -> {
                            binding.loadingBar.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            binding.loadingBar.visibility = View.GONE
                            movieAdapter.setListMovie(it.data!!)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.loadingBar.visibility = View.GONE
                            Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            binding.rvMovie.apply {
                layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            movieAdapter.setOnItemClickCallback(object : MovieAdapter.ItemClickCallback {
                override fun onClickCallback(data: DataLocalMovie) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.ID_MOVIE, data.movieId)
                    startActivity(intent)
                }

            })

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}