package hu.bme.aut.breakingbad.domain

import hu.bme.aut.breakingbad.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(name: String?) = characterRepository.getCharactersByName(name)
}