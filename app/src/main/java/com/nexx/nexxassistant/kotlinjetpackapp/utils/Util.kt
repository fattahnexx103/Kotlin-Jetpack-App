package com.nexx.nexxassistant.kotlinjetpackapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nexx.nexxassistant.kotlinjetpackapp.R

// gives the spinner on the imageView during loading of item
fun getProgressDrawable(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()

    }
}

//we are adding a new function to the ImageView class
fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round) // if there is an error with the image

    //Use glide to load the image
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}