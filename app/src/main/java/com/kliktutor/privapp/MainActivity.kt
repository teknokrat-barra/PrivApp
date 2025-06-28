package com.kliktutor.privapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var btnBeranda: Button
    private lateinit var btnJadwal: Button
    private lateinit var btnPesan: Button
    private lateinit var btnAkun: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("PrivAppPrefs", MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)


        btnBeranda = findViewById(R.id.btn_1)
        btnJadwal = findViewById(R.id.btn_2)
        btnPesan = findViewById(R.id.btn_3)
        btnAkun = findViewById(R.id.btn_4)

        btnBeranda.setOnClickListener {
            loadFragment(berandafragment())
        }

        btnJadwal.setOnClickListener {
            loadFragment(jadwalfragment())
        }

        btnPesan.setOnClickListener {
            loadFragment(pesanfragment())
        }

        btnAkun.setOnClickListener {
            loadFragment(akunfragment())
        }

        // Tampilkan fragment awal (beranda)
        loadFragment(berandafragment())

        // Insets agar tidak tabrakan sama status bar/navigation
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}