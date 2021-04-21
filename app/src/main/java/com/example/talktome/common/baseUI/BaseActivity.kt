package com.example.talktome.common.baseUI

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseActivity<T: BaseViewModel>(clazz: KClass<T>, layout: Int) : AppCompatActivity(layout){

    val viewModel by viewModel(clazz = clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setOnClickListeners()
        viewModel.initRole()
        observeViewModel()
    }

    open fun observeViewModel() = with(viewModel){
        error.observe(this@BaseActivity, Observer {
            Toast.makeText(this@BaseActivity, it, Toast.LENGTH_SHORT).show()
        })
        loading.observe(this@BaseActivity, Observer {
            loaderState(it)
        })
        empty.observe(this@BaseActivity, Observer {
            setupEmptyView(it)
        })
        initDoctorRole.observe(this@BaseActivity, Observer {
            setupDoctorView()
        })
        initPatientRole.observe(this@BaseActivity, Observer {
            setupPatientView()
        })
    }

    open fun setupEmptyView(isEmpty: Boolean){
        //do nothing
    }

    open fun setupView(){
        //do nothing
    }

    open fun setOnClickListeners(){
        //do nothing
    }

    open fun setupPatientView(){
        //do nothing
    }

    open fun setupDoctorView(){
        //do nothing
    }

    open fun loaderState(isVisible: Boolean){
        //do nothing
    }
}