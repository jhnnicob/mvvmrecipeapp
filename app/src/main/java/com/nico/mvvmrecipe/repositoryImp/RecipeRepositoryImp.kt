package com.nico.mvvmrecipe.repositoryImp

import com.nico.mvvmrecipe.domain.model.Recipe
import com.nico.mvvmrecipe.network.RecipeService
import com.nico.mvvmrecipe.network.model.RecipeDtoMapper
import com.nico.mvvmrecipe.repository.RecipeRepository

class RecipeRepositoryImp(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
): RecipeRepository {

    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val result = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        val result = recipeService.get(token, id)
        return mapper.mapToDomainModel(result)
    }
}