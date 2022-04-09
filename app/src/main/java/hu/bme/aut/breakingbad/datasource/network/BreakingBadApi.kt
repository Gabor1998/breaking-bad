package hu.bme.aut.breakingbad.datasource.network

import hu.bme.aut.breakingbad.model.CharacterDto
import javax.inject.Inject

class BreakingBadApi @Inject constructor() {

    fun getCharacters(): List<CharacterDto> {
        TODO()
    }

    fun getCharacter(): CharacterDto {
        TODO()
    }
}