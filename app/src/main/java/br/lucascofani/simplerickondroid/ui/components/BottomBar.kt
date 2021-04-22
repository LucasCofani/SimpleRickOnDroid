package br.lucascofani.simplerickondroid.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import br.lucascofani.simplerickondroid.navigation.Screen

@Composable
fun BottomBar(
    itemsBottom: List<Screen>,
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit,
    inclusive : Boolean = true,
    navStart: Int,
    currentRoute: String?,
) {
    BottomNavigation {
        itemsBottom.forEach { screen ->
            BottomNavigationItem(
                icon =  screen.icon ,
                label = { Text(screen.display) },
                selected = currentRoute == screen.route,
                onClick = {
                    navControllerNavigate(screen.route) {
                        popUpTo(navStart) {
                            this.inclusive = inclusive
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}