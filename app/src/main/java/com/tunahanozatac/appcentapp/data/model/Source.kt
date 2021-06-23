package com.tunahanozatac.appcentapp.data.model

import androidx.room.Entity
import java.io.Serializable

@Entity
data class Source(
    var id: String,
    var name: String
)
    :Serializable