package com.websarva.wings.android.mediasample

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // Media Player
    private var _player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create Media Player object
        _player = MediaPlayer()
        // Create URI string for media file
        //val mediaFileUriStr = getString(R.string.media_file_Uri)
        val mediaFileUriStr = "android.resource://${packageName}/${R.raw.button_click_long}"
        // Create URI object
        val mediaFileUri = Uri.parse(mediaFileUriStr)
        try {
            // Set media file to Media Player
            _player?.setDataSource(applicationContext, mediaFileUri)
            // Set Listener for Start
            _player?.setOnPreparedListener(PlayerPreparedListener())
            // Set Listener for End
            _player?.setOnCompletionListener(PlayerCompletionListener())
            // Prepare media play
            _player?.prepareAsync()
        }
        catch (ex: IllegalArgumentException) {
            Log.e(getString(R.string.app_name), getString(R.string.ex_illegal_Args))
        }
        catch (ex: IOException) {
            Log.e(getString(R.string.app_name), getString(R.string.ex_io_exception))
        }

        // Get Switch
        val loopSwitch = findViewById<Switch>(R.id.swLoop)
        // Set Listener for Switch
        loopSwitch.setOnCheckedChangeListener(LoopSwitchChangedListener())
    }

    /**
     * Release MediaPlayer object
     */
    override fun onDestroy() {
        super.onDestroy()
        // if _player is not null
        _player?.let {
            // if Player is playing...
            if(it.isPlaying) {
                // Stop Player
                it.stop()
            }
            // Release Player
            it.release()
            // Set _player to null
            _player = null
        }
    }

    /**
     * Listener Class for Being Ready for Play Start
     */
    private inner class PlayerPreparedListener: MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            // Set Tap-enabled for each button
            val btPlay = findViewById<Button>(R.id.btPlay)
            btPlay.isEnabled = true
            val btBack = findViewById<Button>(R.id.btBack)
            btBack.isEnabled = true
            val btForward = findViewById<Button>(R.id.btForward)
            btForward.isEnabled = true
        }
    }

    /**
     * Listener Class for Play End
     */
    private inner class PlayerCompletionListener: MediaPlayer.OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer?) {
            // If Player is not null...
            _player?.let {
                // If loop is not set...
                if(!it.isLooping) {
                    // Set Play label
                    val btPlay = findViewById<Button>(R.id.btPlay)
                    btPlay.setText(R.string.bt_play_play)
                }
            }
        }
    }

    /**
     * Listener Class for Music Repeat
     */
    private inner class LoopSwitchChangedListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            // Set loop
            _player?.isLooping = isChecked
        }
    }

    /**
     * Player Button Function
     */
    fun onPlayButtonClick(view: View) {
        // if field player is not null...
        _player?.let {
            // Get Play button
            val btPlay = findViewById<Button>(R.id.btPlay)
            // if Player is playing...
            if(it.isPlaying) {
                // Stop Player
                it.pause()
                // Set label to "Play"
                btPlay.setText(R.string.bt_play_play)
            }
            // if Player is not playing...
            else {
                // Play Player
                it.start()
                // Set label to "Stop"
                btPlay.setText(R.string.bt_play_pause)
            }
        }
    }

    /**
     * Forward Button
     */
    fun onForwardButtonClick(view: View) {
        // If Player is not null...
        _player?.let {
            // Get the length of playing media file
            val duration = it.duration
            // Change play position to the end
            it.seekTo(duration)
            // If Player is not playing...
            if(!it.isPlaying) {
                // Start play
                it.start()
            }
        }
    }

    /**
     * Back Button
     */
    fun onBackButtonClick(view: View) {
        // Change play position to the start
        _player?.seekTo(0)
    }
}
