package com.nexx.nexxassistant.kotlinjetpackapp.network

import com.nexx.nexxassistant.kotlinjetpackapp.model.Item
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitApi {

    //get method takes in where to get the data from
    @GET("DevTides/DogsApi/master/dogs.json")
    fun getApijson() : Single<List<Item>> //returns a single...single only gets observed once.

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getApijsonList() : List<Item> //we dont use this but if we didn't want to use rx java this would have been the call.
}