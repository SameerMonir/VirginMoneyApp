package com.example.virginmoneyapp.Ui


sealed class ResponseState {
    object Loading : ResponseState()
    class SUCCESS<T>(val response: T) : ResponseState()
    class ERROR(val error: Exception) : ResponseState()
}
