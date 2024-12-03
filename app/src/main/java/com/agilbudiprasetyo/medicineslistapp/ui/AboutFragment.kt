package com.agilbudiprasetyo.medicineslistapp.ui

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.agilbudiprasetyo.medicineslistapp.R
import com.agilbudiprasetyo.medicineslistapp.databinding.FragmentAboutBinding
import com.bumptech.glide.Glide

class AboutFragment : Fragment() {
    private var _binding : FragmentAboutBinding? = null
    private val binding get() = _binding!!
    private val imageUrl = "https://firebasestorage.googleapis.com/v0/b/project-dummy-by-hide.appspot.com/o/me%2Fme.png?alt=media&token=99a68119-7753-41a8-bbf4-a5dfedcb0b68"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            tvAboutName.text = getString(R.string.agil_budi_prasetyo)
            tvAboutEmail.text = getString(R.string.agilbudiprasetyo_gmail_com)
            Glide.with(this@AboutFragment)
                .load(imageUrl)
                .into(ivAbout)

            tvAboutEmail.setOnClickListener {
                emailMe()
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun emailMe() {
        val recipientEmail = getString(R.string.agilbudiprasetyo_gmail_com)
        val subject = "Medicines App"
        val body = "Tentang Aplikasi Medicines App..."
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipientEmail?subject=$subject&body=$body")
        }

    try {
        startActivity(intent)
    }catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
        Log.e(TAG, "emailMe: $e", )
    }
//        if (intent.resolveActivity(requireActivity().packageManager) != null){
//            startActivity(intent)
//        }else{
//            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
//        }
    }
    companion object{
        val TAG = AboutFragment::class.simpleName
    }
}