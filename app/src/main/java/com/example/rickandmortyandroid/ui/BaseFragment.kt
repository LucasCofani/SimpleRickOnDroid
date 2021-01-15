package com.example.rickandmortyandroid.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rickandmortyandroid.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    protected open var bottomNavigationViewVisibility = View.VISIBLE
    protected open var toobarVisibility = View.VISIBLE
    protected open var menuItemVisibility = MenuItems(chars = true, episode = true, location = true)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // get the reference of the parent activity and call the setBottomNavigationVisibility method.
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
            defaultLayout()
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toobarVisibility)
            mainActivity.setNavDrawerVisibility(menuItemVisibility)
            mainActivity.resizeFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            defaultLayout()
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toobarVisibility)
            mainActivity.setNavDrawerVisibility(menuItemVisibility)
            mainActivity.resizeFragment()
        }
    }
    private fun defaultLayout(){
        mainActivity.setBottomNavigationVisibility(View.VISIBLE)
        mainActivity.setToolbarVisibility(View.VISIBLE)
        mainActivity.setNavDrawerVisibility( MenuItems(chars = true, episode = true, location = true))
        mainActivity.resizeFragment()
    }
    protected fun sendMessage(msg :String){
        activity?.let { Snackbar.make(it.findViewById(R.id.container),"$msg",Snackbar.LENGTH_LONG).show() }
    }
}