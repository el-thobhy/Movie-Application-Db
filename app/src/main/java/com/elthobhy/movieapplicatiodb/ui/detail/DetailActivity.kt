package com.elthobhy.movieapplicatiodb.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.elthobhy.movieapplicatiodb.databinding.ActivityDetailBinding
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent.getParcelableExtra<DomainModel>(Constants.DATA)
        if (intent != null) {
            detailViewModel.getDetailById(intent.id).observe(this) {
                showDetail(it)
            }
        }
        actionBarSetting()
    }

    private fun actionBarSetting() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun showDetail(intent: DomainModel) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(Constants.IMAGE_LINK + intent.backdropPath)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(imageBackdrop)
            titleDetail.text = intent.title
            tvReleaseDate.text = intent.releaseDate
            voteCount.text = intent.voteCount.toString()
            popularCount.text = intent.popularity.toString()
            starCount.text = intent.voteAverage.toString()
            overviewDetail.text = intent.overview
        }
    }
}