package com.example.talktome.data.session.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreateSessionRequestBody(
    var doctorId: String,
    var patientId: String,
    var date: String,
    var time: String,
    var datetime: Long
): Parcelable

@Parcelize
data class SessionDateRequestBody(
    val date: String
): Parcelable