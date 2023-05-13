package com.example.newsapp.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.api.ApiConstants
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.sourcesResponse.Source
import com.example.newsapp.api.newsResponse.News
import com.example.newsapp.api.newsResponse.NewsResponse
import com.example.newsapp.databinding.FragmentNewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    lateinit var source: Source
    companion object {
        fun getInstance(source: Source): NewsFragment {
            val newNewsFragment = NewsFragment()
            newNewsFragment.source = source
            return newNewsFragment
        }
    }
    lateinit var viewBinding: FragmentNewsBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(NewsViewModel::class.java)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_news, container, false)
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
       // getNews()
        viewModel.getNews(source.id?:"")
        subscribeToLiveData()
    }
        fun subscribeToLiveData(){
            viewModel.newsList.observe(viewLifecycleOwner){
                bindNewsList(it)
            }
        viewModel.showError.observe(viewLifecycleOwner){
            showErrorMessageInLayout(it)
        }
            viewModel.showLoadingLayout.observe(viewLifecycleOwner){
        if (it) showLoadingLayuout()
                else hideLoadingLayuout()
            }
        }
    val newsAdaptor=NewsAdpator(null)
    private fun initRecyclerView() {
        viewBinding.newsRecyclerView.adapter=newsAdaptor
    }

    private fun showLoadingLayuout() {
        viewBinding.loadingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }
    private fun hideLoadingLayuout() {
        viewBinding.loadingIndicator.isVisible = false

    }

    private fun showErrorMessageInLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loadingIndicator.isVisible = false
        viewBinding.errorMsg.text = message

    }

    private fun bindNewsList(articles: List<News?>?) {
        viewBinding.loadingIndicator.isVisible=false
        viewBinding.errorLayout.isVisible=false
        newsAdaptor.changeData(articles)
    }

}