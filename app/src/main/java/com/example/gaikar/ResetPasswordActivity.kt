package com.example.gaikar

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var resetButton: Button
    private lateinit var backToLoginText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword) // your XML file name

        emailEditText = findViewById(R.id.emaileditText)
        resetButton = findViewById(R.id.resetButton)
        backToLoginText = findViewById(R.id.backToLoginText) // added TextView

        resetButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Reset link would be sent to $email", Toast.LENGTH_LONG).show()
            }
        }

        backToLoginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            }
        }
}
