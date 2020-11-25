package com.example.aula35.piada.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.aula35.piada.model.JokeModel
import com.example.aula35.piada.repository.JokeRepository
import kotlinx.coroutines.Dispatchers

class JokeViewModel(
    private val repository: JokeRepository
) : ViewModel() {
    private lateinit var _categoria: String
    private lateinit var _joke: JokeModel
    private lateinit var _response: JokeModel

    fun obterJoke(category: String? = null) = liveData(Dispatchers.IO) {
        if (category != null) {
            _categoria = category.toLowerCase()
            _response = repository.obterJoke(_categoria)
            _joke = _response
        }
        emit(_response)
    }
}