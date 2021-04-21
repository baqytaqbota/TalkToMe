package com.example.talktome.ui.authorized.blog.blogDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.blog.model.BlogItemDTO
import com.example.talktome.data.blog.model.BlogUpdateDeleteRequestBody
import com.example.talktome.domain.authorizedUser.GetUserIdUseCase
import com.example.talktome.domain.blog.MakeDeleteBlogUseCase

class BlogDetailViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val makeDeleteBlogUseCase: MakeDeleteBlogUseCase
) : BaseViewModel() {

    private val _blogData = MutableLiveData<Pair<BlogItemDTO?, Boolean>>()
    val blogData: LiveData<Pair<BlogItemDTO?, Boolean>>
        get() = _blogData

    private val _deleteSuccess = MutableLiveData<Unit>()
    val deleteSuccess: LiveData<Unit>
        get() = _deleteSuccess

    fun onDeleteClicked(itemDTO: BlogItemDTO){
        val request = BlogUpdateDeleteRequestBody(
            _id = itemDTO._id,
            title = itemDTO.title,
            content = itemDTO.content,
            author = itemDTO.author,
            createdAt = itemDTO.createdAt
        )
        makeDeleteBlogUseCase.request(
            params = request,
            onResult = {
                _deleteSuccess.value = Unit
            },
            loading = _loading,
            error = _error
        )
    }

    fun getUserId() = getUserIdUseCase.getUserId()
}