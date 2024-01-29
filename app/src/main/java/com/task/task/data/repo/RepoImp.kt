package com.task.task.data.repo

import com.task.task.data.remote.RemoteSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoImp @Inject constructor(
    private val remoteSource: RemoteSource
) : Repo {

    override fun getAllPosts() = remoteSource.getAllPosts().map {
        it.toHomeItemsList()
    }

    override fun getPostDetails(postId: Int) = remoteSource.getPostDetails(postId).map {
        it.toHomeItem()
    }
}
