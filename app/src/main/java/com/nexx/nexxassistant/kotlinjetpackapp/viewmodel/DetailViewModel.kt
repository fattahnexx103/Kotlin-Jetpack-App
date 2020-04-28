package com.nexx.nexxassistant.kotlinjetpackapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexx.nexxassistant.kotlinjetpackapp.database.ItemDatabase
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) { //view model needs the context for database ops

    val detailItemLiveData  = MutableLiveData<Item>()

    fun update(uuid: Int){
       launch {
           val detailItem = ItemDatabase(getApplication()).getItemDao().getItem(uuid) //get the items from the database
           detailItemLiveData.value  = detailItem // set the live data in detail screen.
       }
    }
}