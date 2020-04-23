package com.nexx.nexxassistant.kotlinjetpackapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nexx.nexxassistant.kotlinjetpackapp.R
import com.nexx.nexxassistant.kotlinjetpackapp.model.Item
import com.nexx.nexxassistant.kotlinjetpackapp.utils.getProgressDrawable
import com.nexx.nexxassistant.kotlinjetpackapp.utils.loadImage
import kotlinx.android.synthetic.main.item_card.view.*

class ListAdapter (val itemList: ArrayList<Item>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

            //create the viewholder by passing in the view with the layout
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_card, parent, false)
            return ListViewHolder(view)
        }

        override fun getItemCount(): Int {
            return itemList.size //return the size
        }

        /* you can also do
        *   ovveride fun getItemCount() =  itemList.size
        * */

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

            //set the values in the view such as the text and image
            holder.view.item_name.text = itemList[position].dogBreed
            holder.view.item_lifespan.text = itemList[position].lifeSpan

            //load the image using the function we created for imageview in the util class
            holder.view.item_imageView.loadImage(itemList[position].imageUrl, getProgressDrawable(holder.view.item_imageView.context))

            //navigate to detail fragment
            holder.view.setOnClickListener {
                val actions = ListFragmentDirections.actionListFragmentToDetailFragment()
                Navigation.findNavController(it).navigate(actions)
            }

        }

        //method to update the list with the new data...this is called in the list fragment class
        fun updateList(newItemList: List<Item>){
            itemList.clear()
            itemList.addAll(newItemList)
            notifyDataSetChanged()
        }

    //create this class which serves as a default view holder class
    class ListViewHolder(var view: View): RecyclerView.ViewHolder(view){}


}