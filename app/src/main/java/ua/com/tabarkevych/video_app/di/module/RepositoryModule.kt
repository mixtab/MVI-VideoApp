package ua.com.tabarkevych.video_app.di.module


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.com.tabarkevych.video_app.data.repository.VideoRepositoryImpl
import ua.com.tabarkevych.video_app.domain.repository.VideoRepository

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindVideoRepository(repository: VideoRepositoryImpl): VideoRepository

}