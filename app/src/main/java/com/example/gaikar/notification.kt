package com.example.gaikar

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class notification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Find toolbar from layout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
        val toolbarAction = findViewById<TextView>(R.id.toolbarAction)

        // Set as support action bar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false) // We use custom title

        // Handle back arrow click
        toolbar.setNavigationOnClickListener {
            finish() // Go back to previous screen (e.g. HomeFragment)
        }

        // Optional: Customize title and action text if needed
        toolbarTitle.text = "Notifications"
        toolbarAction.text = "Clear"

        // Handle "Clear" button click
        toolbarAction.setOnClickListener {
            Toast.makeText(this, "Notifications cleared", Toast.LENGTH_SHORT).show()
            // You can clear a list here if you add RecyclerView
        }
    }
}
