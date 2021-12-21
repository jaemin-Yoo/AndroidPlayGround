package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.TextEntity

class TextListAdapter internal constructor(context: Context)
    : RecyclerView.Adapter<TextListAdapter.TextViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contents = emptyList<TextEntity>()

    inner class TextViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textName: TextView = itemView.findViewById(R.id.text_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_item, parent, false)
        return TextViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val current = contents[position]
        holder.textName.text = current.title
    }

    internal fun setContents(contents: List<TextEntity>) {
        this.contents = contents
        notifyDataSetChanged()
    }

    override fun getItemCount() = contents.size
}