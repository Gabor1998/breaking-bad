package hu.bme.aut.breakingbad

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.breakingbad.datasource.database.BreakingBadDatabase
import hu.bme.aut.breakingbad.datasource.database.CharacterDao
import hu.bme.aut.breakingbad.di.PersistenceModule
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PersistenceModule::class]
)
object FakePersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BreakingBadDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            BreakingBadDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(breakingBadDatabase: BreakingBadDatabase): CharacterDao {
        return breakingBadDatabase.characterDao()
    }

}