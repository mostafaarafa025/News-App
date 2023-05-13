package com.example.newsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.category.CategoryDetailsFragment

class MainActivity : AppCompatActivity() {
//    val apiKey="0522de2a4ef34ab3b98da4fbdc887b50"
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,CategoryDetailsFragment())
            .commit()


//
//        ApiManager.getApis()
//            .getSources(apiKey)
//            .enqueue(object :Callback<SourcesResponse>{
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    Log.e("response body",response.body()?.toString()?:"")
//                    Log.e("response code",response.code().toString())
//                    Log.e("response isSuccessful",response.isSuccessful.toString())
//                    Log.e("error body",response.errorBody()?.string()?:"")

//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    Log.e("error",t?.localizedMessage?:"")
//                }
//
//            })
    }
}