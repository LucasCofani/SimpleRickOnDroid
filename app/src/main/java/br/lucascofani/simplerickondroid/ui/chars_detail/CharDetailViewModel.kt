package br.lucascofani.simplerickondroid.ui.chars_detail

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

const val STATE_KEY_CHAR_ID = "char.state.char.id"

@HiltViewModel
class CharDetailViewModel
@Inject
constructor(
    private val repo: CharacterRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val charInfo: MutableState<Character?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    val onLoad = mutableStateOf(false)


    fun onTriggerEvent(event: CharDetailEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is CharDetailEvent.getCharEvent -> {
                        if (charInfo.value == null)
                            getChar(event.id)
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

    private fun getChar(charId : Int) {
        resetCharInfo()
        repo.getChar(
            charId = charId
        ).onEach { dataState ->
            loading.value = dataState.loading
            dataState.data?.let { list ->
                charInfo.value = list
            }
            dataState.error?.let { error ->
                Log.e(TAG, "getChar: ${error}")
            }
        }.launchIn(viewModelScope)
    }

    private fun resetCharInfo() {
        charInfo.value = null
    }

}