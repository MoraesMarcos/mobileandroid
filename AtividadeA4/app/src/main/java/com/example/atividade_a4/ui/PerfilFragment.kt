package com.example.atividade_a4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.atividade_a4.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textNomeProfessor.text = "Professor: Sensei Leonardo Ferreira"
        binding.textGraduacao.text = "Faixa Preta 2º Grau"
        binding.textContato.text = "Contato: (81) 99999-9999"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}