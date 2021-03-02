package com.example.find.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.find.R
import com.example.find.models.BookDvoFind

class BookAdapterFind : RecyclerView.Adapter<BookAdapterFind.MyViewHolder>() {

    private var list = listOf<BookDvoFind>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(current: BookDvoFind) {
            val imageBook = itemView.findViewById<ImageView>(R.id.image_book_search)
            val nameBook = itemView.findViewById<TextView>(R.id.name_book_search)
            val authorBook = itemView.findViewById<TextView>(R.id.name_author_search)
            nameBook.text = current.name
            authorBook.text = current.author
//            imageBook.text = current.imageUrl
            Glide.with(itemView).load(itemView.resources.getDrawable(R.drawable.xobbit))
                .centerCrop().into(imageBook)
        }
    }

    fun submitList(list: List<BookDvoFind>) {
        this.list = list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_books_search, parent, false)
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