package com.example.aula35.piada.repository

class JokeRepository {
    private val client = JokeEndpoint.endpoint

    suspend fun obterJoke(category: String) = client.obterJoke(category)
}