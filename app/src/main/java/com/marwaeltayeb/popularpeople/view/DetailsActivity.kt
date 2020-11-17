package com.marwaeltayeb.popularpeople.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.utils.Const.Companion.CURRENT_ACTOR
import com.marwaeltayeb.popularpeople.utils.Const.Companion.IMAGE_LINK
import com.marwaeltayeb.popularpeople.utils.Gender
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var actorImage: ImageView
    private lateinit var actorName: TextView
    private lateinit var actorPopularity: TextView
    private lateinit var actorDepartment: TextView
    private lateinit var actorGender: TextView
    private lateinit var actorKnownFor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        actorImage = findViewById(R.id.actorImage)
        actorName = findViewById(R.id.actorName)
        actorPopularity = findViewById(R.id.actorPopularity)
        actorDepartment = findViewById(R.id.actorDepartment)
        actorGender = findViewById(R.id.actorGender)
        actorKnownFor = findViewById(R.id.actorKnownFor)


        val currentActor = intent.getSerializableExtra(CURRENT_ACTOR) as? Actor
        if (currentActor != null) {

            val imageUrl = IMAGE_LINK + currentActor.actorImage

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

            actorName.text = currentActor.actorName

            actorPopularity.text = currentActor.popularity
            actorDepartment.text = currentActor.department

            val gender = currentActor.gender.name
            getGender(gender)

            actorKnownFor.text = currentActor.actorsList.get(0).movieTitle
        }
    }

    private fun getGender(gender: String) {
        if(gender == Gender.MALE.name) {
            actorGender.text = "Male"
        }else{
            actorGender.text = "Female"
        }
    }




}