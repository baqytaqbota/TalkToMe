package com.example.talktome.data.blog.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewBlogRequestBody(
    var title: String,
    var content: String,
    var author: String,
    var image: String
): Parcelable

@Parcelize
data class BlogByIdRequestBody(
    var id : String
): Parcelable

@Parcelize
data class BlogUpdateDeleteRequestBody(
    var id: String,
    var title: String,
    var content: String,
    var author: String,
    var image: String
): Parcelable