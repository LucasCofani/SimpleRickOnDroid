package com.example.rickandmortyandroid.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.example.rickandmortyandroid.R
import com.example.rickandmortyandroid.models.utils.MenuAppBar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    protected open var bottomNavigationViewVisibility = View.VISIBLE
    protected open var toobarVisibility = View.VISIBLE
    protected open var menuAppBar = MenuAppBar(share = false, favorite = false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // get the reference of the parent activity and call the setBottomNavigationVisibility method.
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
            setHasOptionsMenu(true)
            defaultLayout()
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toobarVisibility)
            mainActivity.resizeFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            defaultLayout()
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toobarVisibility)
            mainActivity.resizeFragment()
        }
    }
    private fun defaultLayout(){
        mainActivity.setBottomNavigationVisibility(View.VISIBLE)
        mainActivity.setToolbarVisibility(View.VISIBLE)
        mainActivity.resizeFragment()
    }
    protected fun sendMessage(msg :String){
        activity?.let { Snackbar.make(it.findViewById(R.id.container),"$msg",Snackbar.LENGTH_LONG).show() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.forEach { item ->
            when (item.title.toString().toLowerCase()) {
                "share" -> item.isVisible = menuAppBar.share
                "favorite" -> item.isVisible = menuAppBar.favorite
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}