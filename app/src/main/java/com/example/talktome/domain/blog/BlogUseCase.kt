package com.example.talktome.domain.blog

import com.example.talktome.common.baseUseCase.BaseUseCase
import com.example.talktome.common.network.networkUtils.ResultApi
import com.example.talktome.data.blog.model.*
import com.example.talktome.data.blog.repository.BlogRepository


class MakeCreateNewBlogUseCase(
    private val repo: BlogRepository
): BaseUseCase<NewBlogRequestBody, Unit>(){

    override suspend fun run(params: NewBlogRequestBody, response: (ResultApi<Unit>) -> Unit) {
        response.invoke(
            repo.createBlog(params)
        )
    }
}

class GetAllBlogsUseCase(
    private val repo: BlogRepository
): BaseUseCase<Unit, List<BlogItemDTO>>(){

    override suspend fun run(params: Unit, response: (ResultApi<List<BlogItemDTO>>) -> Unit) {
        response.invoke(
            repo.getAllBlogs()
        )
    }
}

class GetDoctorBlogsUseCase(
    private val repo: BlogRepository
): BaseUseCase<Unit, List<BlogItemDTO>>(){

    override suspend fun run(params: Unit, response: (ResultApi<List<BlogItemDTO>>) -> Unit) {
        response.invoke(
            repo.getDoctorBlogs()
        )
    }
}

class GetBlogsByIdUseCase(
    private val repo: BlogRepository
): BaseUseCase<String, BlogItemDTO>(){

    override suspend fun run(params: String, response: (ResultApi<BlogItemDTO>) -> Unit) {
        response.invoke(
            repo.getBlogById(
                BlogByIdRequestBody(params)
            )
        )
    }
}

class MakeUpdateBlogUseCase(
    private val repo: BlogRepository
): BaseUseCase<BlogUpdateDeleteRequestBody, Unit>(){

    override suspend fun run(params: BlogUpdateDeleteRequestBody, response: (ResultApi<Unit>) -> Unit) {
        response.invoke(
            repo.updateBlog(params)
        )
    }
}

class MakeDeleteBlogUseCase(
    private val repo: BlogRepository
): BaseUseCase<BlogUpdateDeleteRequestBody, Unit>(){

    override suspend fun run(params: BlogUpdateDeleteRequestBody, response: (ResultApi<Unit>) -> Unit) {
        response.invoke(
            repo.deleteBlog(params)
        )
    }
}