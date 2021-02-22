package com.example.rickandmortyandroid.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyandroid.R
import com.example.rickandmortyandroid.models.data.Character
import com.squareup.picasso.Picasso


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var chars = ArrayList<Character>()

    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val ivFront = view.findViewById<ImageView>(R.id.iv_front)
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvStatus = view.findViewById<TextView>(R.id.tv_status)
        val tvGender = view.findViewById<TextView>(R.id.tv_gender)
        val tvCreated = view.findViewById<TextView>(R.id.tv_created)
        val tvSpecie = view.findViewById<TextView>(R.id.tv_specie)

    }

    override fun getItemCount() = chars.size

    fun updateList(list: List<Character>) {
        chars = ArrayList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutItemRest = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_char, parent, false)
        return HomeViewHolder(layoutItemRest)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val picasso = Picasso.get()
        val currItem = chars[position]
        picasso.load(currItem.image).into(holder.ivFront)
        holder.tvCreated.text = currItem.created
        holder.tvName.text = currItem.name
        holder.tvStatus.text = " (" + currItem.status + ")"
        holder.tvGender.text = currItem.gender
        holder.tvSpecie.text = currItem.species
    }

}
