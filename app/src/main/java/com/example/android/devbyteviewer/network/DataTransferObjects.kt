package com.example.android.devbyteviewer.network

import com.example.android.devbyteviewer.database.DatabaseVideo
import com.example.android.devbyteviewer.domain.DevByteVideo
import com.squareup.moshi.JsonClass

/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 *
 * @see domain package for
 */

/**
 * VideoHolder holds a list of Videos.
 *
 * This is to parse first level of our network result which looks like
 *
 * {
 *   "videos": []
 * }
 * Расписываем первый объект, в котором лежит список объектов
 * это просто лестница доступа, без этого класса нельзя
 * названия классов придумываем сами
 *
 * */
@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

/**
 * Videos represent a devbyte that can be played.
 * Это уже класс-объект отдельного элемента списка со своими полями
 * описываем их согласно API
 */
@JsonClass(generateAdapter = true)
data class NetworkVideo(
        val title: String,
        val description: String,
        val url: String,
        val updated: String,
        val thumbnail: String,
        val closedCaptions: String?)

/**
 * Convert Network results to domain objects
 * сразу делаем extension метод для перевода полученных данных из API
 * в класс-модель DevByteVideo и сразу возращаем весь список
 */
fun NetworkVideoContainer.asDomainModel(): List<DevByteVideo> {
    return videos.map {
        DevByteVideo(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}


/**
 * Convert Network results to database objects
 * сразу делаем extension метод для перевода данных из класса-модели
 * в объект класса DatabaseVideo для добавления в базу, возращаем весь список
 */
fun NetworkVideoContainer.asDatabaseModel(): List<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}
//Так выглядит кусок API
// у нас есть объект videos и в нем лежит СПИСОК объектов
//{
//  "videos": [
//    {
//      "title": "Android Jetpack: EmojiCompat",
//      "description": "With the EmojiCompat library, part of Jetpack, your app can get backwards-compatible emoji support on devices with API level 19 and higher and get rid of the blank square characters. To use EmojiCompat, initialize the library when the application starts by using downloadable or bundled fonts. Start supporting emojis in your text views, either by integrating the provided widgets or update your custom views by using the provided helper classes. \n\nFor more information:\nEmojiCompat guide → https://goo.gl/7bEoXB \nEmojiCompat sample app → https://goo.gl/7bEoXB \nGoogle I/O 2017 talk on “What’s new in Support Library” → https://goo.gl/KeRvqB\n\nWatch more Android Jetpack videos here → https://goo.gl/kw8LPv\n\nSubscribe to the Android Developers channel here → https://goo.gl/vLYDU\n\n#jetpack #featured",
//      "url": "https://www.youtube.com/watch?v=sYGKUtM2ga8",
//      "updated": "2018-06-07T17:09:43+00:00",
//      "thumbnail": "https://i4.ytimg.com/vi/sYGKUtM2ga8/hqdefault.jpg"
//    },
//    {
//      "title": "Android Jetpack: Room",
//      "description": "Florina Muntenescu introduces Room, the database persistence library for Jetpack. Room allows you to:\n* write less boilerplate code\n* map query results to objects automatically\n* provide compile-time checked queries \n* test database operations, including migrations, more easily\n* avoid performing any database operations on the main thread \n\nTake a look at the resources below for more information!\n\nGoogle I/O Room Talk → https://goo.gl/Zumb6a\nRoom persistence library guide → https://goo.gl/s23p8y\nArchitecture Components code samples → https://goo.gl/xuAQQi\nPersistence Codelabs → https://goo.gl/AMwWvx\nIncrementally migrate from SQLite to Room → https://goo.gl/cDCFyN\n7 Pro-tips for Room → https://goo.gl/Kfkzvw\nRoom with a View codelab → https://goo.gl/b8KjA2\n\nWatch more Android Jetpack videos here → https://goo.gl/kw8LPv\n\nSubscribe to the Android Developers channel here → https://goo.gl/vLYDU\n\n#jetpack #featured",
//      "url": "https://www.youtube.com/watch?v=SKWh4ckvFPM",
//      "updated": "2018-06-04T16:57:23+00:00",
//      "thumbnail": "https://i4.ytimg.com/vi/SKWh4ckvFPM/hqdefault.jpg"
//    },
//"""

