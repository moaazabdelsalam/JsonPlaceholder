package com.task.task.data.di

import com.task.task.data.remote.RemoteSource
import com.task.task.data.remote.RemoteSourceImp
import com.task.task.data.repo.Repo
import com.task.task.data.repo.RepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindRepo(
        repoImp: RepoImp
    ): Repo

    @Binds
    abstract fun bindRemoteSource(
        remoteSourceImp: RemoteSourceImp
    ): RemoteSource

}