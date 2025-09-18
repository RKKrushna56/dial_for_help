package com.example.gaikar

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupText: TextView
    private lateinit var resetPasswordText: TextView

    private val SUPABASE_URL = "https://bdbwfpedaanklfldclrm.supabase.co"
    private val SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJkYndmcGVkYWFua2xmbGRjbHJtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQzODg4MzQsImV4cCI6MjA2OTk2NDgzNH0.jMrsPIc62VLYVewBPWIGhPROub4kSy7ui-boUKUAX1w"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check login state
        val sharedPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        if (sharedPref.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.button)
        signupText = findViewById(R.id.signupRedirect)
        resetPasswordText = findViewById(R.id.forgot_pass)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginWithSupabase(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        signupText.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        resetPasswordText.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }

    private fun loginWithSupabase(email: String, password: String) {
        Thread {
            try {
                val url = URL("$SUPABASE_URL/auth/v1/token?grant_type=password")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("apikey", SUPABASE_API_KEY)
                connection.setRequestProperty("Accept", "application/json")
                connection.doOutput = true

                val jsonBody = JSONObject().apply {
                    put("email", email)
                    put("password", password)
                }

                OutputStreamWriter(connection.outputStream).use { it.write(jsonBody.toString()) }

                val responseCode = connection.responseCode
                val response = BufferedReader(
                    InputStreamReader(
                        if (responseCode in 200..299) connection.inputStream else connection.errorStream
                    )
                ).use { it.readText() }

                val jsonResponse = JSONObject(response)

                runOnUiThread {
                    if (responseCode == 200) {
                        val userId = jsonResponse.getJSONObject("user").getString("id")
                        val sharedPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
                        with(sharedPref.edit()) {
                            putBoolean("isLoggedIn", true)
                            putString("userId", userId)
                            apply()
                        }

                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, CreateProfile::class.java))
                        finish()
                    } else {
                        val error = jsonResponse.optString("error_description", "Login failed")
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Exception: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }
}
