package com.example.rickandmortyandroid.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.CharWrapper
import com.example.rickandmortyandroid.models.Character
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.Status
import com.example.rickandmortyandroid.retrofit.CharacterService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


open class CharRepository(
    private val characterService: CharacterService,
    private val charDao: CharacterDAO,
    private val responseHandler: ResponseHandler
) {

    private suspend fun getCharAPI(page: Int): ResponseWrapper<CharWrapper> {
        return try {
            val response = characterService.getAll(page)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    private suspend fun getCharDao(page: Int): ResponseWrapper<CharWrapper> {
        // pega a pagina 1 do room caso tenha
        val chars = charDao.getPage(page)

        return if (chars.size == 20)
            ResponseWrapper<CharWrapper>(Status.SUCCESS, CharWrapper(null, chars))
        else
        //se n tiver retorna erro
            ResponseWrapper<CharWrapper>(Status.ERROR, null, "Pegar da API")
    }

    private suspend fun getChar(page: Int): ResponseWrapper<CharWrapper> {
        var response = ResponseWrapper<CharWrapper>(Status.LOADING, null)
        val char = getCharDao(page)
        if (char.status == Status.SUCCESS)
            response = char
        else {
            response = getCharAPI(page)
            if (response.status == Status.SUCCESS)
                // adiciona no banco de dados o resultado da api
                response.data?.let { addCharDao(it.results) }
        }
        return response
    }

    private suspend fun addCharDao(charList: List<Character>) {
        charList.forEach { char ->
            charDao.addChar(char)
        }
    }

    // função do flow é parecida com o suspend, mas ela permite mais coisas e uma delas
    // é a possibilidade de retornar valor mais de uma vez por função
    // o emit seria um return porem podemos aplica-lo varias vezes
    fun getAll(page: Int) = flow {
        var res: ResponseWrapper<CharWrapper> = ResponseWrapper(Status.LOADING, null)
        //irá retornar o processo de load
        emit(res)

        //simular tempo de carregar da api
        //kotlinx.coroutines.delay(5000)

        res = getChar(page)
        // ira retornar o resultado da api ou o erro
        emit(res)

        // flowOn especifiva em qual thread vai rodar
    }.flowOn(Dispatchers.Default)

}