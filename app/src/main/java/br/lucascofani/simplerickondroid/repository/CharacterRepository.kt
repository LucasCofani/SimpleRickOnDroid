package br.lucascofani.simplerickondroid.repository

import android.util.Log
import br.lucascofani.simplerickondroid.models.character.Character
import br.lucascofani.simplerickondroid.models.character.CharacterDtoMapper
import br.lucascofani.simplerickondroid.models.util.DataState
import br.lucascofani.simplerickondroid.network.RickMortyAPIService
import br.lucascofani.simplerickondroid.ui.util.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository(
    private val api: RickMortyAPIService,
    private val mapper: CharacterDtoMapper,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Character>>> = flow {
        try {
            emit(DataState.loading())
            delay(1000)
            if (query == "error") {
                throw Exception("Search FAILED!")
            }
            val chars = getCharsFromNetwork(
                page = page,
                query = query,
            )

            emit(DataState.success(chars))
            Log.i(TAG, "execute: $chars")
        } catch (e: Exception) {
            emit(DataState.error<List<Character>>(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getCharsFromNetwork(
        page: Int,
        query: String
    ): List<Character> {
        return mapper.toDomainList(
            api.searchChar(
                p = page,
                name = query,
            ).results
        )
    }
}