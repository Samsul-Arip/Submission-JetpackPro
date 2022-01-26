package com.samsul.moviecatalogue.ui.fragment.tvshow

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
import com.samsul.moviecatalogue.databinding.FragmentTvShowBinding
import com.samsul.moviecatalogue.ui.detail.DetailActivity
import com.samsul.moviecatalogue.utils.SortUtils
import com.samsul.moviecatalogue.utils.ViewModelFactory
import com.samsul.moviecatalogue.vo.Status

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private lateinit var tvShowAdapter: TvShowsAdapter

    private val viewModelTvShow by lazy {
        val factory = ViewModelFactory.getInstance(requireContext())
        ViewModelProvider(this, factory)[TvShowViewModel::class.java]
    }


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
        if (activity != null) {
            tvShowAdapter = TvShowsAdapter()

            viewModelTvShow.listTvShowPaged(SortUtils.NEWEST).observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            binding?.loadingBar?.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            binding?.loadingBar?.visibility = View.GONE
                            tvShowAdapter.submitList(it.data)
                        }
                        Status.ERROR -> {
                            binding?.loadingBar?.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })


            binding?.rvTvShow?.apply {
                layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

            tvShowAdapter.setOnItemClickCallBack(object : TvShowsAdapter.ItemClickCallBack {
                override fun onItemClicked(data: DataLocalTvShow) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.ID_TV_SHOW, data.tvShowId)
                    intent.putExtra(DetailActivity.EXTRA_DATA2, data)
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