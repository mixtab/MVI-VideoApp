package ua.com.tabarkevych.video_app.data.local.database

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.com.tabarkevych.video_app.data.local.database.dao.video.VideoDao
import ua.com.tabarkevych.video_app.data.local.database.entity.VideoEntity


@Database(
    entities = [
        VideoEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    private var isDataBaseCreated = MutableLiveData<Boolean>()

    companion object {

        private const val dbName = "DB_VIDEO"
        private var dbInstance: AppDatabase? = null

        fun getInstance(app: Context): AppDatabase {
            if (dbInstance == null) {
                synchronized(AppDatabase::class.java) {
                    dbInstance = createDataBase(app)
                    dbInstance!!.updateDatabaseCreated(app)
                }
            }
            return dbInstance!!
        }

        private fun createDataBase(app: Context): AppDatabase {
            return Room.databaseBuilder(app, AppDatabase::class.java, dbName)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(dbName).exists()) {
            provideDatabaseCreated()
        }
    }

    private fun provideDatabaseCreated() {
        isDataBaseCreated.postValue(true)
    }

    abstract fun videoDao(): VideoDao


}