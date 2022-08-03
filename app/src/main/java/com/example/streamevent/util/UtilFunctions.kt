package com.example.streamevent.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.streamevent.R

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions().centerInside())
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_broken_image)
        .fallback(R.drawable.ic_broken_image)
        .into(this)
}