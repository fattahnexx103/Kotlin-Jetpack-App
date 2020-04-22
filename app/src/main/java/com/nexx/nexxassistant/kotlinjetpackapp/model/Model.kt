package com.nexx.nexxassistant.kotlinjetpackapp.model

import com.google.gson.annotations.SerializedName

data class Item(

    //simple model class which maps to the properties in the api
    //if variable name is same as api we dont have to put serialized name

    //Serialized name is name of the key in the api
    @SerializedName("id")
    val breedId: String?,

    @SerializedName("name")
    val dogBreed: String?,

    @SerializedName("life_span")
    val lifeSpan: String?,

    @SerializedName("breed_group")
    val breedGroup: String?,

    @SerializedName("temperament")
    val temperment: String?,

    @SerializedName("url")
    val imageUrl : String?
)