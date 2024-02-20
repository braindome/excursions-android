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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.excursions.auth.AuthenticationViewModel
import com.example.excursions.ui.theme.ExcursionsTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateAccountScreen(
    navController: NavController,
    viewModel: AuthenticationViewModel
) {

    var isError by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)

        ) {
            OutlinedTextField(
                value = viewModel.uiState.value.firstName,
                onValueChange = {
                    viewModel.updateUiState(firstName = it)
                },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = 4.dp)
            )
            OutlinedTextField(
                value = viewModel.uiState.value.lastName,
                onValueChange = {
                    viewModel.updateUiState(lastName = it)
                },
                label = { Text("Surname") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(1f)

            )
        }

        OutlinedTextField(
            value = viewModel.uiState.value.email,
            onValueChange = {
                viewModel.updateUiState(email = it)
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
            value = viewModel.uiState.value.password,
            onValueChange = {
                viewModel.updateUiState(password = it)
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
            Button(
                onClick = {
                    // Perform authentication logic here
                    /*
                    if (isValidCredentials(email, password)) {
                        // Authentication successful
                        isError = false
                        // Navigate to the next screen or perform desired action
                    } else {
                        // Authentication failed
                        isError = true
                        // Clear password field and request focus
                        password = ""
                        focusManager.clearFocus()
                        keyboardController?.show()
                    }
                    */
                    viewModel.createUserWithEmailAndPassword()
                    navController.navigate("mainActivity")
                },
                enabled = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(18.dp)
            ) {
                Text("Create")
            }
        }


    }
}


