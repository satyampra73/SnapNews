package com.satyam.snapnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.satyam.snapnews.databinding.FragmentNewsBinding
import com.satyam.snapnews.presentation.adapter.NewsAdapter
import com.satyam.snapnews.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val country = "us"
    private val page = 1
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
        viewNewsList()

    }

    private fun viewNewsList(){
        viewModel.getNewsHeadlines(country,page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner,{
            response->
            when(response){
                is com.satyam.snapnews.data.util.Resource.Success->{
                hideProgressBar()
                response.data?.let{
                    newsAdapter.differ.submitList(it.articles.toList())
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
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }

}