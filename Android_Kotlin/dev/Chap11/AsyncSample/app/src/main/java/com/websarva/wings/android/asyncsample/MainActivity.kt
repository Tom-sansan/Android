package com.websarva.wings.android.asyncsample

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.function.ToDoubleBiFunction

class MainActivity : AppCompatActivity() {

    private val _name: String = "name"
    private val _id: String = "id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get ListView for City List
        val lvCityList = findViewById<ListView>(R.id.lvCityList)
        // Prepare MutableList object for SimpleAdapter
        val cityList = createCityList()
        // Prepare from and to available
        val from = arrayOf(_name)
        val to = intArrayOf(android.R.id.text1)
        // Create SimpleAdapter
        val adapter = SimpleAdapter(applicationContext, cityList, android.R.layout.simple_expandable_list_item_1, from, to)
        // Set SimpleAdapter to ListView
        lvCityList.adapter = adapter
        // Register Listener Class for list tap
        lvCityList.onItemClickListener = ListItemClickListener()
    }
    /**
     * Listener Class for List Tap
     */
    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        @SuppressLint("SetTextI18n")
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // Get tapped city name and city id
            val item = parent?.getItemAtPosition(position) as Map<String, String>
            val cityName = item[_name]
            val cityId = item[_id]
            // Set city name to TextView
            val tvCityName = findViewById<TextView>(R.id.tvCityName)
            tvCityName.text = "Weather in $cityName: "

            // Create WeatherInfoReceiver instance
            val receiver = WeatherInfoReceiver()
            // Execute WeatherInfoReceiver
            receiver.execute(cityId)
        }
    }
    /**
     * Get Weather Information
     */
    private inner class WeatherInfoReceiver(): AsyncTask<String, String, String>() {
        // Executed in Background thread
        override fun doInBackground(vararg params: String?): String {
            // Get city Id
            val id = params[0]
            // Create connection URL based on city Id
            // val urlStr = "http://weather.livedoor.com/forecast/webservice/json/v1?city=${id}"
            val urlStr = getString(R.string.url_livedoor_api) + id
            // Create URL object
            val url = URL(urlStr)
            // Get HttpURLConnection object from URL object
            val con = url.openConnection() as HttpURLConnection
            // Set http connection method
            con.requestMethod = "GET"
            // Connect
            con.connect()
            // Get response data from HttpURLConnection object
            val stream = con.inputStream
            // Convert InputStream object to JSON string
            val result = is2String(stream)
            // Disconnect HttpURLConnection object
            con.disconnect()
            // Close InputSteam object
            stream.close()
            // Return JSON string
            return result
        }
        // Executed in UI thread
        override fun onPostExecute(result: String?) {
            // Create JSONObject object from JSON string
            val rootJSON = JSONObject(result)
            // Get JSON object 'description'
            val descriptionJSON = rootJSON.getJSONObject("description")
            // Get text string in 'description'
            val desc = descriptionJSON.getString("text")
            // Get 'forecasts' JSON string
            val forecasts = rootJSON.getJSONArray("forecasts")
            // Get JSON object at index 0 in 'forecasts' JSON array
            val forecastNow = forecasts.getJSONObject(0)
            // Get 'telop' string from 'forecasts'
            val telop = forecastNow.getString("telop")

            // Set weather info to TextView
            val tvWeatherTelop = findViewById<TextView>(R.id.tvWeatherTelop)
            val tvWeatherDesc = findViewById<TextView>(R.id.tvWeatherDesc)
            tvWeatherTelop.text = telop
            tvWeatherDesc.text = desc
        }
        // Convert InputStream object to String
        private fun is2String(stream: InputStream): String {
            val sb = StringBuilder()
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var line = reader.readLine()
            while (line != null) {
                sb.append(line)
                line = reader.readLine()
            }
            reader.close()
            return sb.toString()
        }
    }
    /**
     * Create City List
     */
    private fun createCityList(): MutableList<MutableMap<String, String>> {
        val cityList = mutableListOf<MutableMap<String, String>>()
        cityList.add(mutableMapOf(_name to "Tokyo", _id to "130010"))
        cityList.add(mutableMapOf(_name to "Osaka", _id to "270000"))
        cityList.add(mutableMapOf(_name to "Kobe", _id to "280010"))
        cityList.add(mutableMapOf(_name to "Toyooka", _id to "280020"))
        cityList.add(mutableMapOf(_name to "Kyoto", _id to "260010"))
        cityList.add(mutableMapOf(_name to "Maizuru", _id to "260020"))
        cityList.add(mutableMapOf(_name to "Nara", _id to "290010"))
        cityList.add(mutableMapOf(_name to "Kazeya", _id to "290020"))
        cityList.add(mutableMapOf(_name to "Wakayama", _id to "300010"))
        cityList.add(mutableMapOf(_name to "Siozaki", _id to "300020"))
        cityList.add(mutableMapOf(_name to "Osaka", _id to "270000"))
        return cityList
    }
}
