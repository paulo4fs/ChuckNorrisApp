package com.example.aula35.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aula35.R
import com.example.aula35.listagem.view.CategoriasFragment
import com.example.aula35.piada.view.JokeFragment

class MainActivity : AppCompatActivity(), IJoke {
    private val _jokeFragment = JokeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, CategoriasFragment())
            .addToBackStack("Category")
            .commit()
    }

    override fun categoryHandler(category: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, _jokeFragment)
            .addToBackStack("Joke")
            .commit()

        _jokeFragment.changeCategory(category.toLowerCase())
    }

    override fun backPressed() {
        supportFragmentManager.popBackStack()
    }
}