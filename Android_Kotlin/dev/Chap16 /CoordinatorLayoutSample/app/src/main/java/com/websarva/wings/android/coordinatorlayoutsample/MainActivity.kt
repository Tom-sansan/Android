package com.websarva.wings.android.coordinatorlayoutsample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // createToolbar()
        createToolbarForCollapsingToolbarLayout()
        setOnClickFloatingActionButtonListener()
    }

    /**
     * Set OnClickFloatingActionButton Listener
     */
    private fun setOnClickFloatingActionButtonListener() {
        val fab = findViewById<FloatingActionButton>(R.id.fabEmail)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Fab Email was clicked!!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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
        // Set Elevation
        setElevation(toolbar)
    }

    /**
     * Set Elevation
     */
    private fun setElevation(toolbar: Toolbar) {
        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.fabEmail)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { localAppBarLayout, verticalOffset ->
            if (abs(verticalOffset) == localAppBarLayout.totalScrollRange) {
                // if AppBarLayout is invisible then...
                ViewCompat.setElevation(localAppBarLayout, 300F);
                setSupportActionBar(toolbar)
            } else {
                // if AppBarLayout is visible then...
                ViewCompat.setElevation(localAppBarLayout, 300F);
            }
        })

        /**
         * The function below is one before lambda expression
        appBarLayout.addOnOffsetChangedListener(object:AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(localAppBarLayout: AppBarLayout, verticalOffset: Int) {
                if (abs(verticalOffset) == localAppBarLayout.totalScrollRange) {
                    ViewCompat.setElevation(localAppBarLayout, 300F);
                    setSupportActionBar(toolbar)
                } else {
                    ViewCompat.setElevation(localAppBarLayout, 300F);
                }
            }
        })
         */
    }
}
