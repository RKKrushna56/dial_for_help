package com.example.gaikar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnHistory: ImageButton
    private lateinit var profileImage: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnEditProfile: Button
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        loadUserData()
        setupClickListeners()
    }

    /** Initialize Views */
    private fun initViews(view: View) {
        btnBack = view.findViewById(R.id.btn_back)
        btnHistory = view.findViewById(R.id.btn_history)
        profileImage = view.findViewById(R.id.profile_image)
        tvName = view.findViewById(R.id.tv_name)
        tvEmail = view.findViewById(R.id.tv_email)
        tvAddress = view.findViewById(R.id.tv_address)
        tvGender = view.findViewById(R.id.tv_gender)
        tvPhone = view.findViewById(R.id.tv_phone)
        tvAge = view.findViewById(R.id.tv_age)
        btnEditProfile = view.findViewById(R.id.btn_edit_profile)
        btnLogout = view.findViewById(R.id.btn_logout)
    }

    /** Load user data from SharedPreferences */
    private fun loadUserData() {
        val sharedPref = requireContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        tvName.text = sharedPref.getString("name", "Not set")
        tvEmail.text = sharedPref.getString("email", "Not set")
        tvPhone.text = sharedPref.getString("phone", "Not set")
        tvAddress.text = sharedPref.getString("address", "Not set")
        tvGender.text = sharedPref.getString("gender", "Not set")
        tvAge.text = sharedPref.getString("age", "Not set")
    }

    /** Setup all button clicks */
    private fun setupClickListeners() {
        btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        btnHistory.setOnClickListener { openHistoryPage() }
        btnEditProfile.setOnClickListener { openEditProfilePage() }
        btnLogout.setOnClickListener { logoutUser() }
    }

    /** Open History Activity */
    private fun openHistoryPage() {
        val intent = Intent(requireContext(), HistoryActivity::class.java)
        startActivity(intent)
    }

    /** Open Edit Profile Activity with pre-filled data */
    private fun openEditProfilePage() {
        val sharedPref = requireContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        val intent = Intent(requireContext(), CreateProfile::class.java).apply {
            putExtra("name", sharedPref.getString("name", ""))
            putExtra("email", sharedPref.getString("email", ""))
            putExtra("phone", sharedPref.getString("phone", ""))
            putExtra("address", sharedPref.getString("address", ""))
            putExtra("gender", sharedPref.getString("gender", ""))
            putExtra("age", sharedPref.getString("age", ""))
        }
        startActivity(intent)
    }

    /** Logout User (clear data + login status) */
    private fun logoutUser() {
        // Save login status = false
        val prefs = requireContext()
            .getSharedPreferences("MyAppPrefs", AppCompatActivity.MODE_PRIVATE)
        prefs.edit().putBoolean("isLoggedIn", false).apply()

        // Clear cached user profile
        requireContext()
            .getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            .edit().clear().apply()

        Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()

        // Go back to LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}
