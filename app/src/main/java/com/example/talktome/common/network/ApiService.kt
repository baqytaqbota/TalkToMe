package com.example.talktome.common.network

import com.example.talktome.data.authorization.model.RegisterRequestBody
import com.example.talktome.data.authorization.model.AuthResultDTO
import com.example.talktome.data.authorization.model.LoginRequestBody
import com.example.talktome.data.blog.model.*
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.data.doctors.model.DoctorListRequestBody
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun makeLogin(@Body request: LoginRequestBody) : AuthResultDTO

    @POST("patients/signup")
    suspend fun makeRegister(@Body request: RegisterRequestBody): AuthResultDTO

    @POST("doctors")
    suspend fun getDoctors(@Body request: DoctorListRequestBody) : List<DoctorListDTO>

    @POST("blogs")
    suspend fun createBlog(@Body request: NewBlogRequestBody): Unit

    @GET("blogs/all")
    suspend fun getAllBlogs(): List<BlogItemDTO>

    @GET("blogs/doctor")
    suspend fun getDoctorBlogs() : List<BlogItemDTO>

    @GET("blogs")
    suspend fun getBlogById(@Body id: String): BlogItemDTO

    @PUT("blogs")
    suspend fun updateBlog(@Body request: BlogUpdateDeleteRequestBody): Unit

    @DELETE("blogs")
    suspend fun deleteBlogs(@Body request: BlogUpdateDeleteRequestBody): Unit


}