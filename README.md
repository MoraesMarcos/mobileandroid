# 📱 Projeto Android – Atividades A2 e A3  

## 👨‍💻 Autor  
**Marcos Vinícius Vitor de Moraes**  
📍 Instituto Federal de Pernambuco – Campus Palmares  
💻 GitHub: [@MoraesMarcos](https://github.com/MoraesMarcos)

---

## 🧩 Descrição Geral  
Este repositório reúne as **Atividades A2 e A3** desenvolvidas na disciplina de **Desenvolvimento Mobile (Android)**.  
O objetivo é praticar o uso do **Kotlin**, **RecyclerView/ListAdapter**, **Jetpack Compose** e o padrão **MVVM**, criando aplicações simples e funcionais com gerenciamento dinâmico de listas.

---

## 🧠 Atividade A2 — *Lista de Tarefas com MVVM e Compose*  

### 🎯 Objetivo  
Implementar uma aplicação Android usando **Jetpack Compose** e **ViewModel**, explorando o uso de `UiState` e `StateFlow` para construir interfaces **reativas e modernas**.

### 🧱 Principais Componentes  
- `UiState.kt`: classe selada que define os estados da interface (`Loading`, `Success`, `Error`).  
- `TasksViewModel.kt`: gerencia o fluxo de dados e atualiza o estado da UI.  
- `TasksScreen.kt`: Composable principal que exibe a tela conforme o estado atual.  
- `MainActivity.kt`: inicializa o tema e a tela principal via Compose.  
- Tema personalizado em `ProgramacaoTheme`.  

### ⚙️ Funcionalidades  
✅ Exibe lista de tarefas com status (“Pendente” / “Concluída”).  
✅ Permite recarregar a lista com o botão **Atualizar**.  
✅ Mostra tela de erro simulada com o botão **Tentar novamente**.  
✅ Usa `StateFlow` para atualizar a interface em tempo real.  
✅ Interface construída totalmente com **Jetpack Compose e Material 3**.  

### 🧩 Arquitetura Utilizada  
**MVVM (Model-View-ViewModel)** com fluxo unidirecional de dados:  
`ViewModel` → emite `UiState` → observado pela `TasksScreen`.

---

### 🖼️ Exemplo Visual  

| Lista de Tarefas | Erro de Carregamento |
|------------------|----------------------|
| <img width="320" src="https://github.com/user-attachments/assets/e38fa153-8245-400c-a095-600c88815d14" alt="Tela Lista"> | <img width="403" height="835" alt="A2_1" src="https://github.com/user-attachments/assets/9b6de9c6-73ba-46c2-a79f-d79aa1096c56" alt="Tela Erro"> |

---

## 🧰 Atividade A3 — *Gerenciamento de Tarefas com RecyclerView e ListAdapter*  

### 🎯 Objetivo  
Criar um app Android usando **RecyclerView + ListAdapter + DiffUtil** para gerenciar uma lista de tarefas com ações de adicionar, editar, concluir e remover.

### 🧱 Principais Componentes  
- `Task.kt`:  
  ```kotlin
  data class Task(
      val id: Long = System.currentTimeMillis(),
      var title: String,
      var isCompleted: Boolean = false
  )

### 🧱 Principais Componentes  

- **`TaskAdapter.kt`** — herda de `ListAdapter<Task, TaskViewHolder>` e implementa `DiffUtil.ItemCallback`.  
- **`TaskDiffCallback.kt`** — compara itens antigos e novos para atualizar apenas o que mudou.  
- **`MainActivity.kt`** — controla a lista mutável de tarefas e interage com o adapter.  

#### 🧩 Layouts XML  
- **`activity_main.xml`** → campo de texto + botão **“Adicionar”** + RecyclerView.  
- **`item_task.xml`** → layout de cada tarefa (**CheckBox**, **TextView**, **ImageButton**).  

---

### ⚙️ Funcionalidades  

✅ Adicionar novas tarefas com título.  
✅ Marcar como concluída via **CheckBox**.  
✅ Editar o título em um **AlertDialog** personalizado.  
✅ Remover tarefa com o botão de **lixeira**.  
✅ Atualização eficiente via `ListAdapter.submitList(taskList.toList())`.  
✅ Uso de **DiffUtil** para detectar alterações automaticamente.  

---

### 📋 Fluxo de Funcionamento  

1. O usuário digita o título da tarefa no `EditText`.  
2. Ao clicar em **Adicionar**, é criada uma `Task` com `System.currentTimeMillis()` como ID.  
3. O adapter recebe a lista atualizada (`submitList(taskList.toList())`).  
4. O usuário pode:  
   - ✅ **Marcar/desmarcar** tarefas como concluídas.  
   - ✏️ **Editar** uma tarefa com diálogo modal.  
   - 🗑️ **Remover** com o botão vermelho de lixeira.  

---

### 🧠 Estrutura Simplificada do Adapter  

```kotlin
class TaskAdapter(
    val onTaskClicked: (Task) -> Unit,
    val onDeleteClicked: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback) {
    // ViewHolder e listeners implementados conforme requisitos
}
```

### 🖼️ Exemplo Visual da Atividade A3  


| Adicionar Tarefa | Editar Tarefa | Lista Atualizada | Delete |
|------------------|---------------|------------------|--------|
| <img width="371" height="780" alt="A3" src="https://github.com/user-attachments/assets/96536fc4-8098-4448-ba35-63c0856a98f4" alt="Adicionar"> | <img width="347" height="756" alt="A3_1" src="https://github.com/user-attachments/assets/c1e76ffa-9544-4e7a-a267-47b1497ebb8d" alt="Editar"> | <img width="356" height="761" alt="A3_3" src="https://github.com/user-attachments/assets/4e6d5158-d838-4e60-824b-6d7325ceb0c8" alt="Lista Atualizada"> | <img width="362" height="767" alt="A3_2" src="https://github.com/user-attachments/assets/049a1a90-2172-412f-9044-32c1c2426166" alt="Removido"> |

---

## 🧭 Atividade A4 — Navegação com Fragments e Safe Args

### 🎯 Objetivo  
Implementar um aplicativo Android com **Fragments** e **Navigation Component**, praticando a **navegação entre telas** e o uso de **Safe Args** para envio de dados com segurança.

---

### 🧱 Estrutura  
- **`HomeFragment.kt`** → tela inicial com botão de navegação para o login.  
- **`LoginFragment.kt`** → tela de login simulada com campos de usuário e senha.  
- **`fragment_home.xml`** e **`fragment_login.xml`** → layouts individuais.  
- **`nav_graph.xml`** → define o fluxo de navegação entre os fragments.  

---

### ⚙️ Funcionalidades  
✅ Botões e textos configurados com **ViewBinding**  
✅ Navegação feita via `findNavController().navigate()`  
✅ Envio de dados entre fragments usando **Safe Args**  
✅ Interface intuitiva com mensagens de boas-vindas e botões de ação  

---

### 🖼️ Exemplo Visual  

| HomeFragment | LoginFragment |
|---------------|----------------|
| <img width="300" src="https://github.com/user-attachments/assets/9f5f49b5-23a0-49ef-8c02-95dbbd1e3383" alt="Home"> | <img width="300" src="https://github.com/user-attachments/assets/7e7779a5-9f59-43b4-9754-19325c0a90f7" alt="Login"> |

---

## ⚡ Atividade A5 — Coroutines: Do Básico ao Avançado  

### 🎯 Objetivo  
Criar um aplicativo que utilize **Kotlin Coroutines** para executar **tarefas assíncronas** de forma segura, eficiente e sem bloquear a interface do usuário (**UI Thread**).

---

### 🧱 Estrutura do Código  
- **`MainActivity.kt`** → contém os botões e o tratamento das coroutines.  
- **`simulateFetch()`** → simula busca de dados (*I/O-bound*) com `Dispatchers.IO`.  
- **`simulateHeavyComputation()`** → executa cálculo pesado (*CPU-bound*) com `Dispatchers.Default`.  
- **`fakeRemoteFetch()`** → função `suspend` que usa `delay()` para simular latência.  
- **`heavyComputeRandomSum()`** → função `suspend` que realiza um loop intenso e verifica cancelamento com `isActive`.  

---

### ⚙️ Funcionalidades  
✅ Dois botões principais:  
- **Buscar Dados (Simulado)** → retorna uma lista de itens após uma “latência” simulada.  
- **Cálculo Demorado** → executa duas operações pesadas em paralelo usando `async/await`.  

✅ Atualiza o **status** e **resultado** diretamente na tela (`TextView`).  
✅ Usa `lifecycleScope`, `Dispatchers`, `withContext`, `delay()` e `isActive`.  
✅ Exceções tratadas com `CoroutineExceptionHandler`.  

---

### 💬 Comentário Explicativo no Código

```kotlin
/* As coroutines permitem executar operações demoradas sem travar a interface.
   O código usa lifecycleScope.launch(), async/await, funções suspend e delay()
   para simular busca de dados e cálculos pesados de CPU.
   Assim, o app permanece fluido e responsivo, mesmo realizando tarefas paralelas. */
```

### 🖼️ Exemplo Visual  

| Tela Inicial | Buscando Dados | Dados Recebidos | Calculando | Cálculo Concluído |
|---------------|----------------|------------------|-------------|-------------------|
| <img width="250" src="https://github.com/user-attachments/assets/ecbae83f-4c95-45a6-a4fe-3e86bf2acbcb" /> | <img width="250" src="https://github.com/user-attachments/assets/b014a880-7583-448d-bc6f-20f5a283ad4b" /> | <img width="250" src="https://github.com/user-attachments/assets/c9c6d0a8-d67d-4603-93ab-b6eaa8e856f4" /> | <img width="250" src="https://github.com/user-attachments/assets/1d1971f0-4a43-4a77-a7b2-375ec49e176f" /> | <img width="250" src="https://github.com/user-attachments/assets/863a28ca-436b-4cc4-a558-797733aab4a5" /> |

---

### 📁 Organização do Repositório  

| Pasta | Descrição |
|--------|------------|
| **Atividade2** | Projeto da lista de tarefas com Compose e MVVM |
| **AtividadeA3** | App com RecyclerView e ListAdapter |
| **AtividadeA4** | App com navegação usando Fragments e Safe Args |
| **Atividade_A5** | App demonstrando uso de Kotlin Coroutines |
| **README.md** | Documento de explicação e guia das atividades |
