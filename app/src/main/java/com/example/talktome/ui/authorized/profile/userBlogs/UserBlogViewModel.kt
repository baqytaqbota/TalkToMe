package com.example.talktome.ui.authorized.profile.userBlogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.domain.blog.GetDoctorBlogsUseCase

class UserBlogViewModel(
    private val getDoctorBlogsUseCase: GetDoctorBlogsUseCase
) : BaseViewModel(){

    private val _blogs = MutableLiveData<List<Any>>()
    val blogs: LiveData<List<Any>>
        get() = _blogs

   fun onViewCreated(){
       getDoctorBlogsUseCase.request(
           params = Unit,
           onResult = {
               _empty.value = it.isNullOrEmpty()
               _blogs.value = it
           },
           loading = _loading,
           error = _error
       )
   }

}