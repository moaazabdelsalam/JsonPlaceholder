package com.task.task.data.remote

sealed class ApiState<out T> {
    class Success<T>(val data: T) : ApiState<T>()
    class Failure(val error: String) : ApiState<Nothing>()
    data object Loading : ApiState<Nothing>()
}