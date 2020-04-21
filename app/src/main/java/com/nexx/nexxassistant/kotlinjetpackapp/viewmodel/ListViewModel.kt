package com.nexx.nexxassistant.kotlinjetpackapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item

class ListViewModel: ViewModel() {

    val itemsLiveData = MutableLiveData<List<Item>>()
    val loadErrorLiveData = MutableLiveData<Boolean>()

     fun refresh(){
        //create sample data here
        val Item1 = Item("1","Corgi","15 years", "breedGroup","stable","imageUrl")
        val Item2 = Item("2","Labrador","17 years", "breedGroup","friendly","imageUrl")
        val Item3 = Item("3", "BloodHound", "9 years", "breedGroup","seeking","imageUrl")
        val Item4 = Item("4","Shephard","25 years", "breedGroup","intelligent","imageUrl")
        val dogsList: ArrayList<Item> = arrayListOf<Item>(Item1,Item2,Item3,Item4)

        itemsLiveData.value = dogsList
        loadErrorLiveData.value = false
    }
}