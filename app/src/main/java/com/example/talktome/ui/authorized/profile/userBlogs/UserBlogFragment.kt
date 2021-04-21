package com.example.talktome.ui.authorized.profile.userBlogs

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
import com.example.talktome.ui.authorized.blog.main.viewBinders.BlogItemViewBinder
import com.example.talktome.utils.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.fragment_profile_blogs.*

class UserBlogFragment :
    BaseFragment<UserBlogViewModel>(UserBlogViewModel::class, R.layout.fragment_profile_blogs) {

    private val multiTypeAdapter = MultiTypeAdapter().apply {
        register(BlogItemViewBinder {
            val bundle = bundleOf(
                ARGConstants.ARG_BLOG_DATA to it
            )
            findNavController().navigate(R.id.action_from_profile_blog_to_blog_detail, bundle)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
    }

    override fun setupEmptyView(isEmpty: Boolean) {
        super.setupEmptyView(isEmpty)
        emptyLayout.isVisible = isEmpty
    }

    override fun setupView() {
        super.setupView()

        blogsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = multiTypeAdapter
            if (itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(48, 32))
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()

        backIcon.setOnClickListener {
            findNavController().popBackStack()
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