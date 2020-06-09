package com.websarva.wings.android.hellosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get Button object
        val btClick = findViewById<Button>(R.id.btClick)
        // Create Listener class instance
        val listener = HelloListener()
        // Set Listener to View Button
        btClick.setOnClickListener(listener)

        // Get Clear Button object
        val btClear = findViewById<Button>(R.id.btClear)
        // Set Listener to Clear Button
        btClear.setOnClickListener(listener)
    }

    // Listener Class for Button
    private inner class HelloListener : View.OnClickListener {
        override fun onClick(view: View) {
            // Get EditText object
            val input = findViewById<EditText>(R.id.etName)
            // Get TextView object
            val output = findViewById<TextView>(R.id.tvOutput)

            when (view.id) {
                // View Button
                R.id.btClick -> {
                    // Get name string
                    val inputStr = input.text.toString()
                    // Show message
                    output.text = inputStr + ", Hello!"
                }
                // Clear Button
                R.id.btClear -> {
                    // Clear name box
                    input.setText("")
                    // Clear view
                    output.text = ""
                }
            }
        }
    }
}


