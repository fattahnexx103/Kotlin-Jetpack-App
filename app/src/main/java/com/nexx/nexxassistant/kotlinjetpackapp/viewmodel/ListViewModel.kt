package com.nexx.nexxassistant.kotlinjetpackapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item
import com.nexx.nexxassistant.kotlinjetpackapp.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    val itemsLiveData = MutableLiveData<List<Item>>()
    val loadErrorLiveData = MutableLiveData<Boolean>()

    private val apiService =  RetrofitService()
    private val disposable = CompositeDisposable() //rx java memory leak management

     fun refresh(){
         fetchFromRemote()

    }

    private fun fetchFromRemote(){
        loadErrorLiveData.value = true
        disposable.add(
            apiService.getItems()
                .subscribeOn(Schedulers.newThread()) //do the process in background thread
                .observeOn(AndroidSchedulers.mainThread()) //observe the results on the main thread
                .subscribeWith(object: DisposableSingleObserver<List<Item>>(){
                    override fun onSuccess(list: List<Item>) {
                        itemsLiveData.value = list
                        loadErrorLiveData.value = false
                    }

                    override fun onError(error: Throwable) {
                        loadErrorLiveData.value = true
                        Log.i("ERROR LOG : ", error.message )
                        error.printStackTrace()
                    } //what do you observe

                })
        )
    }

    //ViewModel method which handles cleanup
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}