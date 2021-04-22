package br.lucascofani.simplerickondroid.ui.episode_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.popUpTo
import br.lucascofani.simplerickondroid.util.Themes
import br.lucascofani.simplerickondroid.navigation.Screen
import br.lucascofani.simplerickondroid.ui.components.TopBarTitle
import br.lucascofani.simplerickondroid.ui.theme.AppTheme

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun EpisodeListScreen(
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit,
    changeTheme: (Themes) -> Unit,
    theme: String,
    navStart: Int,
    currentRoute: String?
) {
    AppTheme(
        navControllerNavigate = navControllerNavigate,
        changeTheme = changeTheme,
        currentTheme = theme,
        navStart = navStart,
        currentRoute = currentRoute,
        headerContent = { TopBarTitle(text = "Episodes") },
        content = {
            Column {

                Button(onClick = {
                    navControllerNavigate(Screen.CharList.route) {
                        popUpTo(Screen.CharList.route) { inclusive = true }
                    }
                }) {
                }
            }
        }
    )
}