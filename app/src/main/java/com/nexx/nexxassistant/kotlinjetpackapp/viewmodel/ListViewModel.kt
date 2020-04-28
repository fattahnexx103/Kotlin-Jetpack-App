package com.nexx.nexxassistant.kotlinjetpackapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexx.nexxassistant.kotlinjetpackapp.database.ItemDatabase
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item
import com.nexx.nexxassistant.kotlinjetpackapp.network.RetrofitService
import com.nexx.nexxassistant.kotlinjetpackapp.utils.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): BaseViewModel(application) {

    //Mutable Live Data which gets observed
    val itemsLiveData = MutableLiveData<List<Item>>()
    val loadErrorLiveData = MutableLiveData<Boolean>()

    private val apiService =  RetrofitService()
    private val disposable = CompositeDisposable() //rx java memory leak management
    private var preferencesHelper = SharedPreferencesHelper(getApplication()) //for shared preferences to store the time
    private var refreshTIme = 5* 60 * 1000 *1000 * 1000L // 5000 seconds ... after that it makes the api call

     fun refresh(){
         val updateTime =preferencesHelper.getUpdateTime() //we get the time

         //if the system time - the update time is less than refresh time then get from the database
         //so say system got data from db at 5:12:04 from api and its 5:12:6 so diff is less than 5 secs so get from database
         if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTIme){
             fetchFromDatabase()
         }else{
             fetchFromRemote()
         }

    }

    //function to fetch from the database
    private fun fetchFromDatabase(){
        launch {
            val itemsListDb: List<Item> = ItemDatabase(getApplication()).getItemDao().getAllItems() //get all the items
            itemsRetrieved(itemsListDb) //set the live data
            Toast.makeText(getApplication(), "Items Retreived from database", Toast.LENGTH_SHORT).show()
        }
    }

    //function to retrieve from remote api
    private fun fetchFromRemote(){
        loadErrorLiveData.value = true
        disposable.add(
            apiService.getItems()
                .subscribeOn(Schedulers.newThread()) //do the process in background thread
                .observeOn(AndroidSchedulers.mainThread()) //observe the results on the main thread
                .subscribeWith(object: DisposableSingleObserver<List<Item>>(){
                    override fun onSuccess(list: List<Item>) {
                      storeItemsLocally(list) //update the live data
                        Toast.makeText(getApplication(), "Items Retreived from API", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: Throwable) {
                        loadErrorLiveData.value = true
                        Log.i("ERROR LOG : ", error.message )
                        error.printStackTrace()
                    } //what do you observe

                })
        )
    }

    //method sets the live data
    private fun itemsRetrieved(itemList: List<Item>){
        itemsLiveData.value = itemList
        loadErrorLiveData.value = false
    }

    //method stores in the local db
    private fun storeItemsLocally(itemList: List<Item>){
            launch {
                val dao = ItemDatabase(getApplication()).getItemDao() //get the dao from the database class
                dao.deleteAll() //delete all the entries first
                val results = dao.insertAll(*itemList.toTypedArray()) //breaks each item in itemList to an individual Item
                var i = 0 //set the list id to the uuid of the results
                while (i < itemList.size){
                    itemList[i].uuid = results[i].toInt() // we converting the long to int for the results
                    i++
                }
                itemsRetrieved(itemList)
            }
        preferencesHelper.saveUpdateTime(System.nanoTime()) // store the time in shared preferences when inserted in db
    }

    //ViewModel method which handles cleanup
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}