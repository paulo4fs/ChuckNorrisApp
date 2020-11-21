package com.example.aula35.piada.repository

import com.example.aula35.piada.model.JokeModel
import com.example.aula35.utils.NetworkUtils
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeEndpoint {
    @GET("random")
    suspend fun obterJoke(
        @Query("category") category: String
    ): JokeModel

    companion object {
        val endpoint: JokeEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(JokeEndpoint::class.java)
        }
    }
}