package com.websarva.wings.android.databasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    // Selected Cocktail Id
    private var _cocktailId = -1
    // Selected Cocktail Name
    private var _cocktailName = ""
    // Database Helper object
    private val _helper = DatabaseHelper(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get ViewList(lvCocktail)
        val lvCocktail = findViewById<ListView>(R.id.lvCocktail)
        // Register Listener to lvCocktail
        lvCocktail.onItemClickListener = ListItemClickListener()
    }

    override fun onDestroy() {
        // Clear helper object
        _helper.close()
        super.onDestroy()
    }
    /**
     * Save button function
     */
    fun onSaveButtonClick(veiw: View) {
        // Get comment
        val etNote = findViewById<EditText>(R.id.etNote)
        // Get entered comment
        val note = etNote.text.toString()

        // Get database connection object from database helper object
        val db = _helper.writableDatabase
        // Prepare Delete statement
        val sqlDelete = "DELETE FROM cocktailmemos WHERE _id = ?"
        // Get prepared statement
        var stmt = db.compileStatement(sqlDelete)
        // Bind val
        stmt.bindLong(1, _cocktailId.toLong())
        // Execute Delete SQL
        stmt.executeUpdateDelete()

        // Prepare INSERT statement
        val sqlInsert = "INSERT INTO cocktailmemos (_id, name, note) VALUES (?, ?, ?)"
        // Get prepared statement
        stmt = db.compileStatement(sqlInsert)
        // Bind variables
        stmt.bindLong(1, _cocktailId.toLong())
        stmt.bindString(2, _cocktailName)
        stmt.bindString(3, note)
        // Execute INSERT SQL
        stmt.executeInsert()

        // Erase entered comment
        etNote.setText("")
        // Get TextView for cocktail name
        val tvCocktailName = findViewById<TextView>(R.id.tvCocktailName)
        // Change cocktail name to not selected
        tvCocktailName.text = getString(R.string.tv_name)
        // Get Save Button
        val btnSave = findViewById<Button>(R.id.btnSave)
        // Disable save button
        btnSave.isEnabled = false
    }
    /**
     * List Item Click Listener
     */
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // Get tapped row number
            _cocktailId = position
            // Get tapped row data
            _cocktailName = parent?.getItemAtPosition(position) as String
            // Get TextView for cocktail name
            val tvCocktailName = findViewById<TextView>(R.id.tvCocktailName)
            // Set cocktail name to TextView
            tvCocktailName.text = _cocktailName
            // Get save button
            val btnSave = findViewById<Button>(R.id.btnSave)
            // Enable save button
            btnSave.isEnabled = true

            // Get database connection object from database helper object
            val db = _helper.writableDatabase
            // Prepare search SQL
            val sql = "SELECT * FROM cocktailmemos WHERE _id = ${_cocktailId}"
            // Execute SQL
            var cursor = db.rawQuery(sql, null)
            // Prepare variable to store value retrieved from the table
            var note = ""
            // Get data from cocktailmemos table
            while (cursor.moveToNext()) {
                // Get column index
                val idxNote = cursor.getColumnIndex("note")
                // Get data based on index
                note = cursor.getString(idxNote)
            }
            //
            val etNote = findViewById<EditText>(R.id.etNote)
            etNote.setText(note)
        }
    }
}
