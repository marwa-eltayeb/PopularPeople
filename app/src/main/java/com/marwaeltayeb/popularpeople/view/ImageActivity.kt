package com.marwaeltayeb.popularpeople.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.utils.Const
import com.marwaeltayeb.popularpeople.utils.ImageUtils.createImageLink
import com.marwaeltayeb.popularpeople.utils.ImageUtils.downloadImage
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import maes.tech.intentanim.CustomIntent

class ImageActivity : DaggerAppCompatActivity() {

    private lateinit var actorImage: ImageView
    private lateinit var imageUrl: String
    private val PERMISSION_EXTERNAL_STORAGE = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        actorImage = findViewById(R.id.actorImage)

        val currentImage = intent.getSerializableExtra(Const.CURRENT_IMAGE) as? Image

        imageUrl = createImageLink(currentImage!!.image)
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

            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveImage() {
        // Check if write external storage permission is granted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            downloadImage(this, imageUrl)
        } else {
            // Request Writer external storage permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_EXTERNAL_STORAGE &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Download actor image if permission is granted
            downloadImage(this, imageUrl)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this, getString(R.string.bottomToUp_anim))
    }
}