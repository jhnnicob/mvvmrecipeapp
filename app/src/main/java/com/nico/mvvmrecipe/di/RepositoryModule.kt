package com.nico.mvvmrecipe.di

import com.nico.mvvmrecipe.network.RecipeService
import com.nico.mvvmrecipe.network.model.RecipeDtoMapper
import com.nico.mvvmrecipe.repository.RecipeRepository
import com.nico.mvvmrecipe.repositoryImp.RecipeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository{
        return RecipeRepositoryImp(recipeService, recipeDtoMapper)
    }
}