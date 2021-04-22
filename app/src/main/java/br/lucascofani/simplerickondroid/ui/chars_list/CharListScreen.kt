package br.lucascofani.simplerickondroid.ui.chars_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import br.lucascofani.simplerickondroid.ui.components.SearchField
import br.lucascofani.simplerickondroid.ui.components.TopBarTitle
import br.lucascofani.simplerickondroid.ui.theme.AppTheme
import br.lucascofani.simplerickondroid.util.DateUtil
import br.lucascofani.simplerickondroid.util.Themes
import com.google.accompanist.glide.GlideImage


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
                        ) { index, char ->
                            Card(
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .clickable(onClick = {/*
                                     */
                                    }),
                                elevation = 5.dp,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .background(MaterialTheme.colors.secondary)
                                ) {
                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        elevation = 8.dp,
                                        modifier = Modifier
                                            .width(140.dp)
                                            .padding(10.dp),
                                    ){
                                        GlideImage(
                                            data = char.image,
                                            contentScale = ContentScale.Crop,
                                            contentDescription = null,
                                        )
                                    }
                                    Column {
                                        Text(text = "${char.name} - ${DateUtil.dateToString(char.created)}")
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    )
}