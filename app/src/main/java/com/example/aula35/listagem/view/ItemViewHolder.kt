package com.example.aula35.listagem.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aula35.R

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val nome = view.findViewById<TextView>(R.id.categoria)

    fun bind(categoriaModel: String) {
        nome.text = categoriaModel
    }
}