package com.tunahanozatac.appcentapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tunahanozatac.appcentapp.R

class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("newsImage")
        fun loadImage(view: ImageView, profileImage: String) {
            if (!profileImage.isEmpty()) {
                var requestOption = RequestOptions()
                requestOption = requestOption.transforms(RoundedCorners(5))
                profileImage?.apply {
                    Glide.with(view.context)
                        .load(this)
                        .thumbnail(Glide.with(view.context).load(R.drawable.load))
                        .apply(requestOption).into(view)
                }
            }
        }
    }
}