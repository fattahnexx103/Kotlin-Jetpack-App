package com.nexx.nexxassistant.kotlinjetpackapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item

//This is the Room Dao class
@Dao
interface ItemDao {

    //The insert function for room with @Insert
    //Takes in as many Item objects it wants to as arg with the vararg data type
    //Returns a List of Primary Keys which is the uuid in the Item Class
    //suspend makes the function run on the seperate thread
    @Insert
    suspend fun insertAll(vararg items: Item) :  List<Long>

    //Takes in the Query which gives a list of items
    //The function returns a list of items
    @Query("SELECT * FROM room_table")
    suspend fun getAllItems(): List<Item>

    //Takes in Query to return the Item with uuid which is passed as arg
    //itemId is passed in and the function returns an Item object
    @Query("SELECT * FROM room_table WHERE uuid = :itemId")
    suspend fun getItem(itemId: Int): Item

    //Delete query which deletes everything from the table
    @Query("DELETE FROM room_table")
    suspend fun deleteAll()
}