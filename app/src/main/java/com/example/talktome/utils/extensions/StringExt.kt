package com.example.talktome.utils.extensions

fun getTagsFromList(tagList: List<String>): String{
    var tags = ""

    tagList.forEach {
        tags = "$tags#$it"
    }
    return tags
}