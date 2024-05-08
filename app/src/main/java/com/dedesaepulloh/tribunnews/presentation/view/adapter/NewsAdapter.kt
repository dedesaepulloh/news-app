package com.dedesaepulloh.tribunnews.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dedesaepulloh.tribunnews.R
import com.dedesaepulloh.tribunnews.data.remote.model.response.NewsItem
import com.dedesaepulloh.tribunnews.databinding.ItemNewsBinding
import com.dedesaepulloh.tribunnews.utils.CommonUtils.changeDateFormat
import com.dedesaepulloh.tribunnews.utils.Constants.NEW_DATE_TIME_FORMAT

class NewsAdapter(private val onClick: (NewsItem) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var news: MutableList<NewsItem> = mutableListOf()

    fun setNews(data: List<NewsItem>) {
        news.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int = news.size

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsItem) {
            with(binding) {
                news.apply {
                    tvNewsDate.text = pubtime.changeDateFormat(NEW_DATE_TIME_FORMAT)
                    tvNewsTitle.text = title
                    Glide.with(itemView)
                        .load(imageUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(R.drawable.ic_image)
                        .into(imgNews)
                    root.setOnClickListener {
                        onClick.invoke(news)
                    }
                }

            }
        }
    }

}