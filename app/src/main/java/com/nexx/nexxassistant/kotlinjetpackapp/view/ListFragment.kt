package com.nexx.nexxassistant.kotlinjetpackapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.nexx.nexxassistant.kotlinjetpackapp.R
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_floatingActionButton.setOnClickListener {

            //action is the generated direction class
            val actions = ListFragmentDirections.actionListFragmentToDetailFragment()
            actions.detailId = 5; //detailId is an int arguement that listFragment takes and we pass on.
            Navigation.findNavController(it).navigate(actions) // you can also pass in view instead of it
        }
    }

}
