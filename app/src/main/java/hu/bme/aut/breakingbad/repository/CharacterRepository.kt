package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.model.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterLocalDataSource: CharacterLocalDataSource,
    private val characterRemoteDataSource: CharacterRemoteDataSource
) {

    suspend fun getCharactersByName(name: String?): List<Character> {
        characterRemoteDataSource.getCharactersByName(name).onSuccess {
            characterLocalDataSource.insertCharacters(it)
            return it
        }.onFailure {
            return characterLocalDataSource.getCharactersByName(name)
        }
        return emptyList()
    }

    suspend fun getCharacter(id: Int): Character? {
        characterRemoteDataSource.getCharacter(id).onSuccess {
            characterLocalDataSource.insertCharacters(listOf(it))
            return it
        }.onFailure {
            return characterLocalDataSource.getCharacter(id)
        }
        return null
    }
}