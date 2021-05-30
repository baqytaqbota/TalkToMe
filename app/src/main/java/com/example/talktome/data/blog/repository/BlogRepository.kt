package com.example.talktome.data.blog.repository

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.networkUtils.safeApiCall
import com.example.talktome.data.blog.model.*

class BlogRepository(
    private val apiService: ApiService
){

    suspend fun getAllBlogs() = safeApiCall {
        apiService.getAllBlogs()
    }

    suspend fun createBlog(param : NewBlogRequestBody) = safeApiCall {
        apiService.createBlog(param)
    }

    suspend fun getDoctorBlogs() = safeApiCall {
        apiService.getDoctorBlogs()
    }

    suspend fun getBlogById(param: BlogByIdRequestBody) = safeApiCall {
        apiService.getBlogById(param.id)
    }

    suspend fun updateBlog(param: BlogUpdateDeleteRequestBody) = safeApiCall {
        apiService.updateBlog(param)
    }

    suspend fun deleteBlog(param: BlogUpdateDeleteRequestBody) = safeApiCall {
        apiService.deleteBlogs(param)
    }

    suspend fun addFeedback(body: BlogFeedbackRequestBody) = safeApiCall {
        apiService.addBlogFeedback(body)
    }

    suspend fun getBlogFeedback(body: GetBlogFeedbackRequestBody)  = safeApiCall {
        apiService.getBlogFeedback(body)
    }
}
