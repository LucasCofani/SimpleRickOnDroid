package br.lucascofani.simplerickondroid.ui.splash


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.popUpTo
import br.lucascofani.simplerickondroid.R
import br.lucascofani.simplerickondroid.navigation.Screen
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.GlideImage

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit
) {
    val timer = viewModel.timer.value
    GlideImage(
        data = R.drawable.splash,
        contentDescription = "Splash Screen",
        fadeIn = true,
        modifier = Modifier.fillMaxSize(),
        requestBuilder = {
            val opt = RequestOptions()
            opt.centerCrop()
            apply(opt)
        }
    )

    if (timer) {
        navControllerNavigate(Screen.CharList.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
}