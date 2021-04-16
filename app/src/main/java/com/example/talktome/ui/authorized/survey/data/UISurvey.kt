package com.example.talktome.ui.authorized.survey.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIGender(
    val id: Int,
    val genderName: String
): Parcelable

@Parcelize
data class UIServiceType(
    val id: Int,
    val serviceType: String,
    var isSelected: Boolean
): Parcelable
