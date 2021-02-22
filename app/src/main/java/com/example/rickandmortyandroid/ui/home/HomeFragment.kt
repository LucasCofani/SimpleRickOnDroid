package com.example.rickandmortyandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyandroid.databinding.FragmentHomeBinding
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment(),HomeAdapter.onClickListener {

    private val viewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var chars: ArrayList<Character> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeAdapter(this)
        val layoutManager = LinearLayoutManager(context)
        binding.rvChars.setHasFixedSize(true)
        binding.rvChars.layoutManager = layoutManager
        binding.rvChars.adapter = adapter

        viewModel.chars.observe(viewLifecycleOwner, {
            if (it != null)
                when (it.status) {
                    ResponseWrapper.Status.ERROR -> sendMessage(it.error.toString())
                    ResponseWrapper.Status.SUCCESS -> {
                        chars.addAll(it.data!!)
                        adapter.updateList(chars)
                    }
                    //Status.LOADING -> sendMessage(it.error ?: "Carregando")
                }
        })
        binding.rvChars.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //somente atualiza quando parar de scrollar
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    val visibleItem = layoutManager?.childCount
                    val unloadedItem = layoutManager.findFirstVisibleItemPosition()
                    val total = adapter?.itemCount
                    if ((visibleItem + unloadedItem) == total) {
                        viewModel.getAll()
                    }
                }
            }
        })
    }

    override fun charsClick(position: Int) {
        Log.i("teste", "charsClick: ${chars[position]}")
    }
}