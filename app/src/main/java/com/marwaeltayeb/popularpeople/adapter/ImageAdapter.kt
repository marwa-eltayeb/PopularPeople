package com.marwaeltayeb.popularpeople.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.utils.ImageUtils.createImageLink
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

        val imageUrl = createImageLink(currentImage.image)
        Picasso.get().load(imageUrl).into(holder.actorImage)

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