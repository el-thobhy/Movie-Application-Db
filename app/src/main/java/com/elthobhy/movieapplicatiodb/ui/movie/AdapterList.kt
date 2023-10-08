package com.elthobhy.movieapplicatiodb.ui.movie

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.elthobhy.movieapplicatiodb.R
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.utils.Constants
import com.elthobhy.movieapplicationdb.databinding.ItemListBinding

class AdapterList : Adapter<AdapterList.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val data: MutableList<DomainModel> = mutableListOf()
    private val limit = 10

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DomainModel)
    }

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DomainModel) {
            binding.apply {
                title.text = item.title
                Glide.with(itemView)
                    .load(Constants.IMAGE_LINK + item.backdrop_path)
                    .override(800, 600)
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
                    .into(posterImage)

                tvDate.text = item.releaseDate

                Glide.with(itemView.context)
                    .load(Constants.IMAGE_LINK + item.posterPath)
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(roundImage)

                tvOriginalTitle.text = item.original_title
                tvOverview.text = item.overview
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(item)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return if(data.size >= limit) limit
        else data.size
    }

    fun setData(newListData: List<DomainModel>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

}