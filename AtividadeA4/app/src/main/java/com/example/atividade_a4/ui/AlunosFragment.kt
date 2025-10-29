package com.example.atividade_a4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atividade_a4.adapter.AlunoAdapter
import com.example.atividade_a4.databinding.FragmentAlunosBinding
import com.example.atividade_a4.model.Aluno

class AlunosFragment : Fragment() {

    private var _binding: FragmentAlunosBinding? = null
    private val binding get() = _binding!!

    private val listaFake = listOf(
        Aluno("Marcos Vinícius", "Branca"),
        Aluno("Ana Paula", "Azul"),
        Aluno("Lucas Andrade", "Roxa")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlunosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AlunoAdapter(listaFake) { aluno ->
            val action =
                AlunosFragmentDirections
                    .actionAlunosFragmentToDetalheAlunoFragment(
                        nome = aluno.nome,
                        faixa = aluno.faixa
                    )

            findNavController().navigate(action)
        }

        binding.recyclerAlunos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerAlunos.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
