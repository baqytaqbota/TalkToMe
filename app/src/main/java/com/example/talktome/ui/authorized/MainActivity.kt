package com.example.talktome.ui.authorized

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<MainActivityViewModel>(MainActivityViewModel::class, R.layout.activity_main) {

    override fun setupDoctorView() {
        super.setupDoctorView()
        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.nav_doctor_menu)
        val navController = findNavController(R.id.authorizedNav)
        bottomNavigationView.setupWithNavController(navController)
        setNavView(navController)
        viewModel.loginForChat()
    }

    override fun setupPatientView() {
        super.setupPatientView()
        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.nav_patient_menu)
        val navController = findNavController(R.id.authorizedNav)
        bottomNavigationView.setupWithNavController(navController)
        setNavView(navController)
        viewModel.loginForChat()
    }

    private fun setNavView(navController: NavController){
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.mainFragment->showBottomNav()
                R.id.doctorsFragment->showBottomNav()
                R.id.blogFragment->showBottomNav()
                R.id.chatFragment->showBottomNav()
                R.id.profileFragment->showBottomNav()
                else->hideBottomNav()
            }
        }
    }

    private fun showBottomNav(){
        bottomNavigationView.visibility= View.VISIBLE
    }

    private fun hideBottomNav(){
        bottomNavigationView.visibility=View.GONE
    }
}