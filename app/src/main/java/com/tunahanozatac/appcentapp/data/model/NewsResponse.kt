package com.tunahanozatac.appcentapp.data.model

import java.io.Serializable
import java.util.*

data class NewsResponse(
    var status: String,
    var totalResults: Int,
    var articles: ArrayList<Articles>
) : Serializable