package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.datasource.database.CharacterDao
import hu.bme.aut.breakingbad.model.Character
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    characterDao: CharacterDao
) {

    fun getCharacters(): List<Character> {
        TODO()
    }

    fun getCharacter(): Character? {
        TODO()
    }
}