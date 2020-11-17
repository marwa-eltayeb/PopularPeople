package com.marwaeltayeb.popularpeople.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.adapter.ActorAdapter
import com.marwaeltayeb.popularpeople.viewmodel.ActorViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var actorViewModel:ActorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actorViewModel = ViewModelProvider(this).get(ActorViewModel::class.java)

        initViews()
        loadData()
    }

    private fun initViews(){
        recyclerView = findViewById(R.id.rcActorsList)
        val gridLayoutManager = GridLayoutManager(
            this,
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4
        )
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)

        actorAdapter = ActorAdapter()
    }

    private fun loadData() {
        actorViewModel.actorPagedList.observe(this, {
            actorAdapter.submitList(it)
        })

        recyclerView.adapter = actorAdapter
        actorAdapter.notifyDataSetChanged()
    }
}