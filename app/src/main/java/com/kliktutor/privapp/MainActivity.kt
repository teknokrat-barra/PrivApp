package com.kliktutor.privapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //        buat variabel
//        1. var (mutable)
//        2. val (imutable)
//        deklarasi variabek object
        val btn1 = findViewById<Button>(R.id.btn_1)
        val btn2 : Button = findViewById(R.id.btn_2)
        val btn3 : Button = findViewById(R.id.btn_3)
        val btn4 : Button = findViewById(R.id.btn_4)

//        buat event
//        1. event btn1
        btn1.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity,"Tombol 1",Toast.LENGTH_SHORT).show()
        })
//        2. event btn2
        btn2.setOnClickListener {
            Toast.makeText(this@MainActivity,"Tombol 2",Toast.LENGTH_SHORT).show()
        }
//        3. event btn3
        btn3.setOnClickListener {
            Toast.makeText(this@MainActivity,"Tombol 3",Toast.LENGTH_SHORT).show()
        }

//        4. event btn4
        btn4.setOnClickListener {
            Toast.makeText(this@MainActivity,"Tombol 4",Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}