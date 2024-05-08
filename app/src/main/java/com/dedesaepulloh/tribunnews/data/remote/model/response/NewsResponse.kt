package com.dedesaepulloh.tribunnews.data.remote.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NewsResponse(

	@SerializedName("errno")
	val errno: Int,

	@SerializedName("total")
	val total: Int,

	@SerializedName("data")
	val data: List<NewsItem>,

	@SerializedName("errmsg")
	val errmsg: String,

	@SerializedName("page")
	val page: Int,

	@SerializedName("row")
	val row: Int
)

@Parcelize
data class NewsItem(

	@SerializedName("iid")
	val iid: Int,

	@SerializedName("image_url")
	val imageUrl: String,

	@SerializedName("pubtime")
	val pubtime: String,

	@SerializedName("author")
	val author: String,

	@SerializedName("width")
	val width: Int,

	@SerializedName("source")
	val source: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("body")
	val body: String,

	@SerializedName("category")
	val category: String,

	@SerializedName("url")
	val url: String,

	@SerializedName("height")
	val height: Int
) : Parcelable
