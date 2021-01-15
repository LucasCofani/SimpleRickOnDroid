package com.example.rickandmortyandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyandroid.R
import com.example.rickandmortyandroid.models.Character
import com.squareup.picasso.Picasso


class HomeAdapter : ListAdapter<Character, HomeAdapter.HomeViewHolder>( DiffCallback()) {


    inner class HomeViewHolder(view : View): RecyclerView.ViewHolder(view){

        val ivFront = view.findViewById<ImageView>(R.id.iv_front)
        val tvName =  view.findViewById<TextView>(R.id.tv_name)
        val tvStatus =  view.findViewById<TextView>(R.id.tv_status)
        val tvGender =  view.findViewById<TextView>(R.id.tv_gender)
        val tvCreated =  view.findViewById<TextView>(R.id.tv_created)
        val tvSpecie =  view.findViewById<TextView>(R.id.tv_specie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_char,parent,false))

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val picasso = Picasso.get()
        val currItem = getItem(position)
        picasso.load(currItem.image).into(holder.ivFront)
        holder.tvCreated.text = currItem.created
        holder.tvName.text = currItem.name
        holder.tvStatus.text = " (" + currItem.status + ")"
        holder.tvGender.text = currItem.gender
        holder.tvSpecie.text = currItem.species
    }

    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
