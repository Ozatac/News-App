package com.tunahanozatac.appcentapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tunahanozatac.appcentapp.base.BaseViewModel
import com.tunahanozatac.appcentapp.data.api.RetrofitInstance
import com.tunahanozatac.appcentapp.data.db.NewsDatabase
import com.tunahanozatac.appcentapp.data.model.Articles
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : BaseViewModel(application) {

    var newsList: MutableLiveData<List<Articles>> = MutableLiveData()

    init {
        makeApiCall("Türkiye ");
    }

    fun getListObserver(): MutableLiveData<List<Articles>> {
        return newsList
    }

    fun makeApiCall(q: String?) {
        val retrofitInstance = RetrofitInstance.getRetrofit()
        retrofitInstance!!.getAllNews(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getNewsListObserverRx())
    }

    private fun getNewsListObserverRx(): Observer<List<Articles>> {
        return object : Observer<List<Articles>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: List<Articles>) {
                newsList.value = t


            }

            override fun onError(e: Throwable) {
                newsList.value = null
            }

            override fun onComplete() {

            }

        }
    }

    fun showList(showList: List<Articles>) {
        newsList.value = showList
    }

    fun storeInSQLite(list: List<Articles>) {
        launch {
            val dao = NewsDatabase(getApplication()).newsDao()
            dao.getDelete()
            val lisLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].id = lisLong[i].toInt()
                i++
            }
            showList(list)
        }
    }
}