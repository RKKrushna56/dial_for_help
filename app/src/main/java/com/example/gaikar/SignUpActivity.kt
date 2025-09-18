package com.example.gaikar

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signupButton: Button
    private lateinit var loginRedirect: TextView

    private val SUPABASE_URL = "https://bdbwfpedaanklfldclrm.supabase.co"
    private val SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJkYndmcGVkYWFua2xmbGRjbHJtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQzODg4MzQsImV4cCI6MjA2OTk2NDgzNH0.jMrsPIc62VLYVewBPWIGhPROub4kSy7ui-boUKUAX1w"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        nameEditText = findViewById(R.id.name)
        phoneEditText = findViewById(R.id.phone)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirm_password)
        signupButton = findViewById(R.id.signupButton)
        loginRedirect = findViewById(R.id.loginRedirect)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        signupButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                val result = signUpUser(email, password)

                if (result) {
                    Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Signup failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginRedirect.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun signUpUser(email: String, password: String): Boolean {
        try {
            val url = URL("$SUPABASE_URL/auth/v1/signup")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("apikey", SUPABASE_API_KEY)
            connection.setRequestProperty("Authorization", "Bearer $SUPABASE_API_KEY")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            val requestBody = JSONObject()
            requestBody.put("email", email)
            requestBody.put("password", password)

            val outputStreamWriter = OutputStreamWriter(connection.outputStream)
            outputStreamWriter.write(requestBody.toString())
            outputStreamWriter.flush()
            outputStreamWriter.close()

            val responseCode = connection.responseCode

            if (responseCode == 200 || responseCode == 201) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readText()
                reader.close()
                println("Signup response: $response")
                return true
            } else {
                val errorReader = BufferedReader(InputStreamReader(connection.errorStream))
                val error = errorReader.readText()
                errorReader.close()
                println("Signup failed: $error")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}
