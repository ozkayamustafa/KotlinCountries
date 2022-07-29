package com.mustafa.kotlincountries.util

import android.content.Context
import android.widget.ImageView
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

