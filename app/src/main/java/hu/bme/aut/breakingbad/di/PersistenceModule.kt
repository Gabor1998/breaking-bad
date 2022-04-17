package hu.bme.aut.breakingbad.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.breakingbad.datasource.database.BreakingBadDatabase
import hu.bme.aut.breakingbad.datasource.database.CharacterDao

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BreakingBadDatabase = Room.databaseBuilder(
        context,
        BreakingBadDatabase::class.java,
        "breaking-bad"
    ).build()

    @Provides
    fun provideCharacterDao(breakingBadDatabase: BreakingBadDatabase): CharacterDao {
        return breakingBadDatabase.characterDao()
    }

}