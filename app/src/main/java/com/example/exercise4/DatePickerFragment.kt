package com.example.exercise4

import android.app.*
import android.os.Bundle
import android.widget.TextView
import android.widget.DatePicker
import android.widget.Toast
import java.text.DateFormat
import java.util.Calendar


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var calendar:Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a calendar instance
        calendar = Calendar.getInstance()

        // Get the system current date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        /*
            **** reference source developer.android.com ***

            DatePickerDialog(Context context)
                Creates a new date picker dialog for the current date using the
                parent context's default date picker dialog theme.

            DatePickerDialog(Context context, int themeResId)
                Creates a new date picker dialog for the current date.

            DatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener,
            int year, int month, int dayOfMonth)
                Creates a new date picker dialog for the specified date using the parent
                context's default date picker dialog theme.

            DatePickerDialog(Context context, int themeResId, DatePickerDialog.OnDateSetListener
            listener, int year, int monthOfYear, int dayOfMonth)
                Creates a new date picker dialog for the specified date.
        */

        // Initialize a new date picker dialog and return it
        return DatePickerDialog(
            activity, // Context
            // Put 0 to system default theme or remove this parameter
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
            this, // DatePickerDialog.OnDateSetListener
            year, // Year
            month, // Month of year
            day // Day of month
        )
    }


    // When date set and press ok button in date picker dialog
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Display the selected date in text view
        val age:Int = 2020 - year
        activity.findViewById<TextView>(R.id.text_view).text = "Birthday Date: "+formatDate(year,month,day)
        val minBasicSaving:Int = when(age){
            in 16..20 -> 5000
            in 21..25 -> 14000
            in 26..30 -> 29000
            in 31..35 -> 50000
            in 36..40 -> 78000
            in 41..45 -> 116000
            in 46..50 -> 165000
            in 51..90 -> 228000
            else -> 0
        }

        activity.findViewById<TextView>(R.id.age_Text_View).text ="Age: $age years old"
        val allowableInvestAmount:Double = minBasicSaving * 0.3
        activity.findViewById<TextView>(R.id.text_view_1).text ="Minimum Basic Saving: $minBasicSaving"
        activity.findViewById<TextView>(R.id.text_view_2).text ="Allowable Investment Amount: $allowableInvestAmount"
        when {
            age>=90 -> {
                Toast.makeText(
                    activity,
                    "Select age not more than 90 years old."
                    ,Toast.LENGTH_SHORT
                ).show()
            }
            age<=15 -> {
                Toast.makeText(
                    activity,
                    "Select age more than 15 years old."
                    ,Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    // Custom method to format date
    private fun formatDate(year:Int, month:Int, day:Int):String{
        // Create a Date variable/object with user chosen date
        calendar.set(year, month, day, 0, 0, 0)
        val chosenDate = calendar.time

        // Format the date picker selected date
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate)
    }
}