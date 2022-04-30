package hu.bme.aut.breakingbad.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("android:src")
fun AppCompatImageView.setImage(url: String?) {
    Glide.with(context)
        .load(url?.toUri())
        .into(this)
}

@BindingAdapter("circle_image")
fun AppCompatImageView.setCircleImage(url: String?) {
    Glide.with(context)
        .load(url?.toUri())
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}