package com.example.gaikar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CreateProfile : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var etAge: EditText
    private lateinit var btnSave: Button
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cprofile)

        // Initialize views
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPhone = findViewById(R.id.et_phone)
        etAddress = findViewById(R.id.et_address)
        rgGender = findViewById(R.id.rg_gender)
        etAge = findViewById(R.id.et_age)
        btnSave = findViewById(R.id.btn_save_profile)
        btnBack = findViewById(R.id.btn_back)

        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val address = etAddress.text.toString()
            val age = etAge.text.toString()

            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                ""
            }

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || age.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save data in SharedPreferences
            val editor = sharedPref.edit()
            editor.putString("name", name)
            editor.putString("email", email)
            editor.putString("phone", phone)
            editor.putString("address", address)
            editor.putString("gender", gender)
            editor.putString("age", age)
            editor.apply()

            Toast.makeText(this, "Profile Saved!", Toast.LENGTH_SHORT).show()

            // ✅ Open MainActivity after saving profile
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
