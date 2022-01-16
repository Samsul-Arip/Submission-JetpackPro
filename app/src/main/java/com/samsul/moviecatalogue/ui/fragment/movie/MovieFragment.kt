package com.samsul.moviecatalogue.ui.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.databinding.FragmentMovieBinding
import com.samsul.moviecatalogue.ui.detail.DetailActivity
import com.samsul.moviecatalogue.utils.ViewModelFactory

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter


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
        if(activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            binding.loadingBar.visibility = View.VISIBLE
            movieAdapter = MovieAdapter(requireContext())
            viewModel.listMovie.observe(viewLifecycleOwner, { listMovie ->
                binding.loadingBar.visibility = View.GONE
                movieAdapter.setListMovie(listMovie)
            })

            binding.rvMovie.apply {
                layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            movieAdapter.setOnItemClickcallback(object : MovieAdapter.ItemClickCallback{
                override fun onClickCallback(data: DataMovie) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.ID_MOVIE, data.id)
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