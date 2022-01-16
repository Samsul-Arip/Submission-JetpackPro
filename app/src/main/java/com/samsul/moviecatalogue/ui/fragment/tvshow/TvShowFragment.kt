package com.samsul.moviecatalogue.ui.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samsul.moviecatalogue.R
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.databinding.FragmentTvShowBinding
import com.samsul.moviecatalogue.ui.detail.DetailActivity
import com.samsul.moviecatalogue.ui.fragment.movie.MovieViewModel
import com.samsul.moviecatalogue.utils.ViewModelFactory

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private lateinit var tvShowAdapter: TvShowAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            tvShowAdapter = TvShowAdapter(requireContext())
            binding?.loadingBar?.visibility = View.VISIBLE
            viewModel.listTvShow.observe(viewLifecycleOwner, { tvShow ->
                binding?.loadingBar?.visibility = View.GONE
                tvShowAdapter.setListTvShow(tvShow)
            })

            binding?.rvTvShow?.apply {
                layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

            tvShowAdapter.setOnItemClickCallBack(object : TvShowAdapter.ItemClickCallback{
                override fun onClickCallback(data: DataTvShow) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.ID_TV_SHOW, data.id)
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