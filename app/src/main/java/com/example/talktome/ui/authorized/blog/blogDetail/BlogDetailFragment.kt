package com.example.talktome.ui.authorized.blog.blogDetail

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants
import com.example.talktome.common.constants.ARGConstants.ARG_BLOG_DATA
import com.example.talktome.data.blog.model.BlogItemDTO
import com.example.talktome.ui.authorized.blog.blogDetail.viewBinders.BlogFeedbackViewBinder
import com.example.talktome.ui.unauthorized.ui.UnauthorizedActivity
import com.example.talktome.utils.decorator.VerticalItemDecorator
import com.example.talktome.utils.extensions.doOnTextChanged
import kotlinx.android.synthetic.main.fragment_blog_detail.*

class BlogDetailFragment : BaseFragment<BlogDetailViewModel>(BlogDetailViewModel::class, R.layout.fragment_blog_detail){

    private val blogData: BlogItemDTO
        get() = arguments?.getParcelable<BlogItemDTO>(ARG_BLOG_DATA) as BlogItemDTO

    private val multiTypeAdapter = MultiTypeAdapter().apply {
        register(BlogFeedbackViewBinder())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBlogFeedback(blogData._id)
    }

    override fun setupPatientView() {
        super.setupPatientView()
        firstDivider.visibility = View.VISIBLE
        addFeedbackLabel.visibility = View.VISIBLE
        addFeedbackEditText.visibility = View.VISIBLE
        feedbackSaveBtn.visibility = View.VISIBLE
    }

    override fun setupView() {
        super.setupView()
        toolbarView.title = blogData.title
        description.text = blogData.content

        val isAuthor = blogData.authorId == viewModel.getUserId()
        doctorManagePanel.isVisible = isAuthor

        feedbackList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = multiTypeAdapter
            if(itemDecorationCount == 0) {
                addItemDecoration(VerticalItemDecorator(48, 32))
            }
        }
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
        feedbackSaveBtn.setOnClickListener {
            viewModel.addFeedback(blogData._id, addFeedbackEditText.text.toString())
            addFeedbackEditText.setText("")
        }
        addFeedbackEditText.doOnTextChanged { text, start, before, count ->
            if(count>0){
                feedbackSaveBtn.setBackgroundColor(resources.getColor(R.color.baseItemColor))
                feedbackSaveBtn.isClickable = true
            }
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        deleteSuccess.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
        feedback.observe(viewLifecycleOwner, Observer {
            multiTypeAdapter.apply {
                items = it
                notifyDataSetChanged()
            }
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