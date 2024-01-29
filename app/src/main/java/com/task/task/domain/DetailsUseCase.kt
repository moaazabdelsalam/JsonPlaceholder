package com.task.task.domain

import com.task.task.data.repo.Repo
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val repo: Repo
) {
    fun getPostDetails(postId: Int) = repo.getPostDetails(postId)
}