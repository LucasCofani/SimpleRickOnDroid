package br.lucascofani.simplerickondroid.di

import br.lucascofani.simplerickondroid.models.character.CharacterDtoMapper
import br.lucascofani.simplerickondroid.models.episode.EpisodeDtoMapper
import br.lucascofani.simplerickondroid.models.location.LocationDtoMapper
import br.lucascofani.simplerickondroid.network.RickMortyAPIService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCharacterMapper(): CharacterDtoMapper {
        return CharacterDtoMapper()
    }

    @Singleton
    @Provides
    fun provideEpisodeMapper(): EpisodeDtoMapper {
        return EpisodeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideLocationMapper(): LocationDtoMapper {
        return LocationDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRickMortyAPIService(): RickMortyAPIService {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RickMortyAPIService::class.java)
    }

}