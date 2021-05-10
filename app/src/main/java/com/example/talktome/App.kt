package com.example.talktome

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.cometchat.pro.core.AppSettings
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.uikit.ui_components.calls.call_manager.listener.CometChatCallListener
import com.example.talktome.common.constants.ChatConfig.APP_ID
import com.example.talktome.common.constants.ChatConfig.REGION
import com.example.talktome.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(appModule, viewModelModule, useCaseModule, repositoryModule, networkModule)
        }

        initCometChat()
    }

    private fun initCometChat() {
        val setting =
            AppSettings.AppSettingsBuilder()
                .subscribePresenceForAllUsers()
                .setRegion(REGION)
                .build()

        CometChat.init(this, APP_ID, setting, object : CometChat.CallbackListener<String>() {
            override fun onSuccess(p0: String?) {
                Log.d("TAGGED", "Initialization completed successfully")
            }

            override fun onError(p0: CometChatException?) {
                Log.d("TAGGED", "Initialization failed with exception: " + p0?.message)
            }

        })

        addConnectionListener(TAG)
        CometChatCallListener.addCallListener(TAG, this)
        createNotificationChannel()
    }

    private fun addConnectionListener(tag: String) {
        CometChat.addConnectionListener(tag, object : CometChat.ConnectionListener {
            override fun onConnected() {
                Toast.makeText(baseContext, "Connected", Toast.LENGTH_SHORT).show()
            }

            override fun onConnecting() {}
            override fun onDisconnected() {
                Toast.makeText(
                    baseContext, "You connection has been broken with server." +
                            "Please wait for a minute or else restart the app.", Toast.LENGTH_LONG
                ).show()
            }

            override fun onFeatureThrottled() {

            }
        })
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.app_name)
            val description = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("2", name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        CometChatCallListener.removeCallListener(TAG)
        CometChat.removeConnectionListener(TAG)
    }

    companion object {
        private const val TAG = "UIKitApplication"
    }
}