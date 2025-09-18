package com.example.gaikar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private var ivNotifications: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the XML you pasted
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivNotifications = view.findViewById(R.id.ivNotifications)

        ivNotifications?.setOnClickListener {
            // Open NotificationActivity
            val intent = Intent(requireContext(), notification::class.java)
            startActivity(intent)
        }
    }
}
