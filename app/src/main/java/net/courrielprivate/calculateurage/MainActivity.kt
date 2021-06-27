package net.courrielprivate.calculateurage

import android.app.DatePickerDialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


import net.courrielprivate.calculateurage.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Cette partie du code est la configuration permettant d'appeler directement les éléments
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonDatePicker.setOnClickListener { view ->
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
//                Toast.makeText(
//                    this,
//                    "Date selectionné $selectedYear",
//                    Toast.LENGTH_LONG
//                ).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
                //Return Value: It returns the number of milliseconds since January 1, 1970, 00:00:00 GTM.
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate.time /60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time /60000

                val ageInMinutes = currentDateInMinutes - selectedDateInMinutes
                binding.tvSelectedDate.setText(selectedDate.toString())
                binding.tvCalculatedMinutes.setText(ageInMinutes.toString())
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}