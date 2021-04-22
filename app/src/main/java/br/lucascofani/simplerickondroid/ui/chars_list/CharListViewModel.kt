package br.lucascofani.simplerickondroid.ui.chars_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.lucascofani.simplerickondroid.models.character.Character
import br.lucascofani.simplerickondroid.repository.CharacterRepository
import br.lucascofani.simplerickondroid.ui.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

const val STATE_KEY_QUERY = "char.state.query.key"

@HiltViewModel
class CharListViewModel
@Inject
constructor(
    private val repo: CharacterRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val charsList: MutableState<List<Character>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)


    init {
        onTriggerEvent(CharListEvent.NewSearchEvent)
    }

    fun onTriggerEvent(event: CharListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is CharListEvent.NewSearchEvent -> {
                        newSearch()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
    }

    private fun newSearch() {
        Log.d(TAG, "newSearch: query: ${query.value}, page: ${page.value}")
        // New search. Reset the state
        resetSearchState()

        repo.execute(
            page = page.value,
            query = query.value
        ).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                charsList.value = list
            }

            dataState.error?.let { error ->
                Log.e(TAG, "newSearch: ${error}")
            }
        }.launchIn(viewModelScope)
    }

    private fun resetSearchState() {
        charsList.value = listOf()
        page.value = 1
    }
    fun onQueryChanged(query: String) {
        setQuery(query)
    }
    private fun setQuery(query: String) {
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }

}