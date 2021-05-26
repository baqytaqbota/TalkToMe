package com.example.talktome.data.diary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewDiaryRequestDTO(
    val diaryTitle: String,
    val diaryDescription: String
): Parcelable