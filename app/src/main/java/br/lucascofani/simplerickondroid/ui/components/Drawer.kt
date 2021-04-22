package br.lucascofani.simplerickondroid.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import br.lucascofani.simplerickondroid.util.Themes

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Drawer(
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit,
    changeTheme: (Themes) -> Unit
) {
    var expanded = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Divider()
        ListItem(
            text = { Text("Themes") },
            icon = {
                Icon(
                    if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            },
            modifier = Modifier.clickable {
                expanded.value = !expanded.value
            }
        )
        AnimatedVisibility(expanded.value) {
            Column(modifier = Modifier.background(MaterialTheme.colors.secondary)) {
                Divider()
                ListItem(
                    text = { Text("Default") },
                    modifier = Modifier.clickable {
                        changeTheme(Themes.LIGHT)
                    }
                )
                Divider()
                ListItem(
                    text = { Text("Dark") },
                    modifier = Modifier.clickable {
                        changeTheme(Themes.DARK)
                    }
                )
                Divider()
                ListItem(
                    text = { Text("Alternative") },
                    modifier = Modifier.clickable {
                        changeTheme(Themes.ALTERNATIVE)
                    }
                )
                Divider()
            }
        }
        Divider()
    }
}

