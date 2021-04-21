package com.example.talktome.ui.authorized.blog.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.domain.authorizedUser.GetUserIdUseCase
import com.example.talktome.domain.blog.GetAllBlogsUseCase

class BlogViewModel(
    private val getAllBlogsUseCase: GetAllBlogsUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : BaseViewModel() {

    private val _blogs = MutableLiveData<List<Any>>()
    val blogs: LiveData<List<Any>>
        get() = _blogs

    fun onViewCreated() {
        getAllBlogsUseCase.request(
            params = Unit,
            onResult = {
                _empty.value = it?.isEmpty()
                _blogs.value = it
            },
            loading = _loading,
            error = _error
        )
    }
}