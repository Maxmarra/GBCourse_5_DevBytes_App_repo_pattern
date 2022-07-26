package com.example.android.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.devbyteviewer.domain.ModelVideo

/**
 * DatabaseVideo represents a video entity in the database.
 * Это класс объекта на основе которого создаются записи в базе данных
 * Все его поля точно такие же как и класса DevByteVideo из модели для API
 */
@Entity
data class DatabaseVideo constructor(
        @PrimaryKey
        val url: String,
        val updated: String,
        val title: String,
        val description: String,
        val thumbnail: String)


/**
 * Map DatabaseVideos to domain entities
 * Так как принимать из сети мы будем объект по модели DevByteVideo,
 * для того чтобы сохранить его в базу, нам нужно будет перевести (наложить)
 * его на класс DatabaseVideo отвечающий за добавление этого объекта в базу,
 * если же мы делаем наоборот из класса базы в класс API, то мы применяем
 * метод ниже - где список объектов из базы мы переводим в список объектов
 * класса из модели
 */
fun List<DatabaseVideo>.asDomainModel(): List<ModelVideo> {
        return map {
                ModelVideo(
                        url = it.url,
                        title = it.title,
                        description = it.description,
                        updated = it.updated,
                        thumbnail = it.thumbnail
                )
        }
}
