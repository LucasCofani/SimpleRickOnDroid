package com.example.rickandmortyandroid.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyandroid.R
import com.example.rickandmortyandroid.databinding.FragmentSplashBinding
import com.example.rickandmortyandroid.ui.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment() : BaseFragment()  {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override var bottomNavigationViewVisibility  = View.INVISIBLE
    override var toobarVisibility = View.INVISIBLE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivSplash.setImageResource(R.drawable.splash)
        lifecycleScope.launch{
            delay(3000)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }
}