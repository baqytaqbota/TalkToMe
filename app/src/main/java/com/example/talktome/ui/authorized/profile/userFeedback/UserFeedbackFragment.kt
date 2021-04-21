package com.example.talktome.ui.authorized.profile.userFeedback

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.ui.authorized.viewBinders.FeedbackItemView
import com.example.talktome.utils.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.fragment_profile_feedback.*

class UserFeedbackFragment : BaseFragment<UserFeedbackViewModel>(UserFeedbackViewModel::class, R.layout.fragment_profile_feedback){

    private val multiAdapter = MultiTypeAdapter().apply {
        register(FeedbackItemView())
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

        feedbackRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = multiAdapter
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

        feedback.observe(viewLifecycleOwner, Observer {
            multiAdapter.apply {
                items = it
                notifyDataSetChanged()
            }
        })
    }
}