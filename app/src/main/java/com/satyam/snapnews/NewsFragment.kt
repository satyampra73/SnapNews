package com.satyam.snapnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.satyam.snapnews.databinding.FragmentNewsBinding
import com.satyam.snapnews.presentation.adapter.NewsAdapter
import com.satyam.snapnews.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
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
        newsAdapter = (activity as MainActivity).newsAdapter

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }

            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,
                bundle
            )
        }
        initRecyclerView()
        viewNewsList()

    }

    private fun viewNewsList(){
        viewModel.getNewsHeadlines(country,page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner,{
            response->
            when(response){
                is com.satyam.snapnews.data.util.Resource.Success->{
                    val json = Gson().toJson(response)
                    Log.d("strResponse","Response : $json")
                hideProgressBar()
                response.data?.let{
                    newsAdapter.differ.submitList(it.articles.toList())
                    if (it.totalResults % 20 == 0){
                        pages = it.totalResults / 20
                    }
                    else{
                        pages = it.totalResults / 20 + 1
                    }

                    isLastPage = page == pages

                }

                }
                is com.satyam.snapnews.data.util.Resource.Error->{
                    hideProgressBar()
                    response.message?.let{
                        Toast.makeText(activity,"An error occured : $it", Toast.LENGTH_LONG).show()
                    }

                }
                is com.satyam.snapnews.data.util.Resource.Loading->{
                    showProgressBar()

                }

            }

        })
    }


    private fun initRecyclerView() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }

    }

    private fun showProgressBar(){
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }


    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()
            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading &&!isLastPage && hasReachedToEnd && isScrolling

            if (shouldPaginate){
                page++
                viewModel.getNewsHeadlines(country,page)
                isScrolling = false

            }
        }
    }

}