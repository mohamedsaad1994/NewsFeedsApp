package com.link.newsfeedsapp.features.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.link.newsfeedsapp.R
import com.link.newsfeedsapp.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDetailsData()
        actions()
    }

    private fun actions() {
        binding.btnOpenSite.setOnClickListener {
            val url = intent.getStringExtra("url")
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun setDetailsData() {
        binding.tvTitle.text = intent.getStringExtra("title")
        binding.tvAuthor.text = intent.getStringExtra("author")
        binding.tvPublishDate.text = intent.getStringExtra("date")
        binding.tvDescription.text = intent.getStringExtra("description")

        Glide.with(this)
            .load(intent.getStringExtra("image")).placeholder(R.drawable.placeholder)
            .into(binding.ivArticleImg)
    }
}