package com.example.talktome.utils.extensions

import android.app.Activity
import android.content.Intent
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.models.User
import com.cometchat.pro.uikit.ui_components.messages.message_list.CometChatMessageListActivity
import com.cometchat.pro.uikit.ui_resources.constants.UIKitConstants

fun Activity.startChatIntent(user: User){
    val intent = Intent(this, CometChatMessageListActivity::class.java)
    intent.putExtra(UIKitConstants.IntentStrings.UID, user.uid)
    intent.putExtra(UIKitConstants.IntentStrings.AVATAR, user.avatar)
    intent.putExtra(UIKitConstants.IntentStrings.STATUS, user.status)
    intent.putExtra(UIKitConstants.IntentStrings.NAME, user.name)
    intent.putExtra(UIKitConstants.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER)
    startActivity(intent)
}