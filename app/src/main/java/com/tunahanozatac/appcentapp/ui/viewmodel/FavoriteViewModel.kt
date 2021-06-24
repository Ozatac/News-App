package com.tunahanozatac.appcentapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tunahanozatac.appcentapp.data.api.RetrofitInstance
import com.tunahanozatac.appcentapp.data.model.NewsResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel : ViewModel() {

    private var newsList: MutableLiveData<NewsResponse> = MutableLiveData()

    fun makeTopApiCall(q: String) {
        val retrofitInstance = RetrofitInstance.getRetrofit()
        retrofitInstance!!.getAllTop(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getTopListObserverRx())
    }

    private fun getTopListObserverRx(): Observer<NewsResponse> {
        return object : Observer<NewsResponse> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: NewsResponse) {
                newsList.value = t
            }

            override fun onError(e: Throwable) {
                newsList.value = null
            }

            override fun onComplete() {
            }
        }
    }

    fun getTopListObserver(): MutableLiveData<NewsResponse> {
        return newsList
    }
}