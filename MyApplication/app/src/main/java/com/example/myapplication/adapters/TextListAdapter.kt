package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.TextEntity
import com.example.myapplication.databinding.RecyclerItemBinding

/**
 *  RecyclerView + DataBinding
 */

class TextListAdapter internal constructor(context: Context)
    : RecyclerView.Adapter<TextListAdapter.TextViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contents = emptyList<TextEntity>()

    inner class TextViewHolder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(content: TextEntity){
            binding.content = content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val binding = RecyclerItemBinding.inflate(inflater, parent, false)
        return TextViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(contents[position])
    }

    internal fun setContents(contents: List<TextEntity>) {
        this.contents = contents
        notifyDataSetChanged()
    }

    override fun getItemCount() = contents.size
}