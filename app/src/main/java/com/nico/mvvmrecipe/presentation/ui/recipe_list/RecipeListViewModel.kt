package com.nico.mvvmrecipe.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nico.mvvmrecipe.domain.model.Recipe
import com.nico.mvvmrecipe.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String
): ViewModel(){

//    This is use to observe recipe inside fragment
//    private val _recipe: MutableLiveData<List<Recipe>> = MutableLiveData()
//    val recipe: LiveData<List<Recipe>> get() = _recipe

//  This is inside composable component
    val recipes: MutableState<List<Recipe>> = mutableStateOf((listOf()))

    init {
        newSearch()
    }

    private fun newSearch() {
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = "carrot"
            )
            recipes.value = result
        }
    }
}