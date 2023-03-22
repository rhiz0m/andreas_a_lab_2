package com.rhiz0me.myapplication.model

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    //Inside Call function, define a List after getting data from the REST API. Type of list is the model Class

    //Get annotation.
    @GET("/posts")
    fun getAllPosts() : Call<List<Posts>>

}