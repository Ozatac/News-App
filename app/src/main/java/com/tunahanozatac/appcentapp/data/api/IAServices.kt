package com.tunahanozatac.appcentapp.data.api

import com.tunahanozatac.appcentapp.data.model.NewsResponse
import com.tunahanozatac.appcentapp.utils.ConstantParams.Companion.API_KEY
import com.tunahanozatac.appcentapp.utils.ConstantParams.Companion.END_POINT
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IAServices {

    @GET(END_POINT)
    fun getAllNews(
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Observable<NewsResponse>
}