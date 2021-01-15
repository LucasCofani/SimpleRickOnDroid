package com.example.rickandmortyandroid.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyandroid.models.CharWrapper
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.Status
import com.example.rickandmortyandroid.repository.CharRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: CharRepository) : ViewModel() {
    private var page = 1
    val _chars = MutableLiveData<ResponseWrapper<CharWrapper>>()
    val chars = _chars as LiveData<ResponseWrapper<CharWrapper>>

    init {
        //deixei aqui o carregamento inicial pois no fragment da erro por conta do ciclo de vida bugado
        getAll()
    }

    fun getAll() {
        viewModelScope.launch {
            // verifica o status de loading, se estiver carregando n permite executar outra
            // requisicao
            if ((_chars.value?.status ?: Status.SUCCESS) != Status.LOADING)

                repository.getAll(page)
                    // catch é um try catch simplificado das funcoes flow
                    .catch { cause -> Log.i("teste", "${cause}") }
                    // o collect ele fica monitorando cada emit enviado pela funcao
                    // a cada emit novo iremos substituir o valor do chars e assim
                    // atualizar nossa tela inicial
                    .collect {
                        _chars.value = (it)
                    }
            else
                Log.i("teste", "Aguarde a requisicao anteriora terminar!")
        }
        page++
    }
}
