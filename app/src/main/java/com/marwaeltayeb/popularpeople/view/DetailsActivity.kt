package com.marwaeltayeb.popularpeople.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.adapter.ActorAdapter
import com.marwaeltayeb.popularpeople.adapter.ImageAdapter
import com.marwaeltayeb.popularpeople.di.ViewModelProviderFactory
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.utils.Const.Companion.CURRENT_ACTOR
import com.marwaeltayeb.popularpeople.utils.Const.Companion.CURRENT_IMAGE
import com.marwaeltayeb.popularpeople.utils.Const.Companion.IMAGE_LINK
import com.marwaeltayeb.popularpeople.utils.Gender
import com.marwaeltayeb.popularpeople.viewmodel.ActorViewModel
import com.marwaeltayeb.popularpeople.viewmodel.ImageViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailsActivity : DaggerAppCompatActivity(), ImageAdapter.OnItemClickListener {

    @Inject
    lateinit var providerFactory : ViewModelProviderFactory

    private lateinit var actorImage: ImageView
    private lateinit var actorName: TextView
    private lateinit var actorPopularity: TextView
    private lateinit var actorDepartment: TextView
    private lateinit var actorGender: TextView
    private lateinit var actorKnownFor: TextView
    private var actorId: Int = 0

    private lateinit var recyclerView: RecyclerView
    @Inject lateinit var imageAdapter: ImageAdapter
    private lateinit var imageViewModel: ImageViewModel

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

            actorId = currentActor.actorId

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

        imageViewModel = ViewModelProvider(this, providerFactory).get(ImageViewModel::class.java)

        initViews()

        loadImages()
    }


    private fun initViews(){
        recyclerView = findViewById(R.id.rcActorImageList)
        val gridLayoutManager = GridLayoutManager(
            this,
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
        )
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)

        imageAdapter.setOnItemClickListener(this)
    }

    private fun loadImages() {
        imageViewModel.getAllImages(actorId.toString()).observe(this, {
            imageAdapter.setImages(it)
        })

        recyclerView.adapter = imageAdapter
        imageAdapter.notifyDataSetChanged()
    }

    private fun getGender(gender: String) {
        if(gender == Gender.MALE.name) {
            actorGender.text = getString(R.string.male)
        }else{
            actorGender.text = getString(R.string.female)
        }
    }

    override fun onItemClick(image: Image?) {
        intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(CURRENT_IMAGE, image)
        startActivity(intent)
    }
}