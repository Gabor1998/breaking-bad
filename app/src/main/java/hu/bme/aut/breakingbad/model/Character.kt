package hu.bme.aut.breakingbad.model

data class Character(
    val id: Int,
    val name: String,
    val birthday: String? = null,
    val occupation: List<String> = emptyList(),
    val img: String? = null,
    val status: String? = null,
    val nickname: String? = null,
    val appearance: List<Int> = emptyList(),
    val portrayed: String? = null,
    val category: String? = null
)

fun Character.toCharacterEntity() = CharacterEntity(
    id = id,
    name = name,
    birthday = birthday,
    occupation = occupation,
    img = img,
    status = status,
    nickname = nickname,
    appearance = appearance,
    portrayed = portrayed,
    category = category
)
