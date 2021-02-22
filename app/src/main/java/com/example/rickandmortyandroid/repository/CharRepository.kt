package com.example.rickandmortyandroid.repository

import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.models.data.ResultWrapper
import com.example.rickandmortyandroid.retrofit.CharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


open class CharRepository(
        private val characterService: CharacterService,
        private val charDao: CharacterDAO,
        private val responseHandler: ResponseHandler
) {

    private suspend fun getCharAPI(page: Int): ResponseWrapper<List<Character>> {
        return try {
            val response = characterService.getAll(page)
            responseHandler.handleSuccess(response.results)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    private fun getCharDao(page: Int): Flow<ResponseWrapper<List<Character>>> = flow {
        emit(responseHandler.handleLoading("Carregando banco de dados"))

        val chars = charDao.getPage(page)
        if (chars.size == 20)
            emit(responseHandler.handleSuccess(chars))
        else

            emit(ResponseWrapper(ResponseWrapper.Status.ERROR, null, "Pegar da API"))
    }.flowOn(Dispatchers.Default)

    private fun getChar(page: Int): Flow<ResponseWrapper<List<Character>>> = flow {
        var response: ResponseWrapper<List<Character>> = responseHandler.handleLoading()
        emit(response)
        val char = getCharDao(page)
        char.collect {
            when (it.status) {
                ResponseWrapper.Status.ERROR -> {
                    response = getCharAPI(page)
                    if (response.status == ResponseWrapper.Status.SUCCESS)
                        response.data?.let { data -> addCharDao(data) }
                }
                else -> emit(it)
            }
        }
    }.flowOn(Dispatchers.Default)

    private suspend fun addCharDao(charList: List<Character>) {
        charList.forEach { char ->
            charDao.addChar(char)
        }
    }

    fun getAll(page: Int): Flow<ResponseWrapper<List<Character>>> = flow {
        var res: ResponseWrapper<List<Character>> = responseHandler.handleLoading()
        emit(res)

        getChar(page).collect {
            emit(it)
        }

    }.flowOn(Dispatchers.Default)

}