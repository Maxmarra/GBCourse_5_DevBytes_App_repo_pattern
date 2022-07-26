package com.example.android.devbyteviewer.domain
import com.example.android.devbyteviewer.util.smartTruncate

/**
 * Videos represent a devbyte that can be played.
 * Это класс для объекта получаемого через API
 */
data class ModelVideo(val title: String,
                      val description: String,
                      val url: String,
                      val updated: String,
                      val thumbnail: String) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}