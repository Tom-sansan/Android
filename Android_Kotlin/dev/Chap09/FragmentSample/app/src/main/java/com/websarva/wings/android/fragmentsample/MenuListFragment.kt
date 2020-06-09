package com.websarva.wings.android.fragmentsample

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

/**
 * A simple [Fragment] subclass.
 * Related to MainActivity.
 */
class MenuListFragment : Fragment() {

    private val name: String = "name"
    private val price: String = "price"
    private val desc: String = "desc"

    // true: Large scree, false: normal screen
    private var _isLayoutXLarge = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate Fragment view from XML file
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        // Get ListView
        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)
        // Prepare MutableList object for SimpleAdapter
        val menuList = createMenuList()
        // from parameter for SimpleAdapter
        val from = arrayOf(name, price)
        // to parameter for SimpleAdapter
        val to = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)
        // Create SimpleAdapter
        val adapter = SimpleAdapter(activity, menuList, R.layout.row, from, to)
        // Register adapter
        lvMenu.adapter = adapter
        // Register Listener
        lvMenu.onItemClickListener = ListItemClickListener()
        // Return Inflated screen
        return view
    }
    /**
     * Set Screen Size
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Get menuThanksFrame from this activity
        val menuThanksFrame = activity?.findViewById<View>(R.id.menuThanksFrame)
        // menuThanksFrame is null, then normal screen
        if(menuThanksFrame == null) {
            _isLayoutXLarge = false
        }
    }
    /**
     * Listener class for List Item Click
     */
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // Get data from tapped row. MutableMap type in the row in SimpleAdapter
            val item = parent?.getItemAtPosition(position) as MutableMap<String, Any>
            // Get menu name and price
            val menuName = item[name] as String
            val menuPrice = item[price] as Double

            // Create Bundle object
            val bundle = Bundle()
            // Store inherit data to Bundle object
            bundle.putString("menuName", menuName)
            bundle.putString("menuPrice", "$${menuPrice}")

            // Large Screen
            if(_isLayoutXLarge) {
                // Initiate fragment transaction
                val transaction = fragmentManager?.beginTransaction()
                // Create MenuThanksFragment
                val menuThanksFragment = MenuThanksFragment()
                // Store inherit data to menuThanksFragment
                menuThanksFragment.arguments = bundle
                // Add(Replace) menuThanksFragment to menuThanksFrame
                transaction?.replace(R.id.menuThanksFrame, menuThanksFragment)
                // Commit transaction
                transaction?.commit()
            }
            // Normal Screen
            else {
                // Create Intent object
                val intent = Intent(activity, MenuThanksActivity::class.java)
                // Prepare data to send to the 2nd screen
                intent.putExtras(bundle)
                // Initiate the 2nd screen
                startActivity(intent)
            }

            // Create Intent object
            // val intent = Intent(activity, MenuThanksActivity::class.java)
            // Prepare data to send to the 2nd screen
            // intent.putExtra("menuName", menuName)
            // intent.putExtra("menuPrice", "$${menuPrice}")
            // Initiate the 2nd screen
            // startActivity(intent)
        }
    }
    /**
     * Create Menu List
     */
    private fun createMenuList() : MutableList<MutableMap<String, Any>> {
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
}
