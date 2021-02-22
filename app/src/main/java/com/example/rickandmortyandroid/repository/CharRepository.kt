package com.example.rickandmortyandroid.repository

import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.data.CharWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.Status
import com.example.rickandmortyandroid.retrofit.CharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


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

    private fun getCharDao(page: Int): Flow<ResponseWrapper<CharWrapper>> = flow {
        emit(ResponseWrapper<CharWrapper>(Status.LOADING, null, "Carregando banco de dados"))
        // pega a pagina 1 do room caso tenha
        val chars = charDao.getPage(page)

        if (chars.size == 20)
            emit(ResponseWrapper<CharWrapper>(Status.SUCCESS, CharWrapper(null, chars)))
        else
        //se n tiver retorna erro
            emit(ResponseWrapper<CharWrapper>(Status.ERROR, null, "Pegar da API"))
    }.flowOn(Dispatchers.Default)

    private fun getChar(page: Int): Flow<ResponseWrapper<CharWrapper>> = flow {
        var response = ResponseWrapper<CharWrapper>(Status.LOADING, null, "Carregando personagens")
        emit(response)
        val char = getCharDao(page)
        char.collect {
            when (it.status) {
                Status.ERROR -> {
                    response = getCharAPI(page)
                    if (response.status == Status.SUCCESS)
                    // adiciona no banco de dados o resultado da api
                        response.data?.let { addCharDao(it.results) }
                    emit(response)
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

    // função do flow é parecida com o suspend, mas ela permite mais coisas e uma delas
    // é a possibilidade de retornar valor mais de uma vez por função
    // o emit seria um return porem podemos aplica-lo varias vezes
    fun getAll(page: Int) : Flow<ResponseWrapper<CharWrapper>> = flow {
        var res: ResponseWrapper<CharWrapper> = ResponseWrapper(Status.LOADING, null)
        //irá retornar o processo de load
        emit(res)

        //simular tempo de carregar da api
        //kotlinx.coroutines.delay(5000)
        getChar(page).collect {
            emit(it)
        }

        // flowOn especifiva em qual thread vai rodar
    }.flowOn(Dispatchers.Default)

}