package com.websarva.wings.android.menusample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView

/**
 * MenuThanksActivity class
 * Order Completed!! Screen
 */
class MenuThanksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)
        showOrderResult()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    /**
     * Back Button to List
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Finish activity if selected menu is 'Back'.
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    /**
     * Show Order Result
     */
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
    /*
    fun onBackButtonClick(view: View)  {
        finish()
    }
     */
}
