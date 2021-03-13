package com.example.find.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.find.R
import com.example.find.models.GenresDvoFind

class GenresAdapterFind(private val onClickListener: (Boolean, GenresDvoFind) -> Unit): RecyclerView.Adapter<GenresAdapterFind.MyViewHolder>() {
//    private val onClickListener: (Boolean, GenresDvoFind) -> Unit
    private var list = listOf<GenresDvoFind>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun submitList(list: List<GenresDvoFind>) {
        this.list = list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        val genre = holder.itemView.findViewById<CheckedTextView>(R.id.checked_textview)
        val button = holder.itemView.findViewById<Button>(R.id.button)
        genre.text = current.title

        genre.setOnClickListener {
            if (genre.isChecked()) {
                genre.setChecked(false);
                genre.setCheckMarkDrawable(R.drawable.ic_baseline_check_box_outline_blank_24)
                onClickListener.invoke(false, current)
            } else {
                genre.setChecked(true);
                genre.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
                onClickListener.invoke(true, current)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}