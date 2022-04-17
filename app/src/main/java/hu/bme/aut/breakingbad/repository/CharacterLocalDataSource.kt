package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.datasource.database.CharacterDao
import hu.bme.aut.breakingbad.model.Character
import hu.bme.aut.breakingbad.model.toCharacter
import hu.bme.aut.breakingbad.model.toCharacterEntity
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {

    suspend fun getCharactersByName(name: String?) = characterDao.getCharactersByName(name).map { it.toCharacter() }

    suspend fun getCharacter(id: Int) = characterDao.getCharacter(id)?.toCharacter()

    suspend fun insertCharacters(characters: List<Character>) = characterDao.insertCharacters(characters.map { it.toCharacterEntity() })
}