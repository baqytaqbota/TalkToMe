package com.example.talktome.common.baseUI

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel


open class BaseFragment<T: BaseViewModel>(clazz: KClass<T>, layout: Int) : Fragment(layout){

    private val viewModel by viewModel(clazz = clazz)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setOnClickListeners()
        observeViewModel()
    }

    open fun setupView(){

    }

    open fun setOnClickListeners(){

    }

    open fun observeViewModel() = with(viewModel){

    }
}