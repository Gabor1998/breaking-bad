package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.model.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    characterLocalDataSource: CharacterLocalDataSource,
    characterRemoteDataSource: CharacterRemoteDataSource
) {

    fun getCharacters(): List<Character> {
        TODO()
    }

    fun getCharacter(): Character? {
        TODO()
    }
}