package com.example.aula35.listagem.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula35.main.view.IJoke
import com.example.aula35.R
import com.example.aula35.listagem.repository.CategoriaRepository
import com.example.aula35.listagem.viewmodel.CategoriasViewModel
import com.example.aula35.listagem.viewmodel.CategoriasViewModelFactory

class CategoriasFragment : Fragment() {
    private lateinit var _viewModel: CategoriasViewModel
    private lateinit var _view: View
    private lateinit var iJoke: IJoke
    lateinit var lista: RecyclerView

    private lateinit var _categoriasAdapter: CategoriasAdapter

    private var _listaDeCategorias = mutableListOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iJoke = context as IJoke
    }

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

        lista = view.findViewById<RecyclerView>(R.id.recyclerview)

        val manager = LinearLayoutManager(view.context)

        _listaDeCategorias = mutableListOf<String>()
        _categoriasAdapter = CategoriasAdapter(_listaDeCategorias) {
            Toast.makeText(view.context, "Category $it", Toast.LENGTH_LONG).show()
            iJoke.categoryHandler(it)
        }

        lista.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = _categoriasAdapter
        }

        _viewModel = ViewModelProvider(
            this,
            CategoriasViewModelFactory(CategoriaRepository())
        ).get(CategoriasViewModel::class.java)

        _viewModel.obterLista().observe(viewLifecycleOwner, {
            _listaDeCategorias.addAll(it)

            _categoriasAdapter.notifyDataSetChanged()
        })
    }
}