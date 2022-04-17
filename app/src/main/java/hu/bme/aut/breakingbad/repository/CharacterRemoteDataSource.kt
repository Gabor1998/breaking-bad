package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.datasource.network.BreakingBadApi
import hu.bme.aut.breakingbad.model.toCharacter
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val breakingBadApi: BreakingBadApi
) {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.success(apiCall())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    suspend fun getCharactersByName(name: String?) = safeApiCall {
        breakingBadApi.getCharactersByName(name).map { it.toCharacter() }
    }

    suspend fun getCharacter(id: Int) = safeApiCall {
        breakingBadApi.getCharacter(id).toCharacter()
    }
}