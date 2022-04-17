package hu.bme.aut.breakingbad.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.breakingbad.model.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters WHERE name LIKE '%'||:name||'%'")
    suspend fun getCharactersByName(name: String?): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)
}