package com.dedesaepulloh.tribunnews.presentation.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dedesaepulloh.tribunnews.R
import com.dedesaepulloh.tribunnews.data.remote.model.response.NewsItem
import com.dedesaepulloh.tribunnews.databinding.ActivityNewsDetailBinding
import com.dedesaepulloh.tribunnews.utils.CommonUtils.changeDateFormat
import com.dedesaepulloh.tribunnews.utils.Constants.ID_NEWS_ITEM
import com.dedesaepulloh.tribunnews.utils.Constants.NEW_DATE_TIME_FORMAT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    private var activityNewsDetailBinding: ActivityNewsDetailBinding? = null

    private val binding get() = activityNewsDetailBinding

    private var newsItem: NewsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsDetailBinding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initIntent()
        initUi()
    }

    private fun initUi() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.label_detail)
        binding?.apply {
            newsItem?.apply {
                Glide.with(this@NewsDetailActivity)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_image)
                    .into(imgNews)
                tvNewsDate.text = pubtime.changeDateFormat(NEW_DATE_TIME_FORMAT)
                tvNewsTitle.text = title
                tvAuthor.text = author
                tvSource.text = source
                tvNewsContent.text = HtmlCompat.fromHtml(body, HtmlCompat.FROM_HTML_MODE_COMPACT)
                tvNewsContent.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    private fun initIntent() {
        newsItem = intent.getParcelableExtra(ID_NEWS_ITEM)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}