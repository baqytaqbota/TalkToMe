package com.example.talktome.data.blog.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class BlogResultDTO(
    val blogs: List<BlogItemDTO>
): Parcelable

@Parcelize
data class BlogItemDTO(
    val _id: String,
    val title: String,
    val content: String,
    val author: String,
    val authorId: String,
    val createdAt: String,
    val image: String
): Parcelable