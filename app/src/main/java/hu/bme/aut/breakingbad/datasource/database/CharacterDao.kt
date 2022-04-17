package hu.bme.aut.breakingbad.datasource.database

import hu.bme.aut.breakingbad.model.CharacterEntity
import javax.inject.Inject

class CharacterDao @Inject constructor() {

    fun getCharactersByName(name: String?): List<CharacterEntity> {
        TODO()
    }

    fun getCharacter(id: Int): CharacterEntity? {
        TODO()
    }
}