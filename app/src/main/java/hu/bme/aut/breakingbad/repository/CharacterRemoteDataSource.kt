package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.datasource.network.BreakingBadApi
import hu.bme.aut.breakingbad.model.Character
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    breakingBadApi: BreakingBadApi
) {

    fun getCharacters(): List<Character> {
        TODO()
    }

    fun getCharacter(): Character {
        TODO()
    }
}