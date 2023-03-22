package com.rhiz0me.myapplication.model

import com.google.gson.annotations.SerializedName

data class Posts (
    val userId: Int,
    val id: Int,
    val title: String,

    //renaming body to subtitle
    @SerializedName("body")
    val subtitle: String
        ) {
}