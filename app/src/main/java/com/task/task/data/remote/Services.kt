package com.task.task.data.remote

import com.task.task.data.remote.model.PostsResponseItem
import retrofit2.http.GET
import retrofit2.http.Path


interface Services {

    @GET("posts")
    suspend fun getAllPosts(): List<PostsResponseItem>

    @GET("posts/{postId}")
    suspend fun getPostDetails(@Path("postId") postId: Int): PostsResponseItem
}