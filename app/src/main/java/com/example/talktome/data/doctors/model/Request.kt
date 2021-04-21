package com.example.talktome.data.doctors.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DoctorListRequestBody(
    var tags: List<String>
): Parcelable