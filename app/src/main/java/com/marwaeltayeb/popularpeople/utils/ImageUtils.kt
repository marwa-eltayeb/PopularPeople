package com.marwaeltayeb.popularpeople.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.utils.Const.Companion.IMAGE_LINK

object ImageUtils {

     fun downloadImage(context:Context, url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(context.getString(R.string.notification_title))
            .setDescription(context.getString(R.string.notification_description))
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, createRandomImageName())
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
        manager?.let {
            val id = it.enqueue(request)
        }
    }

    private fun createRandomImageName(): String{
        return "image" + Math.random() + ".jpg"
    }

    fun createImageLink(actorImage : String) : String {
        return IMAGE_LINK + actorImage
    }
}