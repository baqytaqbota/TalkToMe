package com.example.talktome.data.doctors.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DoctorListDTO(
    val _id : String,
    val id : String,
    val email: String,
    val firstName: String,
    val secondName: String,
    val image: String,
    val sex: String,
    val education: List<DoctorEducation>,
    val rating: Double,
    val tags: List<String>,
    val createdAt: String,
    val feedback: List<DoctorFeedBack>
): Parcelable

@Parcelize
data class DoctorEducation(
    val university: String,
    val city: String,
    val major: String,
    val period: String
): Parcelable

@Parcelize
data class DoctorFeedBack(
    val _id: String,
    val patientId: String,
    val comment: String,
    val createdAt: String
): Parcelable