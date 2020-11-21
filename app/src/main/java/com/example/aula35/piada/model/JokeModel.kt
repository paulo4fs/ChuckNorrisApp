package com.example.aula35.piada.model

import com.google.gson.annotations.SerializedName

data class JokeModel(
    val id: String,
    val categories: List<String>,
    @SerializedName("icon_url")
    val image: String,
    val value: String
)