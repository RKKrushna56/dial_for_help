package com.example.gaikar

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class EditProfile : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var profileImage: ImageView
    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var etGender: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profilr)

        // Initialize views
        btnBack = findViewById(R.id.btn_back_edit)
        profileImage = findViewById(R.id.profile_image_edit)
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPhone = findViewById(R.id.et_phone)
        etAddress = findViewById(R.id.et_address)
        etGender = findViewById(R.id.et_gender)
        etAge = findViewById(R.id.et_age)
        btnSave = findViewById(R.id.btn_save_profile)
        btnCancel = findViewById(R.id.btn_cancel_profile)

        // Back button listener
        btnBack.setOnClickListener {
            finish()
        }

        // Load existing profile data (this is a placeholder, replace with your logic)
        loadProfileData()

        // Save button listener
        btnSave.setOnClickListener {
            saveProfileData()
        }

        // Cancel button listener
        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun loadProfileData() {
        // Placeholder: Load data from database or API
        etName.setText("John Doe")
        etEmail.setText("johndoe@example.com")
        etPhone.setText("1234567890")
        etAddress.setText("123 Main Street")
        etGender.setText("Male")
        etAge.setText("25")

        // Optionally load profile image with Glide or Picasso
        // Glide.with(this).load(profileUrl).into(profileImage)
    }

    private fun saveProfileData() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val address = etAddress.text.toString().trim()
        val gender = etGender.text.toString().trim()
        val age = etAge.text.toString().trim()

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show()
            return
        }

        // Placeholder: Save data to database or API
        // Example: updateProfile(name, email, phone, address, gender, age)
        Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
