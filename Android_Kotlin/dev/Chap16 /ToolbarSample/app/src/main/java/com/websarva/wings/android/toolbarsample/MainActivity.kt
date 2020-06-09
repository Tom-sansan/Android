package com.websarva.wings.android.toolbarsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createToolbar()
    }

    /**
     * Create Toolbar
     */
    private fun createToolbar() {
        // Get Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // Set logo to toolbar
        toolbar.setLogo(R.mipmap.ic_launcher)
        // Set title
        toolbar.setTitle(R.string.toolbar_title)
        // Set title color
        toolbar.setTitleTextColor(Color.WHITE)
        // Set subtitle
        toolbar.setSubtitle(R.string.toolbar_subtitle)
        // Set subtitle color
        toolbar.setSubtitleTextColor(Color.LTGRAY)
        // Set toolbar to Action bar
        setSupportActionBar(toolbar)
    }
}
