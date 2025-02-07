package com.satyam.snapnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.satyam.snapnews.databinding.FragmentNewsBinding
import com.satyam.snapnews.presentation.adapter.NewsAdapter
import com.satyam.snapnews.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        initRecyclerView()
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
    }

}