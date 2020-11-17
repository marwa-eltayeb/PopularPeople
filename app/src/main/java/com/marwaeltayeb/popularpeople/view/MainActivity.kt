package com.marwaeltayeb.popularpeople.view

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.popularpeople.R
import androidx.lifecycle.Observer;
import com.marwaeltayeb.popularpeople.adapter.ActingAdapter
import com.marwaeltayeb.popularpeople.model.Acting
import com.marwaeltayeb.popularpeople.viewmodel.ActorViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var actingAdapter: ActingAdapter
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

        actingAdapter = ActingAdapter()
    }

    private fun loadData() {
        actorViewModel.actingPagedList.observe(this, {
            actingAdapter.submitList(it)
        })

        recyclerView.adapter = actingAdapter
        actingAdapter.notifyDataSetChanged()
    }
}