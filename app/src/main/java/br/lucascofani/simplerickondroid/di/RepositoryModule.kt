package br.lucascofani.simplerickondroid.di

import br.lucascofani.simplerickondroid.models.character.CharacterDtoMapper
import br.lucascofani.simplerickondroid.network.RickMortyAPIService
import br.lucascofani.simplerickondroid.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideCharRepository(
        api: RickMortyAPIService,
        mapper: CharacterDtoMapper,
    ): CharacterRepository {
        return CharacterRepository(
            api = api,
            mapper = mapper,
        )
    }

}