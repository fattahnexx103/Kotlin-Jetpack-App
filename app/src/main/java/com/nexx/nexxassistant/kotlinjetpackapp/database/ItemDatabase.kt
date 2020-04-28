package com.nexx.nexxassistant.kotlinjetpackapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item

//This is a room database class which takes in the class names from which it is making a database of
@Database(entities = arrayOf(Item::class), version = 1)
abstract class ItemDatabase : RoomDatabase(){ //class needs to be abstract


    abstract fun getItemDao(): ItemDao //gets the itemDao

    //making a singleton instance of the room database
    companion object {
        @Volatile private  var instance: ItemDatabase? = null // initally the database is null
        private val LOCK = Any() // can run on any one of the background threads

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){ //is there an existing instance? , if not build it
            //synchronized(LOCK) means only that thread can access it
            //is the instance there, if not build it
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        //This is the database build class which takes in the context
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, //gets the application context
            ItemDatabase::class.java, // class name of the databse
            "itemDatabase" // name of the database
        ).build()
    }

}