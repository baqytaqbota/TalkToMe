package com.example.talktome.ui.authorized.diary.diaries

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants.ARG_DIARY_DETAIL
import com.example.talktome.ui.authorized.diary.diaries.viewBinder.DiaryItemViewBinder
import com.example.talktome.utils.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.fragment_diaries.*
import kotlinx.android.synthetic.main.fragment_diaries.backIcon
import kotlinx.android.synthetic.main.fragment_diary_detail.*

class DiariesFragment : BaseFragment<DiariesViewModel>(DiariesViewModel::class, R.layout.fragment_diaries){

    private val multiTypeAdapter = MultiTypeAdapter().apply {
        register(DiaryItemViewBinder{
            val arg = bundleOf(
                ARG_DIARY_DETAIL to it
            )
            findNavController().navigate(R.id.action_to_diary_detail, arg)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllDiaries()
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun setupView() {
        super.setupView()
        diariesRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = multiTypeAdapter

            if(itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(48, 32))
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        diaries.observe(viewLifecycleOwner, Observer {
            multiTypeAdapter.apply {
                items = it
                notifyDataSetChanged()
            }
        })
    }
}