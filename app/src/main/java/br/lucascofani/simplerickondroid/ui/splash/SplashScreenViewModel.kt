package br.lucascofani.simplerickondroid.ui.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.lucascofani.simplerickondroid.ui.util.SPLASH_TIMEOUT
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel: ViewModel() {
    val timer: MutableState<Boolean> = mutableStateOf(false)
    init {
        triggerSplash(SPLASH_TIMEOUT)
    }
    fun triggerSplash(timeout: Long){
        viewModelScope.launch {
            delay(timeout)
            timer.value = true
        }
    }
}