package hu.bme.aut.breakingbad.datasource.database

import hu.bme.aut.breakingbad.model.CharacterEntity
import javax.inject.Inject

class CharacterDao @Inject constructor() {

    fun getCharacters(): List<CharacterEntity> {
        TODO()
    }

    fun getCharacter(): CharacterEntity? {
        TODO()
    }
}