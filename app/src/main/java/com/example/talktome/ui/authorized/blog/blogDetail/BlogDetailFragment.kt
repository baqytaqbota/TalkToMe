package com.example.talktome.ui.authorized.blog.blogDetail

import android.app.AlertDialog
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants
import com.example.talktome.common.constants.ARGConstants.ARG_BLOG_DATA
import com.example.talktome.data.blog.model.BlogItemDTO
import com.example.talktome.ui.unauthorized.ui.UnauthorizedActivity
import kotlinx.android.synthetic.main.fragment_blog_detail.*

class BlogDetailFragment : BaseFragment<BlogDetailViewModel>(BlogDetailViewModel::class, R.layout.fragment_blog_detail){

    private val blogData: BlogItemDTO
        get() = arguments?.getParcelable<BlogItemDTO>(ARG_BLOG_DATA) as BlogItemDTO

    override fun setupView() {
        super.setupView()
        toolbarView.title = blogData.title
        description.text = blogData.content

        val isAuthor = blogData.author == viewModel.getUserId()
        doctorManagePanel.isVisible = isAuthor
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        toolbarView.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        editBlog.setOnClickListener {
            val bundle = bundleOf(
                ARG_BLOG_DATA to blogData
            )
            findNavController().navigate(R.id.action_from_blog_detail_to_add_blog, bundle)
        }
        deleteBlog.setOnClickListener {
            showDialog()
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        deleteSuccess.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }

    private fun showDialog() {
        AlertDialog.Builder(context)
            .setMessage("Вы точно хотите удалить?")
            .setPositiveButton("Да") { dialog, _ ->
                viewModel.onDeleteClicked(blogData)
                dialog.dismiss()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}