package com.example.talktome.data.blog.repository

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.networkUtils.safeApiCall
import com.example.talktome.data.blog.model.BlogByIdRequestBody
import com.example.talktome.data.blog.model.BlogUpdateDeleteRequestBody
import com.example.talktome.data.blog.model.NewBlogRequestBody

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
}
