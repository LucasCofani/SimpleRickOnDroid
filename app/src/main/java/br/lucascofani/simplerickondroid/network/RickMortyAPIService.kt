package br.lucascofani.simplerickondroid.network

import br.lucascofani.simplerickondroid.models.util.ResultWrapper
import br.lucascofani.simplerickondroid.models.character.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyAPIService {

    @GET("character/{id}")
    suspend fun getChar(
        @Path("id") id : Int
    ): ResultWrapper<CharacterDto>

    @GET("character")
    suspend fun searchChar(
        @Query("name") name : String,
        @Query("page") p: Int
    ): ResultWrapper<CharacterDto>
}