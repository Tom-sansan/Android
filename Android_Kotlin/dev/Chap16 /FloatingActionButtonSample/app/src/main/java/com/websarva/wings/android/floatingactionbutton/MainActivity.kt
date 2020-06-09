package com.websarva.wings.android.floatingactionbutton

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbarを取得。
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //ツールバーにロゴを設定。
        toolbar.setLogo(R.mipmap.ic_launcher)
        //アクションバーにツールバーを設定。
        setSupportActionBar(toolbar)
        //CollapsingToolbarLayoutを取得。
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        //タイトルを設定。
        toolbarLayout.title = getString(R.string.toolbar_title)
        //通常サイズ時の文字色を設定。
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        //縮小サイズ時の文字色を設定。
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        // createToolbarForCollapsingToolbarLayout()
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
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { localAppBarLayout, verticalOffset ->
            if (abs(verticalOffset) == localAppBarLayout.totalScrollRange) {
                // if AppBarLayout is invisible then...
                ViewCompat.setElevation(localAppBarLayout, 300F);
                // setSupportActionBar(toolbar)
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
