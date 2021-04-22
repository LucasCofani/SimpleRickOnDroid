package br.lucascofani.simplerickondroid.ui.chars_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import br.lucascofani.simplerickondroid.ui.components.CharCard
import br.lucascofani.simplerickondroid.ui.components.SearchField
import br.lucascofani.simplerickondroid.ui.components.TopBarTitle
import br.lucascofani.simplerickondroid.ui.theme.AppTheme
import br.lucascofani.simplerickondroid.util.Themes


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun CharListScreen(
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit,
    changeTheme: (Themes) -> Unit,
    theme: String,
    navStart: Int,
    currentRoute: String?,
    viewModel: CharListViewModel
) {
    val query = viewModel.query.value
    val charList = viewModel.charsList.value

    AppTheme(
        navControllerNavigate = navControllerNavigate,
        changeTheme = changeTheme,
        currentTheme = theme,
        navStart = navStart,
        currentRoute = currentRoute,
        headerContent = {
            TopBarTitle(text = "Characters")
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchField(
                    query = query,
                    onQueryChanged = viewModel::onQueryChanged,
                    onTriggerEvent = viewModel::onTriggerEvent
                )
                Box {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        itemsIndexed(
                            items = charList,
                        ) { _, char ->
                            CharCard(
                                char = char,
                                navControllerNavigate = navControllerNavigate
                            )
                        }
                    }
                }

            }
        }
    )
}