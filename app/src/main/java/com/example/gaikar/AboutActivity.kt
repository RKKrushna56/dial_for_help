package com.example.gaikar

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    private lateinit var btnClose: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity) // Use the XML file name

        // Initialize views
        btnClose = findViewById(R.id.btnClose)

        // Handle close button click
        btnClose.setOnClickListener {
            finish() // Close this activity and go back
        }
    }
}
