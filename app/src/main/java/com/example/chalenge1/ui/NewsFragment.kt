package com.example.chalenge1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chalenge1.ather.LoadMoreListener
import com.example.chalenge1.adapter.NewsAdapter
import com.example.chalenge1.databinding.NewsFragmentBinding

class NewsFragment : Fragment() {

    private val viewModel by viewModels<NewsViewModel>()

    private var binding: NewsFragmentBinding? = null

    private val adapter = NewsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = NewsAdapter.LoaderSpanSizeLookup(adapter)
        binding?.apply {
            recycleView.layoutManager = layoutManager
            recycleView.adapter = adapter

            recycleView.addOnScrollListener(LoadMoreListener() {
                viewModel.onScrollEndReached()
            })
            swipeToRefresh.setOnRefreshListener {
                viewModel.onRefresh()
            }
            viewModel.items.observe(viewLifecycleOwner) {
                adapter.newsList = it
            }
            viewModel.loadingMore.observe(viewLifecycleOwner) {
                adapter.loadingMore = it
                if (swipeToRefresh.isRefreshing && it) swipeToRefresh.isRefreshing = false
            }
        }
    }
}