package com.kliktutor.privapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class jadwalfragment : Fragment() {

    private lateinit var layoutKosong: LinearLayout
    private lateinit var layoutJadwalContainer: ScrollView
    private lateinit var layoutJadwal: LinearLayout
    private lateinit var sharedPreferences: android.content.SharedPreferences

    private val mapelTutorMap = mapOf(
        "Bahasa Indonesia" to "Bu Sari",
        "Matematika" to "Pak Andi",
        "Fisika" to "Pak Budi",
        "Bahasa Inggris" to "Miss Lina"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jadwal, container, false)

        layoutKosong = view.findViewById(R.id.layout_kosong)
        layoutJadwalContainer = view.findViewById(R.id.layout_jadwal_container)
        layoutJadwal = view.findViewById(R.id.layout_jadwal)
        val btnTambahJadwal = view.findViewById<Button>(R.id.btn_tambah_jadwal)

        sharedPreferences = requireContext().getSharedPreferences("jadwal_pref", Context.MODE_PRIVATE)

        loadJadwal()

        btnTambahJadwal.setOnClickListener {
            showTambahDialog()
        }

        return view
    }

    private fun showTambahDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Tambah Jadwal")

        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40, 50, 10)

        val spinnerMapel = Spinner(requireContext())
        val mapelList = mapelTutorMap.keys.toTypedArray()
        spinnerMapel.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, mapelList)
        layout.addView(spinnerMapel)

        val spinnerHari = Spinner(requireContext())
        val hariList = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu")
        spinnerHari.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, hariList)
        layout.addView(spinnerHari)

        val spinnerJam = Spinner(requireContext())
        val jamList = arrayOf("11.00 - 13.00", "13.00 - 15.00", "15.00 - 17.00")
        spinnerJam.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, jamList)
        layout.addView(spinnerJam)

        builder.setView(layout)

        builder.setPositiveButton("Simpan") { _, _ ->
            val mapel = spinnerMapel.selectedItem.toString()
            val tutor = mapelTutorMap[mapel] ?: "-"
            val hari = spinnerHari.selectedItem.toString()
            val jam = spinnerJam.selectedItem.toString()

            val jadwal = "$mapel ($tutor) - $hari, $jam"
            saveJadwal(jadwal)
            tampilkanJadwal(jadwal)
        }

        builder.setNegativeButton("Batal", null)
        builder.show()
    }

    private fun tampilkanJadwal(text: String) {
        val textView = TextView(requireContext())
        textView.text = text
        textView.setPadding(16, 16, 16, 16)
        textView.setBackgroundResource(android.R.color.darker_gray)
        textView.setTextColor(resources.getColor(android.R.color.white, null))
        textView.textSize = 16f

        layoutJadwal.addView(textView)

        layoutKosong.visibility = View.GONE
        layoutJadwalContainer.visibility = View.VISIBLE
    }

    private fun saveJadwal(newItem: String) {
        val existing = sharedPreferences.getStringSet("jadwal_list", mutableSetOf())!!.toMutableSet()
        existing.add(newItem)
        sharedPreferences.edit().putStringSet("jadwal_list", existing).apply()
    }

    private fun loadJadwal() {
        val data = sharedPreferences.getStringSet("jadwal_list", emptySet())
        if (data.isNullOrEmpty()) {
            layoutKosong.visibility = View.VISIBLE
            layoutJadwalContainer.visibility = View.GONE
        } else {
            layoutKosong.visibility = View.GONE
            layoutJadwalContainer.visibility = View.VISIBLE
            for (item in data) {
                tampilkanJadwal(item)
            }
        }
    }
}
