package com.example.atividade_a4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atividade_a4.databinding.ItemAlunoBinding
import com.example.atividade_a4.model.Aluno

class AlunoAdapter(
    private val alunos: List<Aluno>,
    private val onClick: (Aluno) -> Unit
) : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {

    inner class AlunoViewHolder(val binding: ItemAlunoBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val binding = ItemAlunoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AlunoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        val aluno = alunos[position]
        holder.binding.textNome.text = aluno.nome
        holder.binding.textFaixa.text = "Faixa: ${aluno.faixa}"

        holder.binding.root.setOnClickListener {
            onClick(aluno)
        }
    }

    override fun getItemCount(): Int = alunos.size
}
