package hu.bme.aut.breakingbad.model

data class CharacterEntity(
    val id: Int,
    val name: String
)

fun CharacterEntity.toCharacter() = Character(
    id,
    name
)
