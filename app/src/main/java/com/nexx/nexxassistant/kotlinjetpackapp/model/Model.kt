package com.nexx.nexxassistant.kotlinjetpackapp.model

data class Item(

    //simple model class which maps to the properties in the api

    val breedId: String?,
    val dogBreed: String?,
    val lifeSpan: String?,
    val breedGroup: String?,
    val temperment: String?,
    val imageUrl : String?
)