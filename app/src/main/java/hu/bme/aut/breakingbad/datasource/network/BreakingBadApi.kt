package hu.bme.aut.breakingbad.datasource.network

import hu.bme.aut.breakingbad.model.CharacterDto
import hu.bme.aut.breakingbad.model.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("characters")
    suspend fun getCharactersByName(@Query("name") name: String? = null): List<CharacterDto>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDto

    @GET("/api/quote/random")
    suspend fun getRandomQuoteByAuthor(@Query("author") author: String?): List<QuoteDto>
}