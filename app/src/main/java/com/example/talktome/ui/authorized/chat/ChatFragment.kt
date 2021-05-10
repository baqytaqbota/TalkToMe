package com.example.talktome.ui.authorized.chat

import com.cometchat.pro.models.Conversation
import com.cometchat.pro.models.User
import com.cometchat.pro.uikit.ui_components.chats.CometChatConversationList
import com.cometchat.pro.uikit.ui_resources.utils.item_clickListener.OnItemClickListener
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.utils.extensions.startChatIntent

class ChatFragment : BaseFragment<ChatViewModel>(ChatViewModel::class, R.layout.fragment_chat){


    override fun setOnClickListeners() {
        super.setOnClickListeners()

        CometChatConversationList.setItemClickListener(object : OnItemClickListener<Any>() {
            override fun OnItemClick(t: Any, position: Int) {
                val conversation = t as Conversation
                activity?.startChatIntent(conversation.conversationWith as User)
            }
        })
    }
}