package com.example.talktome.common.baseUI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel


open class BaseFragment<T: BaseViewModel>(clazz: KClass<T>, layout: Int) : Fragment(layout){

    val viewModel by viewModel(clazz = clazz)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setOnClickListeners()
        observeViewModel()
        viewModel.initRole()
    }


    open fun observeViewModel() = with(viewModel){
        error.observe(viewLifecycleOwner, Observer {
            //Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        loading.observe(viewLifecycleOwner, Observer {
            loaderState(it)
        })
        empty.observe(viewLifecycleOwner, Observer {
            setupEmptyView(it)
        })
        initDoctorRole.observe(viewLifecycleOwner, Observer {
            setupDoctorView()
        })
        initPatientRole.observe(viewLifecycleOwner, Observer {
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