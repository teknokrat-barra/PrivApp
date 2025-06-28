package com.kliktutor.privapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class akunfragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        val tvNama = view.findViewById<TextView>(R.id.tv_nama_akun)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)

        // Ambil nama dari SharedPreferences
        val prefs = requireContext().getSharedPreferences("PrivAppPrefs", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "Nama Pengguna")
        tvNama.text = username

        // Tombol logout
        btnLogout.setOnClickListener {
            prefs.edit().clear().apply() // Hapus semua data login
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}