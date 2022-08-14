package com.link.newsfeedsapp.features.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaopiz.kprogresshud.KProgressHUD
import com.link.newsfeedsapp.R
import com.link.newsfeedsapp.databinding.ActivityMainBinding
import com.link.newsfeedsapp.utilities.UtilsFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var kProgressHUD: KProgressHUD? = null
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerview()
        observeData()
        getArticles()
        actions()
    }

    private fun actions() {
        binding.btnOpenDrawer.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            else
                binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.lytExplore.setOnClickListener {
            UtilsFunctions.showToast("explore")
        }
        binding.lytGallery.setOnClickListener {
            UtilsFunctions.showToast("gallery")
        }
        binding.lytWishlist.setOnClickListener {
            UtilsFunctions.showToast("wishlist")
        }
        binding.lytMagazine.setOnClickListener {
            UtilsFunctions.showToast("magazine")
        }
        binding.lytLiveChat.setOnClickListener {
            UtilsFunctions.showToast("live chat")
        }
    }

    private fun setupRecyclerview() {
        articlesAdapter=ArticlesAdapter()
        binding.rvArticles.layoutManager = LinearLayoutManager(this)
        binding.rvArticles.adapter = articlesAdapter
    }

    private fun observeData() {
        viewModel.articlesResponse.observe(this) {
            articlesAdapter.addData(it, 0, it.size - 1)
            kProgressHUD?.dismiss()
        }
        viewModel.error.observe(this) {
            UtilsFunctions.showToast(it)
            kProgressHUD?.dismiss()
        }
    }

    private fun getArticles() {
        if (UtilsFunctions.isNetworkAvailable()) {
            if (kProgressHUD == null)
                kProgressHUD = UtilsFunctions.showProgress(this)
            else
                kProgressHUD?.show()
            viewModel.getArticles()
        } else
            UtilsFunctions.showToast(getString(R.string.no_internet_connection))
    }
}