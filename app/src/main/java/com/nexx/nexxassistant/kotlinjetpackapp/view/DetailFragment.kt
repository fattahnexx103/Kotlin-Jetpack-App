package com.nexx.nexxassistant.kotlinjetpackapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.nexx.nexxassistant.kotlinjetpackapp.R
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private var itemId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if arguements is not null
        arguments?.let {bundle ->
            itemId = DetailFragmentArgs.fromBundle(bundle).detailId //get bundle arguements from details fragment
            detail_textView.text = "Detail Fragment with $itemId"
        }

        detail_floatingActionButton.setOnClickListener {

            //action is the generated direction class
            val actions = DetailFragmentDirections.actionDetailFragmentToListFragment()
            Navigation.findNavController(it).navigate(actions) //you can also pass in view instead of it
        }
    }
}
