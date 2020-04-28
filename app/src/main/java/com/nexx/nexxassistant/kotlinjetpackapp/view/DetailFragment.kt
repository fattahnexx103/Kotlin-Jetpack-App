package com.nexx.nexxassistant.kotlinjetpackapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.nexx.nexxassistant.kotlinjetpackapp.R
import com.nexx.nexxassistant.kotlinjetpackapp.utils.getProgressDrawable
import com.nexx.nexxassistant.kotlinjetpackapp.utils.loadImage
import com.nexx.nexxassistant.kotlinjetpackapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private var itemId = 0 // this is the default value of the item id
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get the id from the details fragment which passes it with the action
        arguments?.let {
           itemId = DetailFragmentArgs.fromBundle(it).detailId
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.update(itemId) //the viewmodel needs the item id

        observeViewModel()

    }

    private fun observeViewModel(){

        viewModel.detailItemLiveData.observe(this, Observer { item ->
            item?.let {

                //update the views with data from the viewmodel
                detail_item_name.text = it.dogBreed
                detail_item_breed.text = it.breedGroup
                detail_item_lifespan.text = it.lifeSpan
                detail_item_temperment.text = it.temperment

                //if there is a context, only then can we update the image
                context?.let {context ->
                    detail_item_image.loadImage(it.imageUrl, getProgressDrawable(context))
                }
            }
        })
    }
}
