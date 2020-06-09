package com.websarva.wings.android.implicitintentsample

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    // Latitude field
    private var _latitude = 0.0
    // Loingitude field
    private var _longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableGPS()
    }

    /**
     * Enable GPS with user permission
     */
    private fun enableGPS() {
        // Get LocationManager object
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // Create Listener object when location is updated
        val locationListener = GPSLocationListener()
        // If ACCESS_FINE_LOCATION is not permitted...
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            // Show a dialog to ask for ACCESS_FINE_LOCATION permission
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this@MainActivity, permissions, 1000)
            return
        }
        // Start location tracing
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
    }

    /**
     * GPS Location Listener Class
     */
    private inner class GPSLocationListener: LocationListener {
        override fun onLocationChanged(location: Location?) {
            // Get latitude from Location object
            _latitude = location!!.latitude
            // Get longitude from Location object
            _longitude = location!!.longitude
            // Show gotten latitude
            val tvLatitude = findViewById<TextView>(R.id.tvLatitude)
            tvLatitude.text = _latitude.toString()
            // Show gotten longitude
            val tvLongitude = findViewById<TextView>(R.id.tvLongitude)
            tvLongitude.text = _longitude.toString()
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        override fun onProviderEnabled(provider: String?) {}

        override fun onProviderDisabled(provider: String?) {}
    }

    /**
     * Process after user selected 'ALLOW' or 'DENY'.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // if user chose 'ALLOW' in ACCESS_FINE_LOCATION dialog...
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Get location object
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            // Create Listener object when location is updated
            val locationListener = GPSLocationListener()
            // Again, check if ACCESS_FINE_LOCATION is permitted, and if not permitted then process is ended
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
                return
            }
            // Start location tracing
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        }
    }

    /**
     * Search Map Button Function
     */
    fun onMapSearchButtonClick(view: View) {
        // Get keyword string
        val etSearchWord = findViewById<EditText>(R.id.etSearchWord)
        var searchWord = etSearchWord.text.toString()
        // Encode entered keyword
        searchWord = URLEncoder.encode(searchWord, "UTF-8")
        // Create URI string associated with Map App
        val uriStr = getString(R.string.geo_0_0) + searchWord
        // Get Intent object
        val intent = createIntent(uriStr, Intent.ACTION_VIEW)
        // Launch Activity
        startActivity(intent)
    }

    /**
     * Show Map Button Function
     */
    fun onMapShowCurrentButtonClick(view: View) {
        // Create URI string from _latitude and _longitude
        val uriStr = getString(R.string.geo) + _latitude + "," + _longitude
        // Get Intent object
        val intent = createIntent(uriStr, Intent.ACTION_VIEW)
        // Launch Activity
        startActivity(intent)
    }

    /**
     * Create and return Intent object
     * @param uriStr: URI String
     * @param intentAction: Intent Action String
     * @return Intent object
     */
    private fun createIntent(uriStr: String, intentAction: String): Intent {
        // Create URI object from URI string
        val uri = Uri.parse(uriStr)
        // Create and return Intent object
        return Intent(intentAction, uri)
    }
}
