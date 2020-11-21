package com.example.aula35.listagem.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aula35.R

class CategoriasAdapter(
    private val categorias: List<String>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.categoria_list_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = categorias[position]

        holder.bind(item)

        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = categorias.size
}