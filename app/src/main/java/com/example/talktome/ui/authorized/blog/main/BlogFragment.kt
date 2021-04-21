package com.example.talktome.ui.authorized.blog.main

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
import com.example.talktome.common.constants.ARGConstants.ARG_BLOG_DATA
import com.example.talktome.ui.authorized.blog.main.viewBinders.BlogItemViewBinder
import com.example.talktome.utils.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : BaseFragment<BlogViewModel>(BlogViewModel::class, R.layout.fragment_blog){

    private val multiTypeAdapter = MultiTypeAdapter().apply {
        register(BlogItemViewBinder{
            val bundle = bundleOf(
                ARG_BLOG_DATA to it
            )
            findNavController().navigate(R.id.action_from_blogs_to_blog_detail_fragment, bundle)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.onViewCreated()
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun setupDoctorView() {
        super.setupDoctorView()
        addBlogFab.isVisible = true
    }

    override fun setupEmptyView(isEmpty: Boolean) {
        super.setupEmptyView(isEmpty)
        emptyLayout.isVisible = isEmpty
        blogRecyclerView.isVisible = !isEmpty
    }

    override fun setupView() {
        super.setupView()
        blogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = multiTypeAdapter
            if(itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(48, 32))
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        addBlogFab.setOnClickListener {
            findNavController().navigate(R.id.action_from_blogs_to_add_blog_fragment)
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        blogs.observe(viewLifecycleOwner, Observer {
            multiTypeAdapter.apply {
                items = it
                notifyDataSetChanged()
            }
        })
    }
}