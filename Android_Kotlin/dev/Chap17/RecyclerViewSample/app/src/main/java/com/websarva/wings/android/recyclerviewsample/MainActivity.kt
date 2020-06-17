package com.websarva.wings.android.recyclerviewsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.*
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {

    private val name: String = "name"
    private val price: String = "price"
    private val desc: String = "desc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createToolbarForCollapsingToolbarLayout()
    }

    /**
     * Create Toolbar for CollapsingToolbarLayout
     */
    private fun createToolbarForCollapsingToolbarLayout() {
        // Get Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // Set logo to toolbar
        toolbar.setLogo(R.mipmap.ic_launcher)
        // Set toolbar to Action bar
        setSupportActionBar(toolbar)
        // Get CollapsingToolbarLayout
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        // Set title
        toolbarLayout.title = getString(R.string.toolbar_title)
        // Set text color for normal size
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        // Set text color for collapsed size
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        // Get RecyclerView
        val lvMenu = findViewById<RecyclerView>(R.id.lvMenu)
        // Create LinearLayoutManager object
        val layout = LinearLayoutManager(applicationContext)
        // val layout = GridLayoutManager(applicationContext, 5)
        // val layout = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
        // Set LinearLayoutManager to RecyclerView
        lvMenu.layoutManager = layout
        // Create menu list
        val menuList = createMenuList()
        // Create Adapter object
        val adapter = RecyclerListAdapter(menuList)
        // Set Adapter object to RecylerView
        lvMenu.adapter = adapter

        // Create section line
        val decorator = DividerItemDecoration(applicationContext, layout.orientation)
        // Set section line object to RecyclerView
        lvMenu.addItemDecoration(decorator)
    }

    /**
     * ItemClickListener class in row.xml
     */
    private inner class ItemClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            // Get TextView of menu name
            val tvMenuName = view?.findViewById<TextView>(R.id.tvMenuName)
            // Get menu name string
            val menuName = tvMenuName?.text.toString()
            // Create string on toast
            val msg = getString(R.string.msg_header) + menuName
            // Show toast
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
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

    /**
     * ViewHolder Class to store properties for row.xml
     * @param itemView : parts that LinearLayout is root tag in row.xml
     */
    private inner class RecyclerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Menu name in a row
        var tvMenuName: TextView
        // Menu price in a row
        var tvMenuPrice: TextView

        init {
            // Get TextView for display
            tvMenuName = itemView.findViewById(R.id.tvMenuName)
            tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice)
        }
    }

    /**
     * RecyclerListAdapter class
     */
    private inner class RecyclerListAdapter(private val _listData: MutableList<MutableMap<String, Any>>): RecyclerView.Adapter<RecyclerListViewHolder>() {
        /**
         * Create ViewHolder object
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            // Get LayoutInflater
            val inflater = LayoutInflater.from(applicationContext)
            // Create row.xml inflate
            val view = inflater.inflate(R.layout.row, parent, false)
            // Set Listener to a row in view
            view.setOnClickListener(ItemClickListener())
            // Create ViewHolder object
            val holder = RecyclerListViewHolder(view)
            // Return ViewHolder
            return holder
        }

        /**
         * Bind _listData to ViewHolder object
         */
        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            // Get one row data from ListData
            val item = _listData[position]
            // Get menu name
            val menuName = item[name] as String
            // Get menu price
            val menuPrice = item[price] as Double
            // Convert price to string
            val menuPriceStr = menuPrice.toString()
            // Set menu name and price TextView in ViewHolder
            holder.tvMenuName.text = menuName
            holder.tvMenuPrice.text = menuPriceStr
        }

        override fun getItemCount(): Int {
            // Return total number of list data
            return _listData.size
        }
    }
}
