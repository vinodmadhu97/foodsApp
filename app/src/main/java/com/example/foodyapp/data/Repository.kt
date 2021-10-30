package com.example.foodyapp.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//repository instance is survave the life of the activity classes
@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource: RemoteDataSource) {
    val remote = remoteDataSource
}