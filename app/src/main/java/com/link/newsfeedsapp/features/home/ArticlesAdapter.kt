package com.link.newsfeedsapp.features.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.link.newsfeedsapp.R
import com.link.newsfeedsapp.core.models.Article
import com.link.newsfeedsapp.databinding.ItemArticleBinding
import com.link.newsfeedsapp.features.details.DetailsActivity

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {
    private var data = ArrayList<Article>()

    fun addData(data: List<Article>, start: Int, count: Int) {
        this.data.addAll(data)
        notifyItemRangeChanged(start, count)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        val context = holder.binding.root.context
        holder.bind(data[position])

        holder.binding.root.setOnClickListener {
            context.startActivity(Intent(context, DetailsActivity::class.java).apply {
                putExtra("title", item.title)
                putExtra("date", item.publishedAt)
                putExtra("image", item.urlToImage)
                putExtra("author", item.author)
                putExtra("description", item.description)
                putExtra("url", item.url)
            })
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Article,
        ) {
            val context = binding.root.context
            binding.tvTitle.text = item.title
            binding.tvAuthor.text = item.author
            binding.tvPublishDate.text = item.publishedAt
            Glide.with(context)
                .load(item.urlToImage).placeholder(R.drawable.placeholder)
                .into(binding.ivArticleImg)
        }
    }
}