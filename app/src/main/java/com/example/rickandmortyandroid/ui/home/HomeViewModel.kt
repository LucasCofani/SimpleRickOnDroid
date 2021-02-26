package com.example.rickandmortyandroid.ui.home

import androidx.lifecycle.*
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.repository.CharRepository
import kotlinx.coroutines.flow.*


class HomeViewModel(
    private val repository: CharRepository,
    private val responseHandler: ResponseHandler
) : ViewModel() {
    private var page = 1
    private var offline = false
    private val _chars = MutableLiveData<ResponseWrapper<List<Character>>>()

    val chars: LiveData<ResponseWrapper<List<Character>>> = _chars


    init {
        getAll()
    }

    fun getAll() {
        repository.getCharAPI(page)
            .onEach {
                if (it.status != ResponseWrapper.Status.ERROR) {
                    _chars.value = it
                    page++
                    offline = false
                } else {
                    getAllOffline()
                    if (!offline) {
                        _chars.value =
                            responseHandler.handleException("Please check your connection! Getting data from cache...")
                        offline = true
                    }
                }
            }
            .launchIn(viewModelScope)
    }
    private fun getAllOffline(){
        repository.getCharDao(page)
            .onEach {
                if (it.status != ResponseWrapper.Status.ERROR) {
                    _chars.value = it
                    page++
                }else
                    _chars.value = it
            }
            .launchIn(viewModelScope)
    }
}
