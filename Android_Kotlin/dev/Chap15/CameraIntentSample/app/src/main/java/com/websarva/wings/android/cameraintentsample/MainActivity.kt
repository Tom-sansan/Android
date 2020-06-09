package com.websarva.wings.android.cameraintentsample

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import java.text.SimpleDateFormat
import java.util.*

// import java.util.Date

class MainActivity : AppCompatActivity() {

    // URI of saved image
    private var _imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Process after Camera app is used.
     */
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // In case of return from Camera app and taking photo is successful.
        if (requestCode == 200 && resultCode == RESULT_OK) {
            // Get Bitmap data of Image
            // val bitmap = data?.getParcelableExtra<Bitmap>("data")
            // Get ImageView to show image
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
            // Set taken photo to ImageView
            // ivCamera.setImageBitmap(bitmap)
            // Set image uri to ImageView
            ivCamera.setImageURI(_imageUri)
        }
    }

    /**
     * Function after user selected 'ALLOW' or 'DENY'
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // If user approved in WRITE_EXTERNAL_STORAGE
        if(requestCode == 2000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Launch Camera app again
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
            onCameraImageClick(ivCamera)
        }
    }

    /**
     * onCameraImageClick Function
     */
    /**
    fun onCameraImageClick(view: View) {
        // Create Intent object
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Launch activity
        startActivityForResult(intent, 200)
    }
    */
    fun onCameraImageClick(view: View) {
        // if WRITE_EXTERNAL_STORAGE is not approved...
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
           PackageManager.PERMISSION_GRANTED) {
            // Show a dialog to ask for permission of WRITE_EXTERNAL_STORAGE
            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions, 2000)
            return
        }

        // Create date format by yyyyMMddHHmmss
        val dateFormat = SimpleDateFormat(getString(R.string.date_format_yyyyMMddHHmmss))
        // Get present date
        val now = Date()
        // Create formatted string by yyyyMMddHHmmss from now
        val nowStr = dateFormat.format(now)
        // Create image file name to store phone storage
        val fileName = getString(R.string.image_file_name) + nowStr + getString(R.string.file_extension_jpg)

        // Create ContentValues object
        val values = ContentValues()
        // Set image file name
        values.put(MediaStore.Images.Media.TITLE, fileName)
        // Set image file type
        values.put(MediaStore.Images.Media.MIME_TYPE, getString(R.string.image_type_jpeg))

        // Create URI object using ContentResolver
        _imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        // Create Intent object
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // set _image as Extra info
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri)
        // Launch Activity
        startActivityForResult(intent, 200)
    }
}
