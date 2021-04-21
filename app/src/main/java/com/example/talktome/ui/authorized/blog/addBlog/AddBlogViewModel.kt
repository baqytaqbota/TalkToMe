package com.example.talktome.ui.authorized.blog.addBlog

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.common.constants.ARGConstants
import com.example.talktome.common.constants.ARGConstants.ARG_BLOG_DATA
import com.example.talktome.data.blog.model.BlogItemDTO
import com.example.talktome.data.blog.model.BlogUpdateDeleteRequestBody
import com.example.talktome.data.blog.model.NewBlogRequestBody
import com.example.talktome.domain.authorizedUser.GetUserIdUseCase
import com.example.talktome.domain.blog.MakeCreateNewBlogUseCase
import com.example.talktome.domain.blog.MakeUpdateBlogUseCase

class AddBlogViewModel(
    private val makeCreateNewBlogUseCase: MakeCreateNewBlogUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val makeUpdateBlogUseCase: MakeUpdateBlogUseCase,
) : BaseViewModel() {

    private val _blogData = MutableLiveData<BlogItemDTO>()
    val blogData: LiveData<BlogItemDTO>
        get() = _blogData

    private val _redirect = MutableLiveData<Unit>()
    val redirect: LiveData<Unit>
        get() = _redirect

    private val _updateSuccess = MutableLiveData<Unit>()
    val updateSuccess: LiveData<Unit>
        get() = _updateSuccess


    fun onViewCreated(bundle: Bundle?){
        if(bundle!=null){
            if(bundle.containsKey(ARG_BLOG_DATA)){
                _blogData.value = bundle.getParcelable<BlogItemDTO>(ARG_BLOG_DATA) as BlogItemDTO
            }
        }
    }

    fun onCreateBlog(title: String, content: String) {
        makeCreateNewBlogUseCase.request(
            params = NewBlogRequestBody(
                title = title,
                content = content,
                image = "https://artismedia.by/blog/wp-content/uploads/2017/03/Psychology_for_Designers.jpg",
                author = getUserIdUseCase.getUserId(),
            ),
            onResult = {
                _redirect.value = Unit
            },
            loading = _loading,
            error = _error
        )
    }


    fun onUpdateBlog(itemDTO: BlogItemDTO?, title: String, content: String){
        val request = BlogUpdateDeleteRequestBody(
            _id = itemDTO?._id.orEmpty(),
            title = title,
            content = content,
            author = itemDTO?.author.orEmpty(),
            createdAt = itemDTO?.createdAt.orEmpty()
        )
        makeUpdateBlogUseCase.request(
            params = request,
            onResult = {
                _updateSuccess.value = Unit
            },
            loading = _loading,
            error = _error
        )
    }
}