package com.example.rickandmortyandroid.di

import androidx.room.Room
import com.example.rickandmortyandroid.database.AppDatabase
import com.example.rickandmortyandroid.database.dao.CharacterDAO
import com.example.rickandmortyandroid.models.ResponseHandler
import com.example.rickandmortyandroid.repository.CharRepository
import com.example.rickandmortyandroid.retrofit.CharacterService
import com.example.rickandmortyandroid.ui.home.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val NOME_BANCO_DE_DADOS = "rickandmorty.db"
private const val BASE_URL = "https://rickandmortyapi.com/api/"

//similar timeout
//private const val BASE_URL = "https://rickandmortyapi.com:81/api/"

//simular erro
//private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

//private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

val retrofitModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideCharApi(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    single<Retrofit> { provideRetrofit() }
    single<CharacterService> { provideCharApi(get()) }


}

val appModules = module{
    single<ResponseHandler> { ResponseHandler() }
    single<CharRepository> { CharRepository(get(), get(), get(),get()) }
    factory<Picasso> {
        Picasso.get()
    }
    single<CoroutineDispatcher> { Dispatchers.Default }
}

val viewModelModules = module {
    viewModel {
        HomeViewModel(get(),get())
    }
}

val roomModules = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }
    single<CharacterDAO> { get<AppDatabase>().characterDao }
    //single<CharDbService> { get() }

}