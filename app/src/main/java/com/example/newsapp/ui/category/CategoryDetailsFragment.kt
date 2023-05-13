package com.example.newsapp.ui.category
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
import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.ui.news.NewsFragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CategoryDetailsFragment : Fragment() {
    lateinit var viewBinding: FragmentCategoryBinding
   lateinit var viewModel: CategoryDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewModel=ViewModelProvider(this).get(CategoryDetailsViewModel::class.java)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentCategoryBinding.inflate(
            inflater, container, false
        )
        // return inflater.inflate(R.layout.fragment_category, container, false)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  loadNewSources()
         viewModel.loadNewSources()
            subscribeToLiveData()
    }
    fun subscribeToLiveData(){
        viewModel.sourcesLiveData.observe(viewLifecycleOwner
        ) {
            bindSourcesInTabLayout(it)
        }
        viewModel.showLoadingLayout.observe(viewLifecycleOwner){show ->
            if (show){
                showLoadingLayuout()
            }else{
            hideLoadingLayuout()
            }
        }
        viewModel.showErrorlayout.observe(viewLifecycleOwner){
                showErrorMessageInLayout(it)
        }
    }
    fun changeNewsFragment(source: Source){
        childFragmentManager
            .beginTransaction()
            .replace(R.id.newsDetails_fragment_container,NewsFragment.getInstance(source))
            .commit()
    }
    private fun showLoadingLayuout(){
        viewBinding.loadingIndicator.isVisible=true
        viewBinding.errorLayout.isVisible=false
    }
    private fun hideLoadingLayuout(){
        viewBinding.loadingIndicator.isVisible=false

    }
    private fun showErrorMessageInLayout(message: String?) {
        viewBinding.errorLayout.isVisible=true
        viewBinding.loadingIndicator.isVisible=false
        viewBinding.errorMsg.text=message

    }
    fun bindSourcesInTabLayout(sourcesList: List<Source?>?) {
        sourcesList?.forEach {
           val tab=  viewBinding.tabLayout.newTab()
            tab.text=it?.name
            tab.tag=it
            viewBinding.tabLayout.addTab(tab)
        }
        viewBinding.tabLayout
            .addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source=tab?.tag as Source
                changeNewsFragment(source)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?)  {
                val source=tab?.tag as Source
                changeNewsFragment(source)
            }

        })
        viewBinding.tabLayout.getTabAt(0)?.select()
    }
    }
