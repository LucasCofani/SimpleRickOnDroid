package com.example.rickandmortyandroid.retrofit


import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.models.data.ResultWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getAll(
        @Query("page") p: Int
    ): ResultWrapper<Character>


    @GET("character/{id}")
    suspend fun getChar(
        @Path("id") id : Int
    ): ResultWrapper<Character>

}