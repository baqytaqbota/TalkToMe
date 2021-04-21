package com.example.talktome.ui.authorized.blog.addBlog

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants.ARG_BLOG_DATA
import com.example.talktome.data.blog.model.BlogItemDTO
import kotlinx.android.synthetic.main.fragment_add_blog.*

class AddBlogFragment :
    BaseFragment<AddBlogViewModel>(AddBlogViewModel::class, R.layout.fragment_add_blog) {

    private var blog: BlogItemDTO? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(arguments)
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
        continueButton.isClickable = !isVisible
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()

        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        continueButton.setOnClickListener {
            fieldValidation()
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        blogData.observe(viewLifecycleOwner, Observer {
            blog = it
            blogTitleEditText.text = Editable.Factory.getInstance().newEditable(it.title)
            blogContentEditText.text = Editable.Factory.getInstance().newEditable(it.content)
        })
        redirect.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
        updateSuccess.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack(R.id.blogDetailFragment, true)
        })
    }

    private fun fieldValidation() {
        var isChecked = false

        when {
            blogTitleEditText.text.toString().isNullOrEmpty() -> {
                isChecked = false
                blogTitleEditText.error = "Поле не должно быть пустым"
            }
            blogContentEditText.text.toString().isNullOrEmpty() -> {
                isChecked = false
                blogContentEditText.error = "Поле не должно быть пустым"
            }
            else -> {
                isChecked = true
            }
        }

        if (isChecked) {
            if(blog!=null){
                viewModel.onUpdateBlog(
                    blog,
                    blogTitleEditText.text.toString(),
                    blogContentEditText.text.toString()
                )
            }else {
                viewModel.onCreateBlog(
                    blogTitleEditText.text.toString(),
                    blogContentEditText.text.toString()
                )
            }
        }
    }

}