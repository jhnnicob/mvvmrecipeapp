package com.nico.mvvmrecipe.di

import com.google.gson.GsonBuilder
import com.nico.mvvmrecipe.BuildConfig
import com.nico.mvvmrecipe.network.RecipeService
import com.nico.mvvmrecipe.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeMapper(): RecipeDtoMapper{
        return RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .client(OkHttpClient.Builder().also{ client ->
                if(BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    client.addInterceptor(logging)
                }
            }.build())
            .addConverterFactory((GsonConverterFactory.create(GsonBuilder().create())))
            .build()
            .create(RecipeService::class.java)
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}