package com.example.aula35.listagem.repository

import com.example.aula35.utils.NetworkUtils
import retrofit2.http.GET

interface CategoriaEndpoint {
    @GET("categories")
    suspend fun obterLista(): List<String>

    companion object {
        val endpoint: CategoriaEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(CategoriaEndpoint::class.java)
        }
    }
}