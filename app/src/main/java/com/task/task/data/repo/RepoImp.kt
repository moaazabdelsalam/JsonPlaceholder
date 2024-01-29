package com.task.task.data.repo

import com.task.task.data.remote.RemoteSource
import com.task.task.data.repo.Repo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoImp @Inject constructor(
    private val remoteSource: RemoteSource
) : Repo {

}