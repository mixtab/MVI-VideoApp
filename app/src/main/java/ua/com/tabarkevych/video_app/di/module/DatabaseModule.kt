package ua.com.tabarkevych.video_app.di.module

import android.content.Context

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.com.tabarkevych.video_app.data.local.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) = AppDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideVideoDao(db: AppDatabase) = db.videoDao()

}