package com.websarva.wings.android.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val name: String = "name"
    private val price: String = "price"
    private val desc: String = "desc"

    // List data for ListView
    private var _menuList: MutableList<MutableMap<String, Any>>? = null
    // from parameter for SimpleAdapter
    private val FROM = arrayOf(name, price)
    // to parameter for SimpleAdapter
    private val TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get Menu List
        _menuList = createMenuList()
        // Get ListView
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // Create SimpleAdapter
        val adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        // Register Adapter
        lvMenu.adapter = adapter
        // Register Listener class of List Tap
        lvMenu.onItemClickListener = ListItemClickListener()
        // Register Context Menu
        registerForContextMenu(lvMenu)
    }
    /**
     * Listener class for List Item
     */
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // Get data from tapped row. MutableMap type in the row in SimpleAdapter
            val item = parent?.getItemAtPosition(position) as MutableMap<String, Any>

            /**
             * Moved the functions below to order()
             */
            // Get menu name and price
            // val menuName = item[name] as String
            // val menuPrice = item[price] as Double
            // Create Intent object
            // val intent = Intent(applicationContext, MenuThanksActivity::class.java)
            // Prepare data to send to the 2nd screen
            // intent.putExtra("menuName", menuName)
            // intent.putExtra("menuPrice", "$${menuPrice}")
            // Initiate the 2nd screen
            // startActivity(intent)

            // Order process
            order(item)
        }
    }
    /**
     * Create ordered menu and launch MenuThanksActivity
     */
    private fun order(menu: MutableMap<String, Any>) {
        // Get menu name and price
        val menuName = menu[name] as String
        val menuPrice = menu[price] as Double
        // Create Intent object
        val intent = Intent(applicationContext, MenuThanksActivity::class.java)
        // Prepare data to send to the 2nd screen
        intent.putExtra("menuName", menuName)
        intent.putExtra("menuPrice", "$${menuPrice}")
        // Launch the 2nd screen
        startActivity(intent)
    }
    /**
     * Create Option Menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate xml file for Option menu list
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        // Return boolean calling the same name method of parent class
        return super.onCreateOptionsMenu(menu)
    }
    /**
     * Create Option Menu List when item is selected
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Switch according to selected menu
        when(item?.itemId) {
            R.id.menuListOptionMeal ->
                // Create Meal Menu List
                _menuList = createMenuList()
            R.id.menuListOptionCurry ->
                // Create Curry Menu List
                _menuList = createCurryList()
        }
        // Get ListView
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // Create SimpleAdapter
        val adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        // Register Adapter
        lvMenu.adapter = adapter
        // Return boolean calling the same name method of parent class
        return super.onOptionsItemSelected(item)
    }
    /**
     * Create Context Menu
     */
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Inflate xml file for Context Menu
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        // Set Header title for Context Menu
        menu?.setHeaderTitle(R.string.menu_list_context_header)
    }
    /**
     * Context Menu Process when item is selected
     */
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        // Get View object that was held down
        val info = item?.menuInfo as AdapterView.AdapterContextMenuInfo
        // Get the position that was held down
        val listPosition = info.position
        // Get Menu info map object that was held down from the position
        val menu = _menuList!![listPosition]
        // Switch based on R value of selected menu ID
        when(item.itemId) {
            // For Show Description
            R.id.menuListContextDesc -> {
                // Get Description of menu
                val menuDesc = menu[desc] as String
                // Show Toast
                Toast.makeText(applicationContext, menuDesc, Toast.LENGTH_LONG).show()
            }
            // For Order
            R.id.menuListContextOrder ->
                // Order process
                order(menu)
        }
        return super.onContextItemSelected(item)
    }
    /**
     * Create Menu List
     */
    private fun createMenuList() : MutableList<MutableMap<String, Any>> {
        // Prepare List object
        // val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // Prepare Map object
        // var menu = mutableMapOf(name to "Fried Chicken Set Meal", price to "8.00", desc to "Karaage with salad, rice and miso soup.")
        // menuList.add(menu)
        val menuList = mutableListOf<MutableMap<String, Any>>()
        menuList.add(mutableMapOf(name to "Fried Chicken Set Meal", price to 8.00, desc to "Karaage with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Salisbury Steak Meal", price to 8.50, desc to "Salisbury Steak with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Ginger Fried Pork Meal", price to 8.50, desc to "Ginger Fried Pork with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Steak Meal", price to 10.00, desc to "Steak with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Vegetable Stir Fry Meal", price to 7.50, desc to "Vegetable Stir Fry with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Pork Katsu Meal", price to 9.00, desc to "Pork Katsu with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Fried Minced Meat Meal", price to 8.50, desc to "Fried Minced Meat with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Chicken Katsu Meal", price to 9.00, desc to "Chicken Katsu with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Croquette Meal", price to 8.50, desc to "Four Croquettes with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "Grilled Fish Meal", price to 8.50, desc to "Grilled Fish with salad, rice and miso soup."))
        menuList.add(mutableMapOf(name to "BBQ Meal", price to 9.50, desc to "BBQ with salad, rice and miso soup."))
        return menuList
    }
    /**
     * Create Curry Menu List
     */
    private fun createCurryList() : MutableList<MutableMap<String, Any>> {
        // Prepare Curry Menu List object
        // val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // Prepare Map object
        // var menu = mutableMapOf(name to "Fried Chicken Set Meal", price to "8.00", desc to "Karaage with salad, rice and miso soup.")
        // menuList.add(menu)
        val menuList = mutableListOf<MutableMap<String, Any>>()
        menuList.add(mutableMapOf(name to "Beef Curry", price to 5.20, desc to "100% domestic beef curry with special spices."))
        menuList.add(mutableMapOf(name to "Pork Katsu Curry", price to 4.20, desc to "100% domestic pork curry with special spices."))
        menuList.add(mutableMapOf(name to "Salisbury Steak Curry", price to 6.20, desc to "Topping with a Salisbury Steak on a curry with a special spice."))
        menuList.add(mutableMapOf(name to "Cheese Curry", price to 5.60, desc to "Topping with melting Cheese with special spices."))
        menuList.add(mutableMapOf(name to "Katsu Curry", price to 7.60, desc to "Topping with Domestic Roast Cutlet on curry with special spices."))
        menuList.add(mutableMapOf(name to "Beef Katsu Curry", price to 8.80, desc to "Topping with Domestic Beef Cutlet on curry with special spices."))
        menuList.add(mutableMapOf(name to "Fried Chicken Curry", price to 5.40, desc to "Topping with Young Fried Chicken with special spices."))
        return menuList
    }
}
