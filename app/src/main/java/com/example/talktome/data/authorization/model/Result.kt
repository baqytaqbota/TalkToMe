package com.example.talktome.data.authorization.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthResultDTO(
    val userId: String,
    val role: String,
    val token: String,
    val createdAt: String
): Parcelable