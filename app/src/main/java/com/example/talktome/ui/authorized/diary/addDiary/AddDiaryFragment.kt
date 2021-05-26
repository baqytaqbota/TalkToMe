package com.example.talktome.ui.authorized.diary.addDiary

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_diary.*
import kotlinx.android.synthetic.main.fragment_add_diary.backIcon
import kotlinx.android.synthetic.main.fragment_diaries.*
import kotlinx.android.synthetic.main.fragment_login.*

class AddDiaryFragment : BaseFragment<AddDiaryViewModel>(AddDiaryViewModel::class, R.layout.fragment_add_diary){

    override fun setOnClickListeners() {
        super.setOnClickListeners()

        addDiaryBtn.setOnClickListener {
            fieldValidation()
        }

        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        close.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }

    private fun fieldValidation() {
        var isChecked = false

        when{
            diaryTitle.text.toString().isNullOrEmpty() -> {
                isChecked = false
                diaryTitle.error = "Поле не должно быть пустым"
            }
            diaryDescription.text.toString().isNullOrEmpty() -> {
                isChecked = false
                diaryDescription.error = "Поле не должно быть пустым"
            }
            else -> {
                isChecked = true
            }
        }

        if(isChecked) {
            viewModel.addDiary(
                diaryTitle.text.toString(),
                diaryDescription.text.toString()
            )
        }
    }

}