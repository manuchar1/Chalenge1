package com.example.chalenge1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chalenge1.databinding.ItemNewsLayoutBinding
import com.example.chalenge1.databinding.LoadingItemBinding
import com.example.chalenge1.models.Constants.VIEW_TYPE_NEWS
import com.example.chalenge1.models.Constants.VIEW_TYPE_LOADER
import com.example.chalenge1.models.NewsModel


class NewsAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsList: List<NewsModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var loadingMore = false
        set(value) {
            field = value
            notifyItemChanged(itemCount - 1)
        }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount - 1 == position) VIEW_TYPE_LOADER else VIEW_TYPE_NEWS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            VIEW_TYPE_LOADER -> LoadingViewHolder(
                LoadingItemBinding.inflate(LayoutInflater.from(parent.context))
            )
            VIEW_TYPE_NEWS -> NewsViewHolder(
                binding = ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context)),
            )
            else -> throw RuntimeException("unknown ViewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> {
                val item = newsList[position]
                holder.binding.titleTextView.text = item.titleKA
                Glide.with(holder.itemView)
                    .load(item.cover)
                    .into(holder.binding.newsCoverImageView)
                holder.binding.root.tag = item
            }
            is LoadingViewHolder -> {
                holder.binding.loader.visibility = if (loadingMore) View.VISIBLE else View.GONE
            }
        }
    }

    override fun getItemCount() = newsList.size + 1

    class NewsViewHolder(
        val binding: ItemNewsLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    class LoadingViewHolder(
        val binding: LoadingItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class LoaderSpanSizeLookup(private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) :
        GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (adapter.itemCount - 1 == position) 2 else 1
        }
    }

}


