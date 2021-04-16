package com.example.talktome.common.baseUI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseActivity<T: BaseViewModel>(clazz: KClass<T>, layout: Int) : AppCompatActivity(layout){

    val viewModel by viewModel(clazz = clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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