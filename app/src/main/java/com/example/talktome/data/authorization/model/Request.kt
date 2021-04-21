package com.example.talktome.data.authorization.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequestBody(
    var email: String,
    var password: String
): Parcelable

@Parcelize
data class RegisterRequestBody(
    var email: String,
    var password: String,
    var firstName: String,
    var secondName: String,
    var image: String,
    var age: Int,
    var sex: String,
    var tags: List<String>
): Parcelable