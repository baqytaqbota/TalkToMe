package com.example.talktome.ui.authorized.diary.diaryDetail

import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants
import com.example.talktome.data.diary.model.DiaryDTO
import kotlinx.android.synthetic.main.fragment_diary_detail.*

class DiaryDetailFragment : BaseFragment<DiaryDetailViewModel>(DiaryDetailViewModel::class, R.layout.fragment_diary_detail){

    private val bundle : DiaryDTO?
        get() = arguments?.getParcelable(ARGConstants.ARG_DIARY_DETAIL)

    override fun setupView() {
        super.setupView()
        diaryTitle.text = bundle?.diaryTitle
        diaryDescription.text = bundle?.diaryDescription
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}