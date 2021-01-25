package com.example.newapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newapplication.R
import com.example.newapplication.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewholder>() {

    inner class ArticleViewholder(itemview : View) :RecyclerView.ViewHolder(itemview)

    private val DifferCallBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
             return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.id == newItem.id

        }
    }

    val differ = AsyncListDiffer(this,DifferCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewholder {
        return ArticleViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewholder, position: Int) {
       val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
          //  tvDescription.text = article.description
            tvTitle.text = article.title 
          //  tvPublishedAt.text = article.publishedAt
            setOnClickListener{
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((Article)->Unit)? = null

    fun setOnClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }
}