package hu.bme.aut.breakingbad.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("circle_image")
fun AppCompatImageView.setCircleImage(url: String?) {
    Glide.with(context)
        .load(url?.toUri())
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}