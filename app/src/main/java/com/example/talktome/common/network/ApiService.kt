package com.example.talktome.common.network

import com.example.talktome.data.authorization.model.RegisterRequestBody
import com.example.talktome.data.authorization.model.AuthResultDTO
import com.example.talktome.data.authorization.model.LoginRequestBody
import com.example.talktome.data.blog.model.*
import com.example.talktome.data.diary.model.DiaryDTO
import com.example.talktome.data.diary.model.NewDiaryRequestDTO
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.data.doctors.model.DoctorListRequestBody
import com.example.talktome.data.patient.model.PatientInfoDTO
import com.example.talktome.data.session.model.CreateSessionRequestBody
import com.example.talktome.data.session.model.SessionDateRequestBody
import com.example.talktome.data.session.model.SessionResultDTO
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun makeLogin(@Body request: LoginRequestBody): AuthResultDTO

    @POST("patients/signup")
    suspend fun makeRegister(@Body request: RegisterRequestBody): AuthResultDTO

    @POST("doctors")
    suspend fun getDoctors(@Body request: DoctorListRequestBody): List<DoctorListDTO>

    @POST("blogs")
    suspend fun createBlog(@Body request: NewBlogRequestBody): Unit

    @GET("blogs/all")
    suspend fun getAllBlogs(): List<BlogItemDTO>

    @GET("blogs/doctor")
    suspend fun getDoctorBlogs(): List<BlogItemDTO>

    @GET("blogs")
    suspend fun getBlogById(@Body id: String): BlogItemDTO

    @HTTP(method = "PUT", path = "blogs", hasBody = true)
    suspend fun updateBlog(@Body request: BlogUpdateDeleteRequestBody): Unit

    @HTTP(method = "DELETE", path = "blogs", hasBody = true)
    suspend fun deleteBlogs(@Body request: BlogUpdateDeleteRequestBody): Unit

    @GET("patients")
    suspend fun getPatientProfile(): PatientInfoDTO

    @GET("doctors")
    suspend fun getDoctorProfile(): DoctorListDTO

    @POST("sessions")
    suspend fun makeCreateSession(@Body request: CreateSessionRequestBody): Unit

    @HTTP(method = "GET", path = "sessions", hasBody = false)
    suspend fun getNearlySession(): SessionResultDTO

    @HTTP(method = "POST", path = "sessions/doctor", hasBody = true)
    suspend fun getSessions(@Body request: SessionDateRequestBody): List<SessionResultDTO>

    @HTTP(method = "POST", path = "diary", hasBody = true)
    suspend fun addNewDiary(@Body request: NewDiaryRequestDTO): Unit

    @GET("diary")
    suspend fun getAllDiaries(): List<DiaryDTO>

    @POST("feedback")
    suspend fun addBlogFeedback(@Body body: BlogFeedbackRequestBody)

    @GET("feedback")
    suspend fun getBlogFeedback(@Body body: GetBlogFeedbackRequestBody) : List<BlogFeedbackDTO>
}