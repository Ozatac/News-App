package com.tunahanozatac.appcentapp.data.model

data class NewsResponse(
    var status: String,
    var totalResults: Int,
    var articles: List<Articles>
)