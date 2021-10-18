package com.nico.mvvmrecipe.network.model

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

data class RecipeNetworkEntity(

    @SerializedName("pk")
    val pk: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("publisher")
    val publisher: String? = null,

    @SerializedName("feature_image")
    val featureImage: String? = null,

    @SerializedName("rating")
    val rating: Int? = null,

    @SerializedName("source_url")
    val sourceUrl: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("cookingInstructions")
    val cookingInstruction: String? = null,

    @SerializedName("ingredients")
    val ingredients: List<String>? = null,

    @SerializedName("date_added")
    val dateAdded: String? = null,

    @SerializedName("dateUpdated")
    val dateUpdated: String? = null,
)
