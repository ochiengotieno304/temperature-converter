package com.example.temperatureconverter

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var editText: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromSpinner: Spinner = findViewById(R.id.from_spinner)
        val toSpinner: Spinner = findViewById(R.id.to_spinner)
        val button: Button = findViewById(R.id.convert_button)
        editText = findViewById(R.id.temp_edit_text)
        resultTextView = findViewById(R.id.result_text)
        var fromUnit: String
        var toUnit: String
        var result: Float

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.temperature_unit,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            fromSpinner.adapter = adapter
            fromSpinner.onItemSelectedListener = this

            toSpinner.adapter = adapter
            toSpinner.onItemSelectedListener = this
        }

        button.setOnClickListener {
            fromUnit = setSpinnerItem(fromSpinner)
            toUnit = setSpinnerItem(toSpinner)
            if (fromUnit == "Celsius" && toUnit == "Fahrenheit") {
                result = (getTemperature() * 9 / 5) + 32
                setTemperature(result)
            } else if (fromUnit == "Fahrenheit" && toUnit == "Celsius") {
                result = (getTemperature() - 32) * 5 / 9
                setTemperature(result)
            } else Toast.makeText(this, "$fromUnit $toUnit", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTemperature(result: Float) {
        resultTextView.text = result.toString()
    }

    private fun getTemperature(): Float {
        return editText.text.toString().toFloat()
    }

    private fun setSpinnerItem(spinner: Spinner): String {
        return spinner.selectedItem.toString()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
