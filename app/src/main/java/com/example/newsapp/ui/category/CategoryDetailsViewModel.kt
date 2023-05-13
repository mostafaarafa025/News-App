package com.example.newsapp.ui.category

import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.ApiConstants
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.sourcesResponse.Source
import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsViewModel:ViewModel() {
    val sourcesLiveData=MutableLiveData<List<Source?>?>()
    val showLoadingLayout=MutableLiveData<Boolean>()
    val showErrorlayout=MutableLiveData<String>()
   public fun loadNewSources() {


        ApiManager
            .getApis()
            .getSources(ApiConstants.apiKey)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    showLoadingLayout.postValue(false)
                    //   viewBinding.loadingIndicator.isVisible = false
                    if (response.isSuccessful) {
                        //we have data
                        sourcesLiveData.value=response.body()?.sources
                    //  sourcesLiveData.postValue(response.body()?.sources) if i in background thread

                    //    bindSourcesInTabLayout(response.body()?.sources)

                    } else {
                        val gson = Gson()
                        val errorRespons =
                            gson.fromJson(
                                response.errorBody()?.string(), SourcesResponse::class.java)
                        showErrorlayout.value=errorRespons.message ?: ""
                     //   showErrorMessageInLayout(errorRespons.message)

                    }

                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                 //   viewBinding.loadingIndicator.isVisible = false
                    showLoadingLayout.value=false
                    showErrorlayout.value=t.localizedMessage?:""
                 //   showErrorMessageInLayout(t.localizedMessage)
                }

            })
    }
}