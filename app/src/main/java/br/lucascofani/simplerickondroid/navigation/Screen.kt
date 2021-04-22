package br.lucascofani.simplerickondroid.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import br.lucascofani.simplerickondroid.R

sealed class Screen(
    val route: String,
    val display: String,
    val icon: @Composable () -> Unit = {}
) {
    object Splash : Screen("splash", "splash")
    object CharDetail : Screen("charsDetail", "charsDetail")

    object CharList : Screen("charList", "Characters",
        { Icon(Icons.Filled.Person, contentDescription = "") })

    object EpisodeList : Screen("episodeList", "Episodes",
        { Icon(painterResource(id = R.drawable.ic_film), contentDescription = "") })

    object LocationList : Screen("locationList", "Locations",
        { Icon(painterResource(id = R.drawable.ic_map_marked), contentDescription = "") })

}