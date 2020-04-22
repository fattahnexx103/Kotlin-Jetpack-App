package com.nexx.nexxassistant.kotlinjetpackapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item

class DetailViewModel : ViewModel() {

    val detailItemLiveData  = MutableLiveData<Item>()

    fun update(){
        val dogItem = Item("1", "Detail Dog", "10 years","breedGroup","stable","uri")
        detailItemLiveData.value = dogItem
    }
}