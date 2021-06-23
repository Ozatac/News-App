package com.tunahanozatac.appcentapp.data.model

import androidx.room.Entity
import java.io.Serializable

@Entity
data class NewsResponse(
    var status: String,
    var totalResults: Int,
    var articles: List<Articles>
) : Serializable