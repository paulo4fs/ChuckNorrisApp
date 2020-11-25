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
import androidx.navigation.fragment.findNavController
import com.example.aula35.R
import com.example.aula35.piada.model.JokeModel
import com.example.aula35.piada.repository.JokeRepository
import com.example.aula35.piada.viewmodel.JokeViewModel
import com.example.aula35.piada.viewmodel.JokeViewModelFactory
import com.squareup.picasso.Picasso

class JokeFragment : Fragment() {
    private lateinit var _jokeViewModel: JokeViewModel
    private lateinit var _view: View
    private lateinit var _category: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        _category = arguments?.getString("category").toString()

        _view = view

        _jokeViewModel = ViewModelProvider(
            this,
            JokeViewModelFactory(JokeRepository())
        ).get(JokeViewModel::class.java)

        _jokeViewModel.obterJoke(_category).observe(viewLifecycleOwner, {
            exibirResultados(it)
        })

        backNav()
    }

    private fun exibirResultados(joke: JokeModel?) {
        val imageIv = _view.findViewById<ImageView>(R.id.ivImage)
        val categoryTv = _view.findViewById<TextView>(R.id.tvCategoria)
        val jokeTv = _view.findViewById<TextView>(R.id.tvJoke)

        categoryTv.text = joke?.categories?.get(0)?.capitalize()
        jokeTv.text = joke?.value
        Picasso.get().load(joke?.image).into(imageIv)
    }

    private fun backNav() {
        _view.findViewById<ImageButton>(R.id.ibVoltar).setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_jokeFragment_to_categoriasFragment)
        }
    }
}