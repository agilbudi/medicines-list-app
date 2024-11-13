package com.agilbudiprasetyo.medicineslistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.agilbudiprasetyo.medicineslistapp.R
import com.agilbudiprasetyo.medicineslistapp.databinding.MedicineItemBinding
import com.agilbudiprasetyo.medicineslistapp.model.Medicines
import com.bumptech.glide.Glide

class ListMedicinesAdepter(private val listMedicines: ArrayList<Medicines>) : RecyclerView.Adapter<ListMedicinesAdepter.ListViewHolder>(){
    class ListViewHolder(val binding: MedicineItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback{
        fun onItemClicked(data: Medicines)
    }
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = MedicineItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, image, description) = listMedicines[position]
        with(holder) {
            binding.tvTitleItem.text = name
            binding.tvDetailItem.text = description
            Glide.with(itemView.context)
                .load(image)
                .into(binding.ivItem)

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listMedicines[holder.adapterPosition])
            }
        }

    }

    override fun getItemCount() = listMedicines.size
}