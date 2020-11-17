package com.marwaeltayeb.popularpeople.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.utils.Const
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var images: List<Image> = ArrayList()

    private lateinit var mItemClickListener:OnItemClickListener

    /**
     * The interface that receives onClick messages.
     */
    interface OnItemClickListener {
        fun onItemClick(image: Image?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.image_list_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage: Image = images[position]

        val imageUrl = Const.IMAGE_LINK + currentImage.image

        Picasso.get()
            .load(imageUrl)
            .into(holder.actorImage, object : Callback {
                override fun onSuccess() {
                    Log.d("image", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("image", "error")
                }
            })

        if(::mItemClickListener.isInitialized){
            holder.itemView.setOnClickListener {
                mItemClickListener.onItemClick(currentImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setImages(images: List<Image>){
        this.images = images
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mItemClickListener = listener
    }

    class ImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var actorImage: ImageView

        init {
            actorImage = itemView.findViewById(R.id.actorImage)
        }
    }
}