package com.tunahanozatac.appcentapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tunahanozatac.appcentapp.data.model.Articles

@Dao
interface NewsDao {

    @Insert
    suspend fun insertAll(vararg news: Articles): List<Long>

    @Query("SELECT * FROM articles")
    suspend fun getAllNews(): List<Articles>

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getNews(id: Int): Articles

    @Query("DELETE FROM articles ")
    suspend fun getDelete()
}