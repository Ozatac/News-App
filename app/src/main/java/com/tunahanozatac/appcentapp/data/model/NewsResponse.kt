package com.tunahanozatac.appcentapp.data.model

import java.io.Serializable

data class NewsResponse(
    var status: String,
    var totalResults: Int,
    var articles: List<Articles>
) : Serializable