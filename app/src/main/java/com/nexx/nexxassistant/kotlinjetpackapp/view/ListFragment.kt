package com.nexx.nexxassistant.kotlinjetpackapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.nexx.nexxassistant.kotlinjetpackapp.R
import com.nexx.nexxassistant.kotlinjetpackapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel : ListViewModel
    private var itemListAdapter: ListAdapter = ListAdapter(arrayListOf()) //takes an empty list of

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewmodel setup
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh() //call the refresh model in listViewModel which updates the viewmodel with data

        //recyclerview setup
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemListAdapter
        }

        observeViewModel()
    }

    fun observeViewModel(){

        //observe the mutable live data of itemList
        viewModel.itemsLiveData.observe(this, Observer { items ->
            items?.let {
                recyclerView.visibility = View.VISIBLE
                itemListAdapter.updateList(it)
            }
        })

        //Observe the loading error  mutable live data
        viewModel.loadErrorLiveData.observe(this, Observer {isError ->
            isError?.let {
                if(it){
                    list_error.visibility = View.VISIBLE
                }else{
                    list_error.visibility = View.GONE
                }

            }
        })
    }

}
