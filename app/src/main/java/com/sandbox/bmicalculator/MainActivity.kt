package com.sandbox.bmicalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightTextView = findViewById<EditText>(R.id.weightEditText)
        val heightTextView = findViewById<EditText>(R.id.heightEditText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val weight = weightTextView.text.toString()
            val height = heightTextView.text.toString()

            if (validateInput(weight, height)) {
                val bmiValue =
                    weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val expectedBMIValue = String.format("%.2f", bmiValue).toFloat()

                displayResult(expectedBMIValue)
            }
        }
    }

    private fun validateInput(weight: String?, height: String?): Boolean {

        return when {

            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Invalid Weight",Toast.LENGTH_LONG).show()
                return  false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Invalid Heigh",Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return  true
            }
        }
    }

    private fun displayResult(bmiValue: Float) {
        val resultCardView = findViewById<CardView>(R.id.resultCardView)
        val indexTextView = findViewById<TextView>(R.id.indexTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val informationTextView = findViewById<TextView>(R.id.informationTextView)

        indexTextView.text = bmiValue.toString()
        informationTextView.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when {
            bmiValue < 18.50 -> {
                resultText = "Underweight"
                color = R.color.underwight
            }

            bmiValue in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }

            bmiValue in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.overweight
            }

            bmiValue > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }

        resultCardView.isVisible = true
        descriptionTextView.setTextColor(ContextCompat.getColor(this,color))
        descriptionTextView.text = resultText
    }
}