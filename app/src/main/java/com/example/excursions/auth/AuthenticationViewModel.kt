package com.example.excursions.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.TimeInput
import androidx.compose.runtime.Recomposer
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthenticationViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    //var uiState = mutableStateOf(AuthUiState())
    //    private set

    private val _uiState = mutableStateOf(AuthUiState())
    val uiState = _uiState

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    fun updateUiState(
        email: String = uiState.value.email,
        password: String = uiState.value.password,
        firstName: String = uiState.value.firstName,
        lastName: String = uiState.value.lastName
    ) {
        _uiState.value = AuthUiState(email, password, firstName, lastName)
    }


    fun signInWithEmailAndPassword() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("signInWithEmail:success")
                    val user = auth.currentUser
                    Timber.d(user.toString())
                    updateUiState()
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.w("signInWithEmail:failure", task.exception)
                    //Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
            }
    }

    fun createUserWithEmailAndPassword() {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("Account created")
                } else {
                    Timber.d("Account creation failed")
                    // Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()

                }
        }
    }

    fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("signInAnonymously:success")
                    val user = auth.currentUser
                    Timber.d("Current user info: $user")
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.w("signInAnonymously:failure", task.exception)

                }
            }
    }


}

class AuthenticationViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel() as T
    }
}
