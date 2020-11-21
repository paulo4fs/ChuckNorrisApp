package com.example.aula35.listagem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aula35.listagem.repository.CategoriaRepository

class CategoriasViewModelFactory(private val repository: CategoriaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriasViewModel(repository) as T
    }
}