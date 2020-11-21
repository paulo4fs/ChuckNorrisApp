package com.example.aula35.piada.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.aula35.piada.repository.JokeRepository
import kotlinx.coroutines.Dispatchers

class JokeViewModel(
    private val repository: JokeRepository
) : ViewModel() {

    fun obterJoke(category: String) = liveData(Dispatchers.IO) {
        val response = repository.obterJoke(category)

        emit(response)
    }
}