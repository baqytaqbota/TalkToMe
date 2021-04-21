package com.example.talktome.data.session.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SessionResultDTO(
    val _id: String,
    val doctor: String,
    val doctorId: String,
    val patient: String,
    val patientId: String,
    val isActive: Boolean,
    val date: String,
    val time: String,
    val datetime: String,
    val createdAt: String
): Parcelable