package com.marwaeltayeb.popularpeople.view

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.utils.Const
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import java.lang.Math.random
import javax.inject.Inject

class ImageActivity : DaggerAppCompatActivity() {

    private lateinit var actorImage: ImageView
    private lateinit var imageUrl: String
    private val PERMISSION_EXTERNAL_STORAGE = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        actorImage = findViewById(R.id.actorImage)

        val currentImage = intent.getSerializableExtra(Const.CURRENT_IMAGE) as? Image

        imageUrl = Const.IMAGE_LINK + currentImage!!.image

        Picasso.get().load(imageUrl).into(actorImage)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.image_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saveImage -> {
                saveImage()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveImage() {
        // Check if write external storage permission is granted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            downloadImage(imageUrl)
        } else {
            // Request Writer external storage permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_EXTERNAL_STORAGE
            )
        }
    }

    private fun downloadImage(url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(getString(R.string.notification_title))
            .setDescription(getString(R.string.notification_description))
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "image" + random() + ".jpg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
        manager?.let {
            val id = it.enqueue(request)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_EXTERNAL_STORAGE &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Download actor image if permission is granted
            downloadImage(imageUrl)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}