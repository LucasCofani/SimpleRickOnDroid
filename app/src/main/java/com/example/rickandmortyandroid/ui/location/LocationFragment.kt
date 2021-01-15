package com.example.rickandmortyandroid.ui.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyandroid.R
import com.example.rickandmortyandroid.databinding.FragmentEpisodeBinding
import com.example.rickandmortyandroid.databinding.FragmentLocationBinding
import com.example.rickandmortyandroid.ui.BaseFragment
import com.example.rickandmortyandroid.ui.MenuItems

class LocationFragment : BaseFragment() {

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationBinding.inflate(inflater,container,false)
        return binding.root
    }
}