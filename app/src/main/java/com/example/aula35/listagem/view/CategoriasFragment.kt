package com.example.aula35.listagem.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula35.R
import com.example.aula35.listagem.repository.CategoriaRepository
import com.example.aula35.listagem.viewmodel.CategoriasViewModel
import com.example.aula35.listagem.viewmodel.CategoriasViewModelFactory

class CategoriasFragment : Fragment() {
    private lateinit var _categoriasViewModel: CategoriasViewModel
    private lateinit var _view: View
    private lateinit var _lista: RecyclerView

    private lateinit var _categoriasAdapter: CategoriasAdapter

    private var _listaDeCategorias = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categorias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        _lista = view.findViewById(R.id.recyclerview)

        val manager = LinearLayoutManager(view.context)

        _listaDeCategorias = mutableListOf()
        _categoriasAdapter = CategoriasAdapter(_listaDeCategorias) {
            Toast.makeText(view.context, "Category $it", Toast.LENGTH_LONG).show()
            navigateToJoke(it)
        }

        _lista.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = _categoriasAdapter
        }

        _categoriasViewModel = ViewModelProvider(
            this,
            CategoriasViewModelFactory(CategoriaRepository())
        ).get(CategoriasViewModel::class.java)

        _categoriasViewModel.obterLista().observe(viewLifecycleOwner, {
            _listaDeCategorias.addAll(it)
            _categoriasAdapter.notifyDataSetChanged()
        })
    }

    private fun navigateToJoke(categoryString: String) {
        val bundle = bundleOf("category" to categoryString)
        val navController = findNavController()
        navController.navigate(R.id.action_categoriasFragment_to_jokeFragment, bundle)
    }
}