package com.agilbudiprasetyo.medicineslistapp

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agilbudiprasetyo.medicineslistapp.databinding.ActivityDetailBinding
import com.agilbudiprasetyo.medicineslistapp.model.Medicines
import com.agilbudiprasetyo.medicineslistapp.ui.MedicinesFragment
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: Medicines? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MedicinesFragment.EXTRA_MEDICINES, Medicines::class.java)
        } else {
            intent.getParcelableExtra(MedicinesFragment.EXTRA_MEDICINES)
        }

        if (data != null) {
            with(binding) {
                tvDetailTitle.text = data.name
                tvDetailDescription.text = data.description
                Glide.with(this@DetailActivity)
                    .load(data.image)
                    .into(ivDetailImage)
            }
        }
    }
}