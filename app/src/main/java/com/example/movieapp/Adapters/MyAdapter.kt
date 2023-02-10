package com.example.movieapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Models.Movie
import com.example.movieapp.databinding.ItemRvBinding

class MyAdapter(
    val context: Context,
    var myList: ArrayList<Movie>,
    var myItemClickListener: OnMyItemClickListener
) : RecyclerView.Adapter<MyAdapter.Vh>() {

    inner class Vh(private val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(movie: Movie, position: Int) {
            itemRvBinding.txtNameItemRv.text = movie.name
            itemRvBinding.txtAuthors.text = movie.author
            itemRvBinding.txtSanaItemRv.text = movie.date

            itemRvBinding.btnDelete.setOnClickListener {
                myItemClickListener.onMyItemClickDelete(movie, position)
            }

            itemRvBinding.btnEdit.setOnClickListener {
                myItemClickListener.onMyItemClickDEdit(movie, position)
            }

            itemRvBinding.linRv.setOnClickListener {
                myItemClickListener.onMyLinClick(movie, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(myList[position], position)
    }

    interface OnMyItemClickListener {
        fun onMyItemClickDelete(movie: Movie, position: Int)
        fun onMyItemClickDEdit(movie: Movie, position: Int)
        fun onMyLinClick(movie: Movie, position: Int)

    }
}