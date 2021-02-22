package com.example.rickandmortyandroid.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.repository.CharRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: CharRepository) : ViewModel() {
    private var page = 1
    val chars = MutableLiveData<ResponseWrapper<List<Character>>>()

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch {
            if ((chars.value?.status ?: ResponseWrapper.Status.SUCCESS) != ResponseWrapper.Status.LOADING)
                repository.getAll(page)
                        .catch { cause ->
                            Log.i("teste", "${cause}")
                        }
                        .collect {
                            chars.value = (it)
                        }
            else
                Log.i("teste", "Aguarde a requisicao anteriora terminar!")
        }
        page++
    }
}
