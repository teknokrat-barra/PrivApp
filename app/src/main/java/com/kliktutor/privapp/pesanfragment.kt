package com.kliktutor.privapp

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class pesanfragment : Fragment() {

    private lateinit var layoutPesan: LinearLayout
    private lateinit var sharedPreferences: android.content.SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pesan, container, false)
        layoutPesan = view.findViewById(R.id.layout_pesan)

        sharedPreferences = requireContext().getSharedPreferences("jadwal_pref", Context.MODE_PRIVATE)

        tampilkanPesanDariJadwal()

        return view
    }

    private fun tampilkanPesanDariJadwal() {
        val data = sharedPreferences.getStringSet("jadwal_list", emptySet())
        val tutorSet = mutableSetOf<String>()

        if (!data.isNullOrEmpty()) {
            for (item in data) {
                // Contoh item: "Matematika (Pak Andi) - Rabu, 13.00"
                val regex = Regex("\\((.*?)\\)")
                val matchResult = regex.find(item)
                val tutor = matchResult?.groupValues?.get(1)

                if (tutor != null && tutor !in tutorSet) {
                    tutorSet.add(tutor)
                    tampilkanChatBox(tutor)
                }
            }
        } else {
            tampilkanPesanKosong()
        }
    }

    private fun tampilkanChatBox(namaTutor: String) {
        val tv = TextView(requireContext())
        tv.text = "Chat dengan $namaTutor"
        tv.setPadding(16, 16, 16, 16)
        tv.setBackgroundResource(android.R.color.darker_gray)
        tv.setTextColor(resources.getColor(android.R.color.white, null))
        tv.textSize = 16f

        layoutPesan.addView(tv)
    }

    private fun tampilkanPesanKosong() {
        val tv = TextView(requireContext())
        tv.text = "Belum ada pesan. Silakan tambahkan jadwal terlebih dahulu."
        tv.setPadding(16, 16, 16, 16)
        tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
        layoutPesan.addView(tv)
    }
}