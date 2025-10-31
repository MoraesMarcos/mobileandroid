package com.example.atividade_a5.coroutinesdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.atividade_a5.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val handler = CoroutineExceptionHandler { _, throwable ->
        runOnUiThread {
            binding.tvStatus.text = "Erro: ${throwable.message}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFetch.setOnClickListener { simulateFetch() }
        binding.btnCompute.setOnClickListener { simulateHeavyComputation() }
    }

    private fun simulateFetch() {
        lifecycleScope.launch(handler) {
            binding.tvStatus.text = "Buscando dados..."
            binding.tvResult.text = ""

            val result: List<String> = withContext(Dispatchers.IO) {
                fakeRemoteFetch()
            }

            binding.tvStatus.text = "Dados recebidos!"
            binding.tvResult.text = result.joinToString(prefix = "Itens:\n", separator = "\n")
        }
    }

    private fun simulateHeavyComputation() {
        lifecycleScope.launch(handler) {
            binding.tvStatus.text = "Calculando números aleatórios pesados..."
            binding.tvResult.text = ""

            val deferredA = async(Dispatchers.Default) {
                heavyComputeRandomSum(seed = 42, iterations = 1_500_000)
            }
            val deferredB = async(Dispatchers.Default) {
                heavyComputeRandomSum(seed = 99, iterations = 1_500_000)
            }

            val a = deferredA.await()
            val b = deferredB.await()

            binding.tvStatus.text = "Cálculo concluído!"
            binding.tvResult.text = "Soma A = $a\nSoma B = $b\nTotal = ${a + b}"
        }
    }

    private suspend fun fakeRemoteFetch(): List<String> {
        delay(1200) // simula latência
        val size = Random.nextInt(3, 8)
        return List(size) { idx -> "Item #${idx + 1} - id=${Random.nextInt(1000, 9999)}" }
    }

    private suspend fun heavyComputeRandomSum(seed: Int, iterations: Int): Long {
        val rng = Random(seed)
        var sum = 0L
        val ctx = currentCoroutineContext()

        for (i in 0 until iterations) {
            if (!ctx.isActive) return sum
            sum += rng.nextInt(0, 1000)

            if (i % 100_000 == 0 && i != 0) {
                delay(1)
            }
        }
        return sum
    }
}

/*
EXPLICAÇÃO

O que esse código está fazendo?

1. lifecycleScope.launch(handler)
   - Roda coroutines ligadas ao ciclo de vida da Activity.
   - Se a Activity fechar, as coroutines são canceladas automaticamente.
   - handler captura exceções e mostra na tela em vez de crashar o app.

2. simulateFetch()
   - Mostra na UI "Buscando dados..."
   - Usa withContext(Dispatchers.IO) para rodar fakeRemoteFetch() em uma thread de I/O.
   - fakeRemoteFetch() é suspend e usa delay() para simular rede.
   - Quando termina, volta pra thread principal e atualiza a interface com o resultado.

   -> Ou seja: simula chamada de API sem travar a tela.

3. simulateHeavyComputation()
   - Mostra "Calculando..."
   - Cria duas coroutines async(Dispatchers.Default), uma para cada soma pesada.
     * Dispatchers.Default é otimizado pra CPU-bound.
   - await() pega o resultado de cada uma.
   - Atualiza a UI com as somas.

   -> Ou seja: faz processamento pesado em paralelo e depois junta os resultados, mantendo a UI viva.

4. heavyComputeRandomSum()
   - Faz um loop gigante somando números aleatórios.
   - Checa currentCoroutineContext().isActive:
        se a Activity for destruída e a coroutine cancelada, ele para educadamente.
   - Usa delay(1) a cada 100.000 iterações:
        isso "cede a vez", evitando 100% de uso de CPU contínuo e evitando travar a thread que está rodando essa coroutine.

5. Por que usar Dispatchers?
   - Dispatchers.IO: para tarefas de disco/rede que podem bloquear.
   - Dispatchers.Default: para tarefas de CPU pesado.
   - Thread principal (Main): só atualiza UI.
   Separar isso evita ANR (Application Not Responding).

6. async/await vs launch
   - launch: você dispara e não espera retorno.
   - async: você dispara e recebe um Deferred<T>. Depois dá await() pra pegar o valor.
   Aqui usamos async porque precisamos dos resultados das duas somas.

7. delay() em vez de Thread.sleep()
   - delay() não bloqueia a thread de verdade. Ele só suspende a coroutine.
   - Isso mantém o app responsivo e economiza bateria.

POR QUE ISSO RESOLVE UM PROBLEMA?

Esse padrão resolve dois problemas clássicos de apps Android:
(1) Sem coroutines, se você fizesse rede ou cálculo pesado direto no botão, você estaria rodando na Main Thread e a interface travaria, podendo gerar ANR.
(2) Mesmo que você jogasse isso pra uma thread manual (Thread/Runnable), você teria que cuidar sozinho de cancelamento quando a Activity fecha e de voltar pra Main Thread pra atualizar a UI, o que é chato e propenso a erro.
Com coroutines, o código fica sequencial e limpo, roda em contextos certos (IO / CPU), cancela sozinho com o ciclo de vida e atualiza a UI com segurança — então o app continua fluido mesmo fazendo tarefas demoradas.
*/
