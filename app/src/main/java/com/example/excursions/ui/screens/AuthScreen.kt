package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsRoutes
import com.example.excursions.auth.AuthenticationViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthenticationScreen(
    navController: NavController,
    viewModel: AuthenticationViewModel = AuthenticationViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isButtonEnabled by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                // updateButtonState(email, password)
            },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                // TODO
            },
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        if (isError) {
            Text(
                text = "Invalid email or password",
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*
            // User auth

            Button(
                onClick = {
                    viewModel.signInWithEmailAndPassword()

                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
                    .padding(18.dp)
            ) {
                Text("Login")
            }
            Button(
                onClick = {
                    // viewModel.createUserWithEmailAndPassword()
                    navController.navigate("createAccount")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
                    .padding(18.dp)
            ) {
                Text("Create")
            }

            */
            Button(
                onClick = {
                    viewModel.signInAnonymously()
                    navController.navigate(ExcursionsRoutes.MainActivity.route)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(18.dp)
            ) {
                Text(text = "Anonymous sign-in")
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthenticationScreen(navController = rememberNavController())
}

