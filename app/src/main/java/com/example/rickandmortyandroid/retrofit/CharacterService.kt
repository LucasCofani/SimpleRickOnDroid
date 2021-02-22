package com.example.rickandmortyandroid.retrofit


import com.example.rickandmortyandroid.models.data.CharWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//caminho utilizados pelo retrofit
interface CharacterService {
    @GET("character")
    suspend fun getAll(
        @Query("page") p: Int
    ): CharWrapper


    @GET("character/{id}")
    suspend fun getChar(
        @Path("id") id : Int
    ): CharWrapper
//
//       simular erros no marvel api
//    @GET("characters?ts=1&apikey=123")
//    suspend fun getAll(): CharWrapper
}