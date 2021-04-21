package com.example.talktome.data.patient.model

class PatientInfoDTO(
    val _id: String,
    val id: Int,
    val email: String,
    val firstName: String,
    val secondName: String,
    val image: String,
    val age: Int,
    val sex: String,
    val tags: List<String>,
    val createdAt: String
)