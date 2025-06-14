package com.kliktutor.privapp

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.settings)

//       Buat Variabel object
        val edt_nilai1 = findViewById<EditText>(R.id.edt_nilai1)
        val edt_nilai2 = findViewById<EditText>(R.id.edt_nilai2)
        val edt_nilai3 = findViewById<EditText>(R.id.edt_hasil)
        val btn_hitung = findViewById<Button>(R.id.btn_hitung)
        val btn_reset = findViewById<Button>(R.id.btn_reset)

//        Baca parameter intent
        edt_nilai1.setText(intent.getStringExtra("nilai1"))
        edt_nilai2.setText(intent.getIntExtra("nilai2",0).toString())

//        setup awal tombol/button
        btn_hitung.isEnabled = true
        btn_reset.isEnabled = false

        //      Buat Event
        //      1. event btn_hitung
        btn_hitung.setOnClickListener {
            //           jika nilai1, nilai 2 tidak terisi
            if (edt_nilai1.text.toString().isEmpty() || edt_nilai2.text.toString().isEmpty()) {
                //              tampilkan pesan
                Toast.makeText(
                    this@Settings,
                    "Nilai 1 dan Nilai 2 Harus Diisi!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            //         Jika nilai1 dan nilai 2 terisi
            else {
                //         buat variabel untuk nilai1, nilai2
                val nilai1 = edt_nilai1.text.toString().toInt()
                val nilai2 = edt_nilai2.text.toString().toInt()
                //        buat variabel hasil
                val hasil = nilai1 + nilai2
//            tampilkan hasil
                edt_nilai3.setText(hasil.toString())
                edt_nilai3.text = Editable.Factory.getInstance().newEditable(hasil.toString())

            }

            //      2. event btn_reset
            btn_reset.setOnClickListener {
                edt_nilai1.setText("")
                edt_nilai2.setText("")
                edt_nilai3.setText("")
            }
            //           arahkan kursor ke nilai 1
            edt_nilai1.requestFocus()


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}