package com.exclr8.n1corporate.Helpers

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.exclr8.n1corporate.APIHelpers.URLs

class ImageLoader {

    fun get(context: Context, imageKeyString: String, imageRes: ImageView){
        val imageURL = URLs().createSpecImageURL(imageKeyString)
        Glide.with(context)
            .load(imageURL)
            .into(imageRes)
    }

    fun getCache(context: Context, imageList: MutableList<String>, label: TextView, callback: (Boolean) -> Unit){
        var imageCount = 0
        for (imageIndex in 0 until imageList.count()) {
            val imageURL = URLs().createSpecImageURL(imageList[imageIndex])
            Glide.with(context)
                .load(imageURL)
                .addListener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                        imageCount++
                        return false
                    }

                    override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {

                        if (imageCount == imageList.count()-1){
                            callback(true)
                        }
                        val percentage = ((imageCount.toDouble() / imageList.count().toDouble()) * 100).toInt()
                        label.text = "Prefetching some data " + percentage + "%"
                        imageCount++
                        return false
                    }

                }).preload()
        }
    }
}