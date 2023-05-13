package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.newsapp.api.newsResponse.News
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdpator(var items:List<News?>?): RecyclerView.Adapter<NewsAdpator.ViewHolder>() {



    class ViewHolder(val viewBinding: ItemNewsBinding):RecyclerView.ViewHolder(viewBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding=ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items?.get(position)
     holder.viewBinding.textViewAuthor.text=item?.author.toString()
     holder.viewBinding.textViewTitLe.text=item?.title.toString()
     holder.viewBinding.textViewTime.text=item?.publishedAt.toString()

        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.viewBinding.imageViewNews)

    }

    override fun getItemCount(): Int {
        return items?.size?:0
    }

    fun changeData(articles: List<News?>?) {
        items=articles;
        notifyDataSetChanged()
    }
}