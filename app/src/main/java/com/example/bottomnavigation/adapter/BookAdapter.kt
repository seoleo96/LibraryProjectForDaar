package com.example.bottomnavigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottomnavigation.R
import com.example.bottomnavigation.data.model.Book

class BookAdapter : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    private var list = listOf<Book>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(current: Book) {
            val imageBook = itemView.findViewById<ImageView>(R.id.image_book)
            val nameBook = itemView.findViewById<TextView>(R.id.name_book)
            nameBook.text = current.name
            Glide.with(itemView.context).load(current.imageUrl).centerCrop().into(imageBook)
        }
    }

    fun submitList(list: List<Book>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}