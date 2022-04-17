package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.datasource.database.CharacterDao
import hu.bme.aut.breakingbad.model.toCharacter
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {

    fun getCharactersByName(name: String?) = characterDao.getCharactersByName(name).map { it.toCharacter() }

    fun getCharacter(id: Int) = characterDao.getCharacter(id)?.toCharacter()
}