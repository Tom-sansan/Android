package com.websarva.wings.android.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    private val name: String = "name"
    private val price: String = "price"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get ListView
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // Prepare MutableList object for SimpleAdapter
        // var menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        // Get Menu List
        val menuList = createMenuList()
        val from = arrayOf(name, price)
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        // Create Simple Adapter
        val adapter = SimpleAdapter(applicationContext, menuList, android.R.layout.simple_expandable_list_item_2, from, to)
        // Set Adapter
        lvMenu.adapter = adapter

        // Listener class for List Tap
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // Get data from tapped row. MutableMap type in the row in SimpleAdapter
            val item = parent?.getItemAtPosition(position) as MutableMap<String, String>
            // Get menu name and price
            val menuName = item[name]
            val menuPrice = item[price]
            // Create Intent object
            val intent = Intent(applicationContext, MenuThanksActivity::class.java)
            // Prepare data to send to the 2nd screen
            intent.putExtra("menuName", menuName)
            intent.putExtra("menuPrice", menuPrice)
            // Initiate the 2nd screen
            startActivity(intent)
        }
    }

    /**
     * Create Menu List
     */
    private fun createMenuList() : MutableList<MutableMap<String, String>> {
        val menuList = mutableListOf<MutableMap<String, String>>()
        menuList.add(mutableMapOf(name to "Fried Chicken Set Meal", price to "$8.00"))
        menuList.add(mutableMapOf(name to "Salisbury Steak Platter", price to "$8.50"))
        menuList.add(mutableMapOf(name to "Ginger Fried Pork Set", price to "$8.50"))
        menuList.add(mutableMapOf(name to "Steak Platter", price to "$10.00"))
        menuList.add(mutableMapOf(name to "Vegetable Stir Fry Meal", price to "$7.50"))
        menuList.add(mutableMapOf(name to "Pork Katsu Meal", price to "$9.00"))
        menuList.add(mutableMapOf(name to "Fried Minced Meat Meal", price to "$8.50"))
        menuList.add(mutableMapOf(name to "Chicken Katsu Meal", price to "$9.00"))
        menuList.add(mutableMapOf(name to "Croquette Meal", price to "$8.50"))
        menuList.add(mutableMapOf(name to "Grilled Fish Meal", price to "$8.50"))
        menuList.add(mutableMapOf(name to "BBQ Meal", price to "$9.50"))
        return menuList
    }
}
