package com.example.breathingreport.ui.first

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.breathingreport.R

class FirstViewAdapter(
    private val list: List<FirstItemModel>,
    private val listener: ListListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface ListListener {
        fun onClickItem(tappedView: View, itemModel: FirstItemModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.first_item, parent, false)
        return FirstViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.first_item_textview).text = list[position].text
        holder.itemView.setOnClickListener {
            listener.onClickItem(it, list[position])
        }
    }

    override fun getItemCount(): Int = list.size
}