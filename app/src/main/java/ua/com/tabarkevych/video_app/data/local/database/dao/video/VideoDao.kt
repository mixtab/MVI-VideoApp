package ua.com.tabarkevych.video_app.data.local.database.dao.video

import androidx.room.Dao
import androidx.room.Query

import ua.com.tabarkevych.video_app.data.local.database.dao.base.BaseDao
import ua.com.tabarkevych.video_app.data.local.database.entity.VideoEntity
import ua.com.tabarkevych.video_app.data.local.database.entity.VideoEntity.Companion.TABLE_NAME

@Dao
interface VideoDao : BaseDao<VideoEntity> {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAll(): List<VideoEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): VideoEntity

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()
}