package com.example.rickandmortyandroid.repository

import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.retrofit.CharacterService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


open class CharRepository(
    private val characterService: CharacterService,
    private val charDao: CharacterDAO,
    private val dispatcher: CoroutineDispatcher,
    private val responseHandler: ResponseHandler
) {

    fun getCharAPI(page: Int): Flow<ResponseWrapper<List<Character>>> = flow {
        try {
            val res = characterService.getAll(page).results
            addCharDao(res)
            emit(responseHandler.handleSuccess(res))
        } catch (e: Exception) {
            emit(responseHandler.handleException<List<Character>>(e))
        }
    }.flowOn(dispatcher)

    fun getCharDao(page: Int): Flow<ResponseWrapper<List<Character>>> {
        return charDao.getPage(page)
            .map { responseHandler.handleSuccess(it) }
            .catch { err -> emit(responseHandler.handleException(err.message!!)) }
            .flowOn(dispatcher)
    }

    private suspend fun addCharDao(charList: List<Character>) {
        charList.forEach { char ->
            charDao.addChar(char)
        }
    }
}