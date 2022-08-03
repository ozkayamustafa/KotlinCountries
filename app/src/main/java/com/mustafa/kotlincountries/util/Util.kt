package com.mustafa.kotlincountries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafa.kotlincountries.R

fun ImageView.uploadInternet(url:String?,progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)

    url?.let {
        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(it)
            .into(this)

    }
}

fun progressBarCircler(context: Context):CircularProgressDrawable{
    return  CircularProgressDrawable(context).apply {
        strokeWidth = 8F
        centerRadius = 40F
        start()
    }
}

@BindingAdapter("android:downloadImageUrl")
fun downloadImageView(view: ImageView,url: String?){
     view.uploadInternet(url, progressDrawable = progressBarCircler(view.context))
}
