package com.example.excursions

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.excursions.auth.AuthenticationViewModel
import com.example.excursions.auth.AuthenticationViewModelFactory
import com.example.excursions.ui.screens.AuthenticationScreen
import com.example.excursions.ui.screens.CreateAccountScreen

@Composable
fun ExcursionsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "auth"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("auth") {
            //AuthenticationScreen()
        }
        //composable("createAccount") { CreateAccountScreen() }
        composable("mainActivity") { AppScreen() }
        composable("test") { TestScreen() }
        
    }
}

@Composable
fun TestScreen() {
    Text(text = "test page")
}



@Composable
fun MyApp() {
    val navController = rememberNavController()
    val viewModel = viewModel<AuthenticationViewModel>(
        factory = AuthenticationViewModelFactory()
    )

    /*
    val viewModel = viewModel<AuthenticationViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthenticationViewModel() as T
            }
        }
    )

     */


    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            AuthenticationScreen(navController = navController)
        }
        composable("createAccount") { CreateAccountScreen(navController, viewModel) }
        composable("mainActivity") { AppScreen() }
    }
}
