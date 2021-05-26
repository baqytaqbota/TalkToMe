package com.example.talktome.data.diary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiaryDTO(
    val diaryTitle: String,
    val diaryDescription: String
): Parcelable