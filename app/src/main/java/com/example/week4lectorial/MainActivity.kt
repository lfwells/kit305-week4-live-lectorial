package com.example.week4lectorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.week4lectorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var ui : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        var settings = getSharedPreferences("MY_PREFS", MODE_PRIVATE)
        var name = settings.getString("name", "No name")
        Log.d("MainActivity", "the saved name was $name")


        val names = resources.getStringArray(R.array.names)
        ui.spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            names
        )

        ui.spinner.setSelection(names.indexOf(name))

        ui.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                ui.txtName.text = names[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?){
                Log.d("MainActivity", "Nothing selected")
            }
        }

        ui.btnSave.setOnClickListener {
            with (settings.edit())
            {
                putString("name", names[ui.spinner.selectedItemPosition])
                apply()
                Log.d("MainActivity", "saved "+names[ui.spinner.selectedItemPosition])
            }
        }
    }
}