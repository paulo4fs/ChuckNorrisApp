package com.example.aula35.piada.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aula35.piada.repository.JokeRepository

class JokeViewModelFactory(
    private val repository: JokeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokeViewModel(repository) as T
    }
}