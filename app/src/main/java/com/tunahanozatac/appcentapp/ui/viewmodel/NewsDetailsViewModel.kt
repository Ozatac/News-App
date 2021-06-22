package com.tunahanozatac.appcentapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tunahanozatac.appcentapp.data.model.NewsResponse

class NewsDetailsViewModel() : ViewModel() {

    var newsDetailList: MutableLiveData<NewsResponse> = MutableLiveData()


}