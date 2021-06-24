package com.tunahanozatac.appcentapp.data.api

import com.tunahanozatac.appcentapp.data.model.NewsResponse
import com.tunahanozatac.appcentapp.utils.ConstantParams.Companion.API_KEY
import com.tunahanozatac.appcentapp.utils.ConstantParams.Companion.END_POINT
import com.tunahanozatac.appcentapp.utils.ConstantParams.Companion.TOP_END_POINT
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IAServices {
    //top-headlines?country=tr&apiKey=05e78c4a5dcd44fbbbc269a75098062c

    @GET(END_POINT)
    fun getAllNews(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Observable<NewsResponse>

    @GET(TOP_END_POINT)
    fun getAllTop(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Observable<NewsResponse>
}