package com.task.task.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.task.databinding.ItemPostBinding

class HomeAdapter(
    private val onClickListener: (Int) -> Unit
) : ListAdapter<HomePostItem, HomeAdapter.ViewHolder>(HomeDiffUtil()) {

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            userIdTxt.text = "User${item.userId}"
            postTitleTxt.text = item.title
            rootLayout.setOnClickListener { onClickListener(item.id) }
        }
    }
}

class HomeDiffUtil : DiffUtil.ItemCallback<HomePostItem>() {
    override fun areItemsTheSame(oldItem: HomePostItem, newItem: HomePostItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomePostItem, newItem: HomePostItem): Boolean {
        return oldItem == newItem
    }
}