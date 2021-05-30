package com.example.talktome.ui.authorized.blog.blogDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.blog.model.BlogFeedbackDTO
import com.example.talktome.data.blog.model.BlogFeedbackRequestBody
import com.example.talktome.data.blog.model.BlogItemDTO
import com.example.talktome.data.blog.model.BlogUpdateDeleteRequestBody
import com.example.talktome.domain.authorizedUser.GetUserIdUseCase
import com.example.talktome.domain.blog.AddBlogFeedbackUseCase
import com.example.talktome.domain.blog.GetBlogFeedbackUseCase
import com.example.talktome.domain.blog.MakeDeleteBlogUseCase

class BlogDetailViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val makeDeleteBlogUseCase: MakeDeleteBlogUseCase,
    private val addBlogFeedbackUseCase: AddBlogFeedbackUseCase,
    private val getBlogFeedbackUseCase: GetBlogFeedbackUseCase
) : BaseViewModel() {

    private val _blogData = MutableLiveData<Pair<BlogItemDTO?, Boolean>>()
    val blogData: LiveData<Pair<BlogItemDTO?, Boolean>>
        get() = _blogData

    private val _deleteSuccess = MutableLiveData<Unit>()
    val deleteSuccess: LiveData<Unit>
        get() = _deleteSuccess

    private val _feedback = MutableLiveData<List<BlogFeedbackDTO>>()
    val feedback: LiveData<List<BlogFeedbackDTO>>
        get() = _feedback

    fun getBlogFeedback(blogId: String){
        getBlogFeedbackUseCase.request(
            params = blogId,
            onResult = {
                _feedback.value = it
            },
            loading = _loading,
            error = _error
        )
    }

    fun onDeleteClicked(itemDTO: BlogItemDTO?){
        val request = BlogUpdateDeleteRequestBody(
            id = itemDTO?._id.orEmpty(),
            title = itemDTO?.title.orEmpty(),
            content = itemDTO?.content.orEmpty(),
            author = itemDTO?.author.orEmpty(),
            image = itemDTO?.image.orEmpty()
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

    fun addFeedback(blogId: String, feedback: String){
        addBlogFeedbackUseCase.request(
            params = BlogFeedbackRequestBody(
                blogId,
                feedback
            ),
            onResult = {
                getBlogFeedback(blogId)
            },
            loading = _loading,
            error = _error
        )
    }
}