package com.example.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.home.R
import com.example.home.models.BookDvo

class BookAdapter : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    private var list = listOf<BookDvo>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(current: BookDvo) {
            val imageBook = itemView.findViewById<ImageView>(R.id.image_book)
            val nameBook = itemView.findViewById<TextView>(R.id.name_book)
            val authorBook = itemView.findViewById<TextView>(R.id.name_author)
            nameBook.text = current.name
            authorBook.text = current.author
//            imageBook.text = current.imageUrl
//            Glide.with(itemView).load(itemView.resources.getDrawable(R.drawable.xobbit)).centerCrop().into(imageBook)
            if (current.imageUrl == "null"){
                Glide.with(itemView).load(itemView.resources.getDrawable(R.drawable.default_book))
                    .centerCrop().into(imageBook)
            } else {
                Glide.with(itemView).load(itemView.resources.getDrawable(R.drawable.xobbit))
                    .centerCrop().into(imageBook)
            }


        }
    }

    fun submitList(list: List<BookDvo>) {
        this.list = list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return if (list.size > 4) {
            4
        }else{
            list.size
        }
    }
}