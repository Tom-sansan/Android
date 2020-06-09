package com.websarva.wings.android.intentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)
        showOrderResult()
    }

    private fun showOrderResult() {
        // Get data from List screen
        val menuName = intent.getStringExtra("menuName")
        val menuPrice = intent.getStringExtra("menuPrice")
        // Get TextView to show meal name and the price
        val tvMenuName = findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = findViewById<TextView>(R.id.tvMenuPrice)
        // Show meal name and price in TextView
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice
    }

    // Back Button to List
    fun onBackButtonClick(view: View)  {
        finish()
    }
}
