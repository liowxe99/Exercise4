package com.example.exercise4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set a click listener for button widget
        button.setOnClickListener{
            // Initialize a new DatePickerFragment
            val newFragment = DatePickerFragment()
            // Show the date picker dialog
            newFragment.show(fragmentManager, "Date Picker")
        }
    }
}