package com.example.rickandmortyandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyandroid.databinding.FragmentHomeBinding
import com.example.rickandmortyandroid.models.Status
import com.example.rickandmortyandroid.ui.BaseFragment
import org.koin.android.viewmodel.ViewModelOwner
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeAdapter()
        val layoutManager = LinearLayoutManager(context)
        binding.rvChars.adapter = adapter
        binding.rvChars.layoutManager = layoutManager
        binding.rvChars.setHasFixedSize(true)

        viewModel.chars.observe(viewLifecycleOwner, {
            if (it != null)
                when (it.status) {
                    Status.ERROR -> sendMessage(it.error.toString())
                    //Status.SUCCESS -> adapter.submitList(it.data?.results)
                    Status.SUCCESS -> adapter.submitList(
                        merge(
                            adapter.currentList,
                            it.data?.results!!
                        )
                    )
                    Status.LOADING -> sendMessage(it.error ?: "Carregando")
                }
        })

        binding.btnGetchar.setOnClickListener {
            viewModel.getAll()
        }
    }

    fun <T> merge(first: List<T>, second: List<T>): List<T> {
        val list: MutableList<T> = ArrayList()
        list.addAll(first!!)
        list.addAll(second!!)
        return list
    }
}