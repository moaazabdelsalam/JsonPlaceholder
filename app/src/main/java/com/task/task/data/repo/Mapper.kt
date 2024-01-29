package com.task.task.data.repo

import com.task.task.data.remote.model.PostsResponseItem
import com.task.task.presentation.home.HomePostItem

fun PostsResponseItem.toHomeItem() =
    HomePostItem(body, id, title, userId)

fun List<PostsResponseItem>.toHomeItemsList() =
    map {
        HomePostItem(it.body, it.id, it.title, it.userId)
    }