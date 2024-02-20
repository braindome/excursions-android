package com.example.excursions.auth

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = ""
)