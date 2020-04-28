package com.nexx.nexxassistant.kotlinjetpackapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//This marks the class as a room table with table name = "room_table"
@Entity(tableName =  "room_table")
data class Item(

    //simple model class which maps to the properties in the api
    //if variable name is same as api we dont have to put serialized name

    //Serialized name is name of the key in the api
    //ColumnInfo is the name of the column in the room database table

    @ColumnInfo(name= "itemId")
    @SerializedName("id")
    val breedId: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val dogBreed: String?,

    @ColumnInfo(name = "lifespan")
    @SerializedName("life_span")
    val lifeSpan: String?,

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    val breedGroup: String?,


    @SerializedName("temperament")
    val temperment: String?,

    @ColumnInfo(name = "image_url")
    @SerializedName("url")
    val imageUrl : String?
){
    //the primary key for room
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0 // 0 by default
}