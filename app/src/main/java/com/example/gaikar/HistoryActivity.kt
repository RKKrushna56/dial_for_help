package com.example.gaikar

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var btnCloseHistory: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history) // <-- use your XML file name

        // Initialize views
        btnCloseHistory = findViewById(R.id.btnCloseHistory)

        // Close button functionality
        btnCloseHistory.setOnClickListener {
            finish() // closes the activity and goes back
        }
    }
}
