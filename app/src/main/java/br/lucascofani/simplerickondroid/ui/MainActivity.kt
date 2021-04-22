package br.lucascofani.simplerickondroid.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import br.lucascofani.simplerickondroid.datastore.UserSettings
import br.lucascofani.simplerickondroid.navigation.Screen
import br.lucascofani.simplerickondroid.ui.chars_list.CharListScreen
import br.lucascofani.simplerickondroid.ui.chars_list.CharListViewModel
import br.lucascofani.simplerickondroid.ui.episode_list.EpisodeListScreen
import br.lucascofani.simplerickondroid.ui.location_list.LocationListScreen
import br.lucascofani.simplerickondroid.ui.splash.SplashScreen
import br.lucascofani.simplerickondroid.ui.splash.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var settingsDataStore: UserSettings

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Splash.route) {
                composable(route = Screen.Splash.route) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: SplashScreenViewModel = viewModel("splashScreenViewModel",factory)
                    SplashScreen(viewModel = viewModel, navController::navigate)
                }
                composable(route = Screen.CharList.route) { navBackStackEntry ->
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                    val navStart = navController.graph.startDestination

                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: CharListViewModel = viewModel("charScreenViewModel",factory)


                    CharListScreen(
                        navController::navigate,
                        settingsDataStore::toggleTheme,
                        settingsDataStore.theTheme.value,
                        navStart,
                        currentRoute,
                        viewModel
                    )
                }
                composable(route = Screen.EpisodeList.route) { navBackStackEntry ->
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                    val navStart = navController.graph.startDestination
                    EpisodeListScreen(
                        navController::navigate,
                        settingsDataStore::toggleTheme,
                        settingsDataStore.theTheme.value,
                        navStart,
                        currentRoute
                    )
                }
                composable(route = Screen.LocationList.route) { navBackStackEntry ->
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                    val navStart = navController.graph.startDestination
                    LocationListScreen(
                        navController::navigate,
                        settingsDataStore::toggleTheme,
                        settingsDataStore.theTheme.value,
                        navStart,
                        currentRoute
                    )
                }
            }
        }
    }
}