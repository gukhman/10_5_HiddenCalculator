package com.example.hiddencalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private lateinit var buttonCalculator: Button

    private val launchActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                var res = data?.getStringExtra("result")
                if (res!!.takeLast(2) == ".0") res = res.dropLast(2)
                textViewResult.text = res
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textViewResult = findViewById(R.id.textViewResult)
        buttonCalculator = findViewById(R.id.buttonCalculator)

        buttonCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            launchActivity.launch(intent)
        }
    }
}