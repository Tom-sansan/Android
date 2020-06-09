package com.websarva.wings.android.servicesample

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.io.IOException

class SoundManageService : Service() {

    // Media Player
    private var _player: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        // Create MediaPlayer object
        _player = MediaPlayer()
        // Prepare ID string for notification channel
        val id = getString(R.string.notification_channel_id)
        // Prepare notification channel name
        val name = getString(R.string.notification_channel_name)
        // Set channel importance to DEFAULT
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        // Create notification channel
        val channel = NotificationChannel(id, name, importance)
        // Enable vibration
        channel.enableVibration(true)
        // Set Lock Screen Visibility as Public
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC;
        // Get NotificationManager object
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Set notification channel
        manager.createNotificationChannel(channel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Create URI string of media file
        val mediaFileUriStr = "android.resource://${packageName}/${R.raw.mountain_stream}"
        // Create URI object
        val mediaFileUri = Uri.parse(mediaFileUriStr)
        try {
            // Get media file
            _player?.setDataSource(applicationContext, mediaFileUri)
            // Set Listener for Start
            _player?.setOnPreparedListener(PlayerPreparedListener())
            // Get Listener for End
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

        // Return constant value
        return Service.START_NOT_STICKY
    }

    /**
     * Release MediaPlayer object
     */
    override fun onDestroy() {
        // if MediaPlayer is not null...
        _player?.let {
            // If Player is playing...
            if(it.isPlaying) {
                // Stop Player
                it.stop()
            }
            // Release Player
            it.release()
            // Set Player to null
            _player = null
        }
    }

    /**
     * Listener Class for Being Ready for Play Start
     */
    private inner class PlayerPreparedListener: MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            // Start media file
            mp?.start()
            // Create Builder class for Notification
            val builder = NotificationCompat.Builder(applicationContext, getString(R.string.notification_channel_id))
            // Set Icon for Display area
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            // Set notification title in notification drawer
            builder.setContentTitle(getString(R.string.msg_notification_title_start))
            // Set display message in notification drawer
            builder.setContentText(getString(R.string.msg_notification_text_start))
            // Create Intent object
            val intent = Intent(applicationContext, MainActivity::class.java)
            // Put transferred data
            intent.putExtra("fromNotification", true)
            // Get PendingIntent object
            val stopServiceIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            // Set PendingIntent object to Builder
            builder.setContentIntent(stopServiceIntent)
            // Configure to automatically delete tapped notification
            builder.setAutoCancel(true)
            // Create Notification object from Builder
            val notification = builder.build()
            // Get NotificationManager object
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // Notify
            manager.notify(1, notification)
        }
    }

    /**
     * Listener Class for Play End
     */
    private inner class PlayerCompletionListener: MediaPlayer.OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer?) {
            // Create Builder class for Notification
            val builder = NotificationCompat.Builder(applicationContext, getString(R.string.notification_channel_id))
            // Set Icon to notification area
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            // Set notification title in notification drawer
            builder.setContentTitle(getString(R.string.msg_notification_title_finish))
            // Set display message in notification drawer
            builder.setContentText(getString(R.string.msg_notification_text_finish))
            // Create notification object
            val notification = builder.build()
            // Get NotificationManager object
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // Notify
            manager.notify(0, notification)
            // Stop itself
            stopSelf()
        }
    }
}
