package br.lucascofani.simplerickondroid.ui.theme

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavOptionsBuilder
import br.lucascofani.simplerickondroid.util.Themes
import br.lucascofani.simplerickondroid.navigation.BottomBarList
import br.lucascofani.simplerickondroid.navigation.Screen
import br.lucascofani.simplerickondroid.ui.components.BottomBar
import br.lucascofani.simplerickondroid.ui.components.CustomTopAppBar
import br.lucascofani.simplerickondroid.ui.components.Drawer

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = BlackC,
    primaryVariant = BlackF,
    onPrimary = Color.White,
    secondary = BlackF,
    secondaryVariant = Color.Black,
    onSecondary = BlackA,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Color.Black,
    onBackground = Color.White,
    surface = BlackC,
    onSurface = BlackF,
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = GreenE,
    primaryVariant = GreenB,
    onPrimary = Color.White,
    secondary = GreenC,
    secondaryVariant = GreenE,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = GreenBG,
    onBackground = Color.Black,
    surface = GreenE,
    onSurface = Color.White,
)

@SuppressLint("ConflictingOnColor")
private val AlternativeColorPalette = Colors(
    primary = GreenA,
    primaryVariant = GreenB,
    onPrimary = Color.White,
    secondary = GreenD,
    secondaryVariant = GreenA,
    onSecondary = Color.White,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = BlackA,
    onBackground = BlackF,
    surface = GreenA,
    onSurface = Color.White,
    isLight = false
)

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun AppTheme(
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit,
    currentTheme: String = Themes.LIGHT.value,
    itemsBottom: List<Screen> = BottomBarList.default,
    changeTheme: (Themes) -> Unit,
    headerContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
    topBar: Boolean = true,
    bottomBar: Boolean = true,
    drawer: Boolean = true,
    navStart: Int,
    currentRoute: String?
) {
    val colors = when (currentTheme) {
        Themes.ALTERNATIVE.value -> AlternativeColorPalette
        Themes.DARK.value -> DarkColorPalette
        else -> LightColorPalette
    }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
    ) {
        Scaffold(
            topBar = {
                if (topBar) CustomTopAppBar(
                    navControllerNavigate = navControllerNavigate,
                    scaffoldState = scaffoldState,
                    scope = scope,
                    headerContent = headerContent,
                    drawer = drawer
                )
            },
            bottomBar = {
                if (bottomBar) BottomBar(
                    itemsBottom = itemsBottom,
                    navControllerNavigate = navControllerNavigate,
                    navStart = navStart,
                    currentRoute = currentRoute
                )
            },
            drawerContent = {
                if (drawer) Drawer(
                    navControllerNavigate = navControllerNavigate,
                    changeTheme = changeTheme
                )
            },
            scaffoldState = scaffoldState
        ) { innerPadding->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    }
}