package com.example.simplecalculator


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var operator = ""
    private var operand1: Double? = null
    private var operand2: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        tvResult = findViewById(R.id.tvResult)

        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonEqual: Button = findViewById(R.id.buttonEqual)

        // Number Buttons
        val buttonsNumbers = listOf<Button>(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        // Operation Buttons
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonPlus: Button = findViewById(R.id.buttonPlus)

        // Set listeners for number buttons
        for (button in buttonsNumbers) {
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }

        // Set listeners for operation buttons
        buttonDivide.setOnClickListener { setOperator("/") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonMinus.setOnClickListener { setOperator("-") }
        buttonPlus.setOnClickListener { setOperator("+") }

        // Clear button
        buttonClear.setOnClickListener {
            clearAll()
        }

        // Equal button
        buttonEqual.setOnClickListener {
            calculateResult()
        }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        tvResult.text = currentInput
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput.toDoubleOrNull()
            operator = op
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            operand2 = currentInput.toDoubleOrNull()

            val result = when (operator) {
                "+" -> (operand1 ?: 0.0) + (operand2 ?: 0.0)
                "-" -> (operand1 ?: 0.0) - (operand2 ?: 0.0)
                "*" -> (operand1 ?: 0.0) * (operand2 ?: 0.0)
                "/" -> {
                    if (operand2 == 0.0) {
                        "Error"
                    } else {
                        (operand1 ?: 0.0) / (operand2 ?: 0.0)
                    }
                }
                else -> "Error"
            }

            tvResult.text = result.toString()
            currentInput = result.toString()
            operator = ""
            operand1 = null
            operand2 = null
        }
    }

    private fun clearAll() {
        currentInput = ""
        operator = ""
        operand1 = null
        operand2 = null
        tvResult.text = "0"
    }
}
