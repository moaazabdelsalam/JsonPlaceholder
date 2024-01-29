package com.task.task.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.task.data.remote.ApiState
import com.task.task.domain.AllPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allPostsUseCase: AllPostsUseCase
): ViewModel() {
    private val _postsState: MutableStateFlow<ApiState<List<HomePostItem>>> =
        MutableStateFlow(ApiState.Loading)
    val postsState: StateFlow<ApiState<List<HomePostItem>>>
        get() = _postsState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllPosts()
        }
    }

    private suspend fun getAllPosts() {
        allPostsUseCase.invoke().catch {
            Log.i("TAG", "getAllPosts: ${it.message}")
            _postsState.value = ApiState.Failure("Something went wrong. No data was found, check internet connection then try again.")
        }.collectLatest {
            _postsState.value = ApiState.Success(it)
        }
    }
}