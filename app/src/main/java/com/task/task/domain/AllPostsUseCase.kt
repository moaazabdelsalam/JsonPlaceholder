package com.task.task.domain

import com.task.task.data.repo.Repo
import javax.inject.Inject

class AllPostsUseCase @Inject constructor(
    private val repo: Repo
) {

    operator fun invoke() = repo.getAllPosts()
}