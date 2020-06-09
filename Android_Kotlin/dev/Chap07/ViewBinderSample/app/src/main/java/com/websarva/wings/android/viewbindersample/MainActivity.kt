package com.websarva.wings.android.viewbindersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

/**
 * View Binder Sample
 */
class MainActivity : AppCompatActivity() {
    
    private val name : String = "name"
    private val sex : String = "sex"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create Name List
        val nameList = createNameList()
        // Create from parameter
        val from = arrayOf(name, sex)
        // Create to parameter
        val to = intArrayOf(R.id.tvName, R.id.imSex)
        // Create SimpleAdapter
        val adapter = SimpleAdapter(applicationContext, nameList, R.layout.row, from, to)
        // Register CustomViewBinder
        adapter.viewBinder = CustomViewBinder()
        // Get ListView
        val lvPhones = findViewById<ListView>(R.id.lvNameList)
        // Register adapter
        lvPhones.adapter = adapter
    }

    // CustomViewBinder for ListView
    private inner class CustomViewBinder : SimpleAdapter.ViewBinder {
        /*
         * view:                partial data in each row
         * data:                data for each row
         * textRepresentation:  data converted by string
         */
        override fun setViewValue(view: View?, data: Any?, textRepresentation: String?): Boolean {
            when(view?.id) {
                //
                R.id.tvName -> {
                    val tvName = view as TextView
                    tvName.text = textRepresentation
                    return true
                }
                R.id.imSex -> {
                    val imPhoneType = view as ImageView
                    val sex = data as Int
                    when(sex) {
                        // for female
                        0 ->
                            // Set female icon
                            imPhoneType.setImageResource(R.drawable.ic_female)
                        // for male
                        1 ->
                            // Set male icon
                            imPhoneType.setImageResource(R.drawable.ic_male)
                    }
                    return true
                }
            }
            return false
        }
    }

    // Create Name List
    private fun createNameList(): MutableList<MutableMap<String, Any>> {
        var nameList = mutableListOf<MutableMap<String, Any>>()
        nameList.add(mutableMapOf(name to "Ichiro Tanaka", sex to 1))
        nameList.add(mutableMapOf(name to "Kaori Eto", sex to 0))
        nameList.add(mutableMapOf(name to "Yuko Yakayama", sex to 0))
        nameList.add(mutableMapOf(name to "Genzo Nakatani", sex to 1))
        nameList.add(mutableMapOf(name to "Naomi Yamashita", sex to 0))
        nameList.add(mutableMapOf(name to "Shota Suzuki", sex to 1))
        nameList.add(mutableMapOf(name to "Shinji Ishibashi", sex to 1))
        nameList.add(mutableMapOf(name to "Takanori Sugimoto", sex to 1))
        nameList.add(mutableMapOf(name to "Tomoko Makino", sex to 0))
        nameList.add(mutableMapOf(name to "Haruka Mikami", sex to 0))
        nameList.add(mutableMapOf(name to "Hiroaki Ono", sex to 1))
        nameList.add(mutableMapOf(name to "Kenta Nishiguchi", sex to 1))
        nameList.add(mutableMapOf(name to "Akemi Hishino", sex to 0))
        return nameList
    }
}
