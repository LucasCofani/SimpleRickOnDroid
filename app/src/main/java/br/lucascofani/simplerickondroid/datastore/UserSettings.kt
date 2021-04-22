package br.lucascofani.simplerickondroid.datastore

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import br.lucascofani.simplerickondroid.BaseApp
import br.lucascofani.simplerickondroid.util.Themes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserSettings
@Inject
constructor(app: BaseApp) {


    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val datastore = app.datastore

    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        observeDataStore()
    }

    val theTheme = mutableStateOf(Themes.LIGHT.value)

    fun toggleTheme(themes: Themes) {
        scope.launch {
            datastore.edit { preferences ->
                preferences[THEME_KEY] = themes.value
            }
        }
    }

    private fun observeDataStore() {
        datastore.data.onEach { preferences ->
            preferences[THEME_KEY]?.let { currentTheme ->
                theTheme.value = currentTheme
            }
        }.launchIn(scope)
    }

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme_key")
    }
}