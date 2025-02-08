package com.satyam.snapnews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.satyam.snapnews.data.model.Article
import com.satyam.snapnews.databinding.NewsListItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        val binding: NewsListItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(article: Article){
            binding.txtTitle.text = article.title
            binding.txtDesc.text = article.description
            binding.txtPublishedAt.text = article.publishedAt
            binding.txtTvSource.text = article.source?.name

            Glide.with(binding.imgImage.context)
                .load(article.urlToImage)
                .into(binding.imgImage)

            binding.root.setOnClickListener{
                onItemClickListener?.let{
                    it(article)
                }
            }
        }

    }

    private var onItemClickListener : ((Article)->Unit)?=null

    fun setOnItemClickListener(listener : (Article)->Unit){
        onItemClickListener = listener
    }


}