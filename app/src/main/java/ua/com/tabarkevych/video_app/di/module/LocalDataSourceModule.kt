package ua.com.tabarkevych.video_app.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.com.tabarkevych.video_app.data.local.datasource.video.VideoLocalDataSource
import ua.com.tabarkevych.video_app.data.local.datasource.video.VideoLocalDataSourceImpl

@InstallIn(SingletonComponent::class)
@Module
interface LocalDataSourceModule {

    @Binds
    fun bindVideoLocalDataSource(source: VideoLocalDataSourceImpl): VideoLocalDataSource
}