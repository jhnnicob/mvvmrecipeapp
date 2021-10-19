package com.nico.mvvmrecipe.network.response

import com.google.gson.annotations.SerializedName
import com.nico.mvvmrecipe.network.model.RecipeDto

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>
)