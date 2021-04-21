package com.example.talktome.ui.authorized.doctors.main

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
import com.example.talktome.common.constants.ARGConstants.ARG_DOCTOR_DATA
import com.example.talktome.ui.authorized.viewBinders.DoctorsViewBinder
import com.example.talktome.utils.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.fragment_doctors.*

class DoctorsFragment :
    BaseFragment<DoctorsViewModel>(DoctorsViewModel::class, R.layout.fragment_doctors) {

    private val doctorsAdapter = MultiTypeAdapter().apply {
        register(
            DoctorsViewBinder(
                onDetailClick = {
                    val bundle = bundleOf(
                        ARG_DOCTOR_DATA to it
                    )
                    findNavController().navigate(R.id.action_from_doctors_to_doctors_detail, bundle)
                },
                getSessionClick = {
                    val bundle = bundleOf(
                        ARG_DOCTOR_DATA to it
                    )
                    findNavController().navigate(R.id.action_from_doctors_to_session, bundle)
                })
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }

    override fun setupView() {
        super.setupView()
        doctorsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = doctorsAdapter
            if (itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(48, 32))
        }
    }

    override fun setupEmptyView(isEmpty: Boolean) {
        super.setupEmptyView(isEmpty)
        emptyLayout.isVisible = isEmpty
        doctorsRecyclerView.isVisible = !isEmpty
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        doctors.observe(viewLifecycleOwner, Observer {
            doctorsAdapter.apply {
                items = it
                notifyDataSetChanged()
            }
        })
    }

}