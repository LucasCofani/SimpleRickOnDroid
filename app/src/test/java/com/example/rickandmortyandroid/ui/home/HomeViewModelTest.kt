package com.example.rickandmortyandroid.ui.home


import app.cash.turbine.test
import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.models.ResponseWrapper
import com.example.rickandmortyandroid.models.data.Character
import com.example.rickandmortyandroid.models.data.LocationResume
import com.example.rickandmortyandroid.repository.CharRepositoryExample
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.time.ExperimentalTime


class HomeViewModelTest {
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
    val apiService = mock<CharacterDAO> {
        on { getById(1) } doReturn listChars.asFlow()
    }

    val charTestRepo = CharRepositoryExample(apiService, dispatcher, ResponseHandler())


    @ExperimentalTime
    @Test
    fun `simple test`() = dispatcher.runBlockingTest {
        val chars = charTestRepo.getCharDao().test {
            assertEquals(expectItem().data, char)
            assertEquals(expectItem().data, char2)
            assertEquals(expectItem().data, char)
            expectComplete()
        }
    }

    @ExperimentalTime
    @Test
    fun `simple error`() = dispatcher.runBlockingTest {
        whenever(charTestRepo.getCharDao()) doReturn flow { throw Exception() }
        charTestRepo.getCharDao().test {
            assertEquals(expectItem().status,ResponseWrapper.Status.ERROR)
            expectComplete()
        }
    }
}
