package ua.com.tabarkevych.video_app.data.mapper.model

import ua.com.tabarkevych.video_app.data.local.database.entity.VideoEntity
import ua.com.tabarkevych.video_app.data.remote.dto.VideoDto
import ua.com.tabarkevych.video_app.domain.model.Video

fun VideoDto.toEntity() = VideoEntity(id,  image, title, description)

fun Video.toEntity() = VideoEntity(id,  image, title, description)

fun List<Video>.toEntity() = map { it.toEntity() }

fun VideoEntity.toDomain() = Video(id,  image, title, description)

fun List<VideoEntity>.toDomains() = map { it.toDomain() }

