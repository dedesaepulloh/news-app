package com.dedesaepulloh.tribunnews.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dedesaepulloh.tribunnews.R
import com.dedesaepulloh.tribunnews.databinding.ActivityNewsBinding
import com.dedesaepulloh.tribunnews.presentation.view.adapter.NewsAdapter
import com.dedesaepulloh.tribunnews.presentation.viewmodel.NewsViewModel
import com.dedesaepulloh.tribunnews.utils.CommonUtils.showToast
import com.dedesaepulloh.tribunnews.utils.Constants.ID_NEWS_ITEM
import com.dedesaepulloh.tribunnews.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private var activityNewsBinding: ActivityNewsBinding? = null

    private val binding get() = activityNewsBinding

    private val viewModel: NewsViewModel by viewModels()

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter {
            val intent = Intent(this, NewsDetailActivity::class.java)
            intent.putExtra(ID_NEWS_ITEM, it)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initObserver()
        initProcess()
        initUi()

    }

    private fun initUi() {
        binding?.rvNews?.apply {
            layoutManager = GridLayoutManager(this@NewsActivity, 2)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    private fun initProcess() {
        viewModel.loadNews()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.news.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }

                    is Resource.Success -> {
                        showLoading(false)
                        if (result.data.isNotEmpty()) {
                            newsAdapter.setNews(result.data)
                        } else {
                            showToast(this@NewsActivity, getString(R.string.label_data_not_found))
                        }
                    }

                    is Resource.Error -> {
                        showLoading(false)
                        showToast(this@NewsActivity, result.exception.message.toString())
                    }
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.apply {
                rvNews.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        } else {
            binding?.apply {
                rvNews.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }


}