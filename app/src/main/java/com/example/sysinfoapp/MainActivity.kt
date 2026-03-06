package com.example.sysinfoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Build
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textInfo)

        val info = """
        Manufacturer: ${Build.MANUFACTURER}
        Model: ${Build.MODEL}
        Brand: ${Build.BRAND}
        Device: ${Build.DEVICE}
        User: ${Build.USER}
        Base: ${Build.VERSION_CODES.BASE}
        Incremental: ${Build.VERSION.INCREMENTAL}
        SDK: ${Build.VERSION.SDK_INT}
        Version Code: ${Build.VERSION.RELEASE}
        Display: ${Build.DISPLAY}
        Hardware: ${Build.HARDWARE}
        Host: ${Build.HOST}
        ID: ${Build.ID}
        """.trimIndent()

        textView.text = info
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}