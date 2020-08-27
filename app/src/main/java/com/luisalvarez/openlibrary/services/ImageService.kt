package com.luisalvarez.openlibrary.services

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

//class ImageService @Inject constructor(private val context: Context) {
//    private val imageHeight = context.resources.getDimensionPixelSize(R.dimen.gif_image_width)
//    private val imageWidth = imageHeight
//
//    fun load(url: String): RequestBuilder<> {
//        return Glide.with(context)
//            .asGif()
//            .apply(
//                RequestOptions.noTransformation()
//                    .error(R.mipmap.ic_launcher)
//                    .fallback(R.mipmap.ic_launcher)
//            )
//            .load(url)
//    }
//}

fun ImageView.load(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}