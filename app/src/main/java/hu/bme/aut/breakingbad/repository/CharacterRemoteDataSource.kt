package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.datasource.network.BreakingBadApi
import hu.bme.aut.breakingbad.model.toCharacter
import hu.bme.aut.breakingbad.util.safeApiCall
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val breakingBadApi: BreakingBadApi
) {

    suspend fun getCharactersByName(name: String?) = safeApiCall {
        breakingBadApi.getCharactersByName(name).map { it.toCharacter() }
    }

    suspend fun getCharacter(id: Int) = safeApiCall {
        breakingBadApi.getCharacter(id).toCharacter()
    }
}