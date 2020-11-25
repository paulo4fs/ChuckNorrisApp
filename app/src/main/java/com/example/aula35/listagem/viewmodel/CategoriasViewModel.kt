package com.example.aula35.listagem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.aula35.listagem.repository.CategoriaRepository
import kotlinx.coroutines.Dispatchers

class CategoriasViewModel(
    private val repository: CategoriaRepository
) : ViewModel() {
    var categorias = listOf<String>()

    fun obterLista() = liveData(Dispatchers.IO) {
        if (categorias.isEmpty()) {
            val response = repository.obterLista()
            categorias = response
        }

        emit(categorias)
    }
}