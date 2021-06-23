package com.tunahanozatac.appcentapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Articles(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String,
) : Serializable
