package com.nexx.nexxassistant.kotlinjetpackapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesHelper {

    companion object{

        private  const val  PREF_TIME = "Pref Time"
        private var prefs: SharedPreferences? = null

        @Volatile private  var instance: SharedPreferencesHelper? = null
        private var LOCK = Any()

        operator fun invoke(context: Context): SharedPreferencesHelper = instance?: synchronized(LOCK){
            instance?: buildHelper(context).also{
                instance = it
            }
        }

        //Create a singleton instance
        private fun buildHelper(context: Context): SharedPreferencesHelper{
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }

    //function to save the time at which insertion occured
    fun saveUpdateTime(time: Long){
        prefs?.edit(commit = true){
            putLong(PREF_TIME, time)
        }
    }

    //get the updated time
    fun getUpdateTime() = prefs?.getLong(PREF_TIME,0)

}