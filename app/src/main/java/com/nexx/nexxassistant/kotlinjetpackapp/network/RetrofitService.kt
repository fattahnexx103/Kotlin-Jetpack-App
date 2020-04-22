package com.nexx.nexxassistant.kotlinjetpackapp.network

import com.nexx.nexxassistant.kotlinjetpackapp.model.Item
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private val BASE_URL = "https://raw.githubusercontent.com"

    //Create a retrofit instance
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //use for json conversion
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //use for rx java
        .build()
        .create(RetrofitApi::class.java) //the api class

    //function to get list of items from api
    fun getItems(): Single<List<Item>>{
        return api.getApijson() //this is being used in the viewmodel disposable
    }
}