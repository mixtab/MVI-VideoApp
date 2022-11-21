package ua.com.tabarkevych.video_app.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.com.tabarkevych.video_app.data.local.database.entity.VideoEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class VideoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")val id: String,
    @ColumnInfo(name = "image")val image: String,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "description")val description:String
){
    companion object {

        const val TABLE_NAME = "video"
    }
}