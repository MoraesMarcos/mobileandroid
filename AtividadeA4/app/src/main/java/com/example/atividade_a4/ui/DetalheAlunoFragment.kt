package com.example.atividade_a4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.atividade_a4.databinding.FragmentDetalheAlunoBinding

class DetalheAlunoFragment : Fragment() {

    private var _binding: FragmentDetalheAlunoBinding? = null
    private val binding get() = _binding!!

    // Safe Args: recebe os dados enviados
    private val args: DetalheAlunoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalheAlunoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Acessa os dados enviados pelo Safe Args
        binding.textNomeAluno.text = args.nome
        binding.textFaixaAluno.text = "Faixa: ${args.faixa}"
        binding.textStatusAluno.text = "Aluno ativo na turma da noite ✅"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
