package hu.bme.aut.breakingbad.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.breakingbad.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BreakingBadDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}