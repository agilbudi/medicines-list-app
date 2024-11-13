package com.agilbudiprasetyo.medicineslistapp.view_model

import androidx.lifecycle.ViewModel
import com.agilbudiprasetyo.medicineslistapp.model.Medicines

class MainViewModel: ViewModel() {
    lateinit var listMedic: List<Medicines>

    fun setList(list: List<Medicines>){
        listMedic = list
    }
}