package com.websarva.wings.android.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Related to MenuThanksActivity.
 */
class MenuThanksFragment : Fragment() {

    // true: Large scree, false: normal screen
    private var _isLayoutXLarge = true

    /**
     * Set Screen Size
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get Menu List Fragment from Fragment Manager
        val menuListFragment = fragmentManager?.findFragmentById(R.id.fragmentMenuList)
        // menuListFragment is null then normal screen
        if(menuListFragment == null) {
            _isLayoutXLarge = false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate fragment view from xml file
        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)

        // Prepare Bundle object
        val extras: Bundle?
        // Large screen
        if(_isLayoutXLarge) {
            // Get inherit data from arguments
            extras = arguments
        }
        // Normal screen
        else {
            // Get intent from activity
            val intent = activity?.intent
            // Get data from intent
            extras = intent?.extras
        }

        // Get intent from activity
        // val intent = activity?.intent
        // Get data from intent
        // val extras = intent?.extras
        // Get meal and price
        val menuName = extras?.getString("menuName")
        val menuPrice = extras?.getString("menuPrice")
        // Get TextView to show meal and price
        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)
        // Show meal and price in TextView
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice

        // Get Back Button
        val btBackButton = view.findViewById<Button>(R.id.btBackButton)
        // Register Listener to Back Button
        btBackButton.setOnClickListener(ButtonClickListener())

        // Return inflated view
        return view
    }
    /**
     * Member class for Button click
     */
    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            // Large Screen
            if(_isLayoutXLarge) {
                // Initiate Fragment Transaction
                val transaction = fragmentManager?.beginTransaction()
                // Delete itself
                transaction?.remove(this@MenuThanksFragment)
                // Commit Fragment Transaction
                transaction?.commit()
            }
            // Normal Screen
            else {
                // Finish this activity
                activity?.finish()
            }

            // Finish this activity
            // activity?.finish()
        }
    }
}
