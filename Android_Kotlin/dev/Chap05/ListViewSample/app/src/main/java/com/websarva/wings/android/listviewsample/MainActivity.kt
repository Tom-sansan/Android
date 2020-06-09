package com.websarva.wings.android.listviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get ListView object
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // Set Listener to ListView
        lvMenu.onItemClickListener = ListItemClickListener()
        // lvMenu.setOnItemClickListener(ListItemClickListener())
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // Get Tapped meal
            val item = parent?.getItemAtPosition(position) as String
            // val item = (view as TextView).text.toString()
            // Create message
            val show = "Your selected meal: " + item
            // Show Toast
            Toast.makeText(applicationContext, show, Toast.LENGTH_LONG).show()
        }

    }


}
