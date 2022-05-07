package hu.bme.aut.breakingbad.domain

import hu.bme.aut.breakingbad.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(id: Int) = characterRepository.getCharacter(id)
}