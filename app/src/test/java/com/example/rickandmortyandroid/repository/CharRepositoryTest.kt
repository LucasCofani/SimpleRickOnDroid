package com.example.rickandmortyandroid.repository

import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.models.data.LocationResume
import com.example.rickandmortyandroid.retrofit.CharacterService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*

class CharRepositoryTest{
    val dispatcher = TestCoroutineDispatcher()
    val location = LocationResume("teste", "")
    val char = Character(
        "teste",
        "teste",
        "teste",
        "teste",
        "teste",
        location,
        location,
        "",
        arrayListOf(),
        "",
        "",
        1
    )
    val char2 = Character(
        "teste",
        "teste",
        "teste",
        "teste",
        "teste",
        location,
        location,
        "",
        arrayListOf(),
        "",
        "",
        2
    )
    val listChars = listOf(
        char, char2, char
    )
    val listListChars = listOf(listChars,listChars)
    val daoTest = mock<CharacterDAO> {
        on { getById(1) } doReturn listChars.asFlow()
    }
    val serviceTest = mock<CharacterService>(){
        on { getChar(2)} doReturn listListChars.asFlow()
    }

    val charTestRepo = CharRepository(serviceTest,daoTest, dispatcher, ResponseHandler())
}