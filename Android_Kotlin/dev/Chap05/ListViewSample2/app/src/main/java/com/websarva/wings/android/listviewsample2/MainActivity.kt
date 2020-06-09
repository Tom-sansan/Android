package com.websarva.wings.android.listviewsample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get ListView object
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // Create list data to show on ListView
        var menuList = mutableListOf(
                "Fried Chicken Set Meal"
                ,"Salisbury Steak Platter"
                ,"Ginger Fried Pork Set"
                ,"Steak Platter"
                ,"Vegetable Stir Fry Meal"
                ,"Pork Katsu Meal"
                ,"Fried Minced Meat Meal"
                ,"Chicken Katsu Meal"
                ,"Croquette Meal"
                ,"Grilled Fish Meal"
                ,"BBQ Meal"
        )
        // Create Adapter object
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, menuList)
        // Set Adapter object to ListView
        lvMenu.adapter = adapter
        // Set Listener to ListView
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // Create Order Confirmation Dialog Fragment object
            val dialogFragment = OrderConfirmDialogFragment()
            // Show Dialog
            dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
        }
    }
}
