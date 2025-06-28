package com.kliktutor.privapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class akunfragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textNama: TextView
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        sharedPreferences = requireContext().getSharedPreferences("login_pref", Context.MODE_PRIVATE)
        textNama = view.findViewById(R.id.text_nama_akun)
        btnLogout = view.findViewById(R.id.btn_logout_akun)

        val nama = sharedPreferences.getString("nama", "Nama Pengguna")
        textNama.text = nama

        btnLogout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}