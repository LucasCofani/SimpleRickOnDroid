package br.lucascofani.simplerickondroid.ui.chars_detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptionsBuilder
import br.lucascofani.simplerickondroid.ui.components.CharDetailCard
import br.lucascofani.simplerickondroid.ui.components.TopBarTitle
import br.lucascofani.simplerickondroid.ui.theme.AppTheme
import br.lucascofani.simplerickondroid.util.Themes

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun CharDetailScreen(
    charId: Int?,
    changeTheme: (Themes) -> Unit,
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit,
    theme: String,
    navStart: Int,
    currentRoute: String?,
    viewModel: CharDetailViewModel
) {
    if (charId != null) {
        val onLoad = viewModel.onLoad.value

        //only trigger once
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(CharDetailEvent.getCharEvent(charId))
        }

        val charInfo = viewModel.charInfo.value

        AppTheme(
            navControllerNavigate = navControllerNavigate,
            changeTheme = changeTheme,
            currentTheme = theme,
            navStart = navStart,
            currentRoute = currentRoute,
            headerContent = {
                charInfo?.let { char ->
                    TopBarTitle(text = char.name)
                } ?: TopBarTitle(text = "Character")
            },
            content = {
                charInfo?.let { char ->
                    CharDetailCard(char = char)
                }
            },
            bottomBar = false
        )
    }


}