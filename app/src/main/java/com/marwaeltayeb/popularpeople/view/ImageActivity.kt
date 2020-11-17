package com.marwaeltayeb.popularpeople.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.utils.Const
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageActivity : AppCompatActivity() {

    private lateinit var actorImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        actorImage = findViewById(R.id.actorImage)

        val currentImage = intent.getSerializableExtra(Const.CURRENT_IMAGE) as? Image

        val imageUrl = Const.IMAGE_LINK + currentImage!!.image

        Picasso.get()
            .load(imageUrl)
            .into(actorImage, object : Callback {
                override fun onSuccess() {
                    Log.d("image", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("image", "error")
                }
            })

    }
}