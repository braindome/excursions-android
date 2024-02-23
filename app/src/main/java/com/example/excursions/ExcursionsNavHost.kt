package com.example.excursions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.excursions.auth.AuthenticationViewModel
import com.example.excursions.auth.AuthenticationViewModelFactory
import com.example.excursions.ui.components.BottomNavBar
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsTopBar
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
        composable("auth") { AuthenticationScreen(navController = navController) }
        composable("createAccount") { CreateAccountScreen(navController, viewModel) }
        composable("mainActivity") { AppScreen() }
    }

    Scaffold(
        topBar = { ExcursionsTopBar() },
        bottomBar = {
            // BottomNavBar(navController = navController, modifier = Modifier )
            ExcursionsBottomBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button x times.
                """.trimIndent(),
            )
        }

    }
}
