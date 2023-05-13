package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.ApiConstants
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.newsResponse.News
import com.example.newsapp.api.newsResponse.NewsResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {
       val  showLoadingLayout=MutableLiveData<Boolean>()
        val showError=MutableLiveData<String>()
    val newsList=MutableLiveData<List<News?>?>()
    public fun getNews(sourceId:String) {
        showLoadingLayout.value=false
        ApiManager.getApis()
            .getNews(ApiConstants.apiKey, sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
//                        bindNewsList(response.body()?.articles)
                        newsList.value=response.body()?.articles
                        return
                    }
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.string(), NewsResponse::class.java)
                  //  showErrorMessageInLayout(errorResponse.message)
                    showError.value=errorResponse.message ?:""
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    showError.value=t.localizedMessage
                }

            })
    }
}