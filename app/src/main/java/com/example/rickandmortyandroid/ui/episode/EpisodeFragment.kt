package com.example.rickandmortyandroid.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyandroid.databinding.FragmentEpisodeBinding
import com.example.rickandmortyandroid.ui.BaseFragment


class EpisodeFragment : BaseFragment() {

    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEpisodeBinding.inflate(inflater,container,false)
        return binding.root
    }
}