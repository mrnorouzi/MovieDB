package com.mrn.moviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * extension function for setting RecyclerView adapter in xml
 */
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
        this.itemAnimator = DefaultItemAnimator()
    }
}

@BindingAdapter("imageUrl")
fun loadImage(
    view: ImageView,
    url: String?
) {
    Glide.with(view.context).load(url).into(view)
}