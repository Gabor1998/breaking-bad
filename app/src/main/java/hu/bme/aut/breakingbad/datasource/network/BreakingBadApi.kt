package hu.bme.aut.breakingbad.datasource.network

import hu.bme.aut.breakingbad.model.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("characters")
    suspend fun getCharactersByName(@Query("name") name: String?): List<CharacterDto>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDto
}