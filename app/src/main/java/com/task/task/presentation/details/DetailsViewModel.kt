package com.task.task.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.task.data.remote.ApiState
import com.task.task.domain.DetailsUseCase
import com.task.task.presentation.home.HomePostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: DetailsUseCase
) : ViewModel() {

    private val _postDetails = MutableStateFlow<ApiState<HomePostItem>>(ApiState.Loading)
    val postDetails = _postDetails.asStateFlow()

    fun getPostDetails(postId: Int) {
        viewModelScope.launch {
            detailsUseCase.getPostDetails(postId).catch {
                _postDetails.value =
                    ApiState.Failure("Something went wrong. No data was found, check internet connection then try again.")
            }.collectLatest {
                _postDetails.value = ApiState.Success(it)
            }
        }
    }
}