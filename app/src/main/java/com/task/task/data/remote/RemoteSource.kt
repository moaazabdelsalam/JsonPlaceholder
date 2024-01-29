package com.task.task.data.remote

import com.task.task.data.remote.model.PostsResponseItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path

interface RemoteSource {
    fun getAllPosts(): Flow<List<PostsResponseItem>>
    fun getPostDetails(@Path("postId") postId: Int): Flow<PostsResponseItem>
}