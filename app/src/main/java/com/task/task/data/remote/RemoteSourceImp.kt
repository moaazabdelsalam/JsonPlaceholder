package com.task.task.data.remote

import com.task.task.data.remote.model.PostsResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceImp @Inject constructor(
    private val service: Services
) : RemoteSource {

    override fun getAllPosts() = flow {
        emit(service.getAllPosts())
    }

    override fun getPostDetails(postId: Int): Flow<PostsResponseItem> {
        return flow {
            emit(service.getPostDetails(postId))
        }
    }
}