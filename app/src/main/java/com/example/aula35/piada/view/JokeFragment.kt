package com.example.aula35.piada.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.aula35.main.view.IJoke
import com.example.aula35.R
import com.example.aula35.piada.model.JokeModel
import com.example.aula35.piada.repository.JokeRepository
import com.example.aula35.piada.viewmodel.JokeViewModel
import com.example.aula35.piada.viewmodel.JokeViewModelFactory
import com.squareup.picasso.Picasso

class JokeFragment : Fragment() {
    private lateinit var _viewModel: JokeViewModel
    private lateinit var _view: View
    private lateinit var category: String
    private lateinit var iJoke: IJoke

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iJoke = context as IJoke
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        _viewModel = ViewModelProvider(
            this,
            JokeViewModelFactory(JokeRepository())
        ).get(JokeViewModel::class.java)

        category

        _viewModel.obterJoke(category).observe(viewLifecycleOwner, {
            exibirResultados(it)
        })

        backNav()
    }

    fun changeCategory(newCategory: String) {
        category = newCategory
    }

    private fun exibirResultados(joke: JokeModel?) {
        joke?.let { joke }

        val imageIv = _view.findViewById<ImageView>(R.id.ivImage)
        val categoryTv = _view.findViewById<TextView>(R.id.tvCategoria)
        val jokeTv = _view.findViewById<TextView>(R.id.tvJoke)

        categoryTv.text = joke?.categories?.get(0)
        jokeTv.text = joke?.value
        Picasso.get().load(joke?.image).into(imageIv)
    }

    private fun backNav() {
        _view.findViewById<ImageButton>(R.id.ibVoltar).setOnClickListener {
            iJoke.backPressed()
        }
    }
}