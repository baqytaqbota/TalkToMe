package com.example.talktome.ui.launcher.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseActivity
import com.example.talktome.ui.unauthorized.ui.UnauthorizedActivity

class LauncherActivity : BaseActivity<LauncherViewModel>(LauncherViewModel::class,R.layout.activity_launcher){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            val intent = Intent(this, UnauthorizedActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000L)
    }
}