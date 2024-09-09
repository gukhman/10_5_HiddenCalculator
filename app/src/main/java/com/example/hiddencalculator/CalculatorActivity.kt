package com.example.hiddencalculator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class CalculatorActivity : AppCompatActivity() {

    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMult: Button
    private lateinit var buttonDiv: Button
    private lateinit var buttonSendResult: Button

    private lateinit var editTextNumber1: EditText
    private lateinit var editTextNumber2: EditText

    private var result: Double = 0.0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        editTextNumber1 = findViewById(R.id.editTextNumber1)
        editTextNumber2 = findViewById(R.id.editTextNumber2)

        buttonSendResult = findViewById(R.id.buttonSendResult)

        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSub = findViewById(R.id.buttonSub)
        buttonMult = findViewById(R.id.buttonMult)
        buttonDiv = findViewById(R.id.buttonDiv)

        buttonAdd.setOnClickListener {
            performOperation(Operation.ADD)
        }
        buttonSub.setOnClickListener {
            performOperation(Operation.SUB)
        }
        buttonMult.setOnClickListener {
            performOperation(Operation.MULT)
        }
        buttonDiv.setOnClickListener {
            performOperation(Operation.DIV)
        }

        buttonSendResult.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("result", result.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun performOperation(operation: Operation) {
        val num1 = editTextNumber1.text.toString().trim().toDoubleOrNull()
        val num2 = editTextNumber2.text.toString().trim().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Пожалуйста, введите оба числа", Toast.LENGTH_SHORT).show()
            return
        }

        result = when (operation) {
            Operation.ADD -> num1 + num2
            Operation.SUB -> num1 - num2
            Operation.MULT -> num1 * num2
            Operation.DIV -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Деление на ноль невозможно", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
        }

        Toast.makeText(this, "Результат: $result", Toast.LENGTH_SHORT).show()
    }

    private enum class Operation {
        ADD,
        SUB,
        MULT,
        DIV
    }
}