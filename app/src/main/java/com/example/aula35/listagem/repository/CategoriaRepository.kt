package com.example.aula35.listagem.repository

class CategoriaRepository {
    private val client = CategoriaEndpoint.endpoint

    suspend fun obterLista() = client.obterLista()
}