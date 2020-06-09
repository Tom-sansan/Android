package com.websarva.wings.android.servicesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get transferred data from Intent
        val fromNotification = intent.getBooleanExtra("fromNotification", false)
        // if there is transferred data...
        if(fromNotification) {
            // Disable Play button, Enable Stop button
            setPlayStopButtons(false)
        }
    }

    /**
     * Play Button Function
     */
    fun onPlayButtonClick(view: View) {
        // Create Intent object
        val intent = Intent(applicationContext, SoundManageService::class.java)
        // Launch Service
        startService(intent)
        // Disable Play button, Enable Stop button
        setPlayStopButtons(false)
        /**
        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)
        btPlay.isEnabled = false
        btStop.isEnabled = true
        */
    }

    /**
     * Stop Button Function
     */
    fun onStopButtonClick(view: View) {
        // Create Intent object
        val intent = Intent(applicationContext, SoundManageService::class.java)
        // Launch Service
        stopService(intent)
        // Enable Play button, Disable Stop button
        setPlayStopButtons(true)
        /**
        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)
        btPlay.isEnabled = true
        btStop.isEnabled = false
        */
    }

    /**
     * Set Play and Stop button features
     * @param enabled {@code true} if Play button is Enabled and Stop button is Disabled,
     * {@code false} otherwise.
     */
    private fun setPlayStopButtons(enabled: Boolean) {
        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)
        btPlay.isEnabled = enabled
        btStop.isEnabled = !enabled
    }
}
