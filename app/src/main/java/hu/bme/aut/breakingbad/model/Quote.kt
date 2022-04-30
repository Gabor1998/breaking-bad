package hu.bme.aut.breakingbad.model

import com.squareup.moshi.Json

data class Quote(
    @Json(name = "quote_id")
    val id: Int,
    val quote: String,
    val author: String
)
