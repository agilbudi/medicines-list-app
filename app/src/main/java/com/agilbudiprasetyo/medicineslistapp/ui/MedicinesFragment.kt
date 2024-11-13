package com.agilbudiprasetyo.medicineslistapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilbudiprasetyo.medicineslistapp.DetailActivity
import com.agilbudiprasetyo.medicineslistapp.adapter.ListMedicinesAdepter
import com.agilbudiprasetyo.medicineslistapp.databinding.FragmentMedicinesBinding
import com.agilbudiprasetyo.medicineslistapp.model.Medicines
import com.agilbudiprasetyo.medicineslistapp.utils.dataMedicines
import com.agilbudiprasetyo.medicineslistapp.view_model.MainViewModel

class MedicinesFragment : Fragment() {
    private var _binding: FragmentMedicinesBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvMedicines: RecyclerView
    private lateinit var viewModel: MainViewModel
    private val list = ArrayList<Medicines>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedicinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val rotation = windowManager.defaultDisplay.rotation
        val typeLayoutManager = assignScreen(rotation)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        resolveData()
        rvMedicines = binding.rvMedicines
        rvMedicines.setHasFixedSize(true)

        showRecycleData(typeLayoutManager)
    }

    private fun assignScreen(rotation: Int): Boolean {
        return when(rotation){
            Surface.ROTATION_0 -> true
            Surface.ROTATION_90 -> false
            Surface.ROTATION_180 -> true
            Surface.ROTATION_270 -> false
            else -> true
        }
    }

    private fun resolveData() {
        if (list.isEmpty()) {
            viewModel.setList(dataMedicines)
            list.addAll(viewModel.listMedic)
        }
    }

    private fun showRecycleData(typeLayoutManager: Boolean) {
        rvMedicines.layoutManager = if (typeLayoutManager) LinearLayoutManager(context) else GridLayoutManager(context, 2)
        val listMedicinesAdapter = ListMedicinesAdepter(list)
        rvMedicines.adapter = listMedicinesAdapter
        listMedicinesAdapter.setOnItemClickCallback(object : ListMedicinesAdepter.OnItemClickCallback{
            override fun onItemClicked(data: Medicines) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_MEDICINES, data)
                startActivity(intent)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val EXTRA_MEDICINES = "extra_medicines"
    }
}
