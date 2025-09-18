package com.example.gaikar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class ContactsFragment : Fragment() {

    private var btnAbout: ImageButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate your services XML
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAbout = view.findViewById(R.id.btn_about)

        btnAbout?.setOnClickListener {
            // Open AboutUsActivity when tapped
            val intent = Intent(requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }
    }
}
