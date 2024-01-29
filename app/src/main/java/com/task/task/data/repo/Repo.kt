package com.task.task.data.repo

import com.task.task.presentation.home.HomePostItem
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getAllPosts(): Flow<List<HomePostItem>>
    fun getPostDetails(postId: Int): Flow<HomePostItem>
}