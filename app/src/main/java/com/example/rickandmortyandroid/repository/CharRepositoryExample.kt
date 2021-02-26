package com.example.rickandmortyandroid.repository

import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.models.data.ResultWrapper
import com.example.rickandmortyandroid.retrofit.CharacterService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


open class CharRepositoryExample(
        private val charDao: CharacterDAO,
        private val dispatcher: CoroutineDispatcher,
        private val responseHandler: ResponseHandler
) {


    fun getCharDao(): Flow<ResponseWrapper<Character>> {
        return charDao.getById(1)
            .map { responseHandler.handleSuccess(it) }
            .catch { emit(responseHandler.handleException("Error")) }
            .flowOn(dispatcher)
    }


}