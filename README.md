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


O repositório está organizado com duas atividades principais, estruturadas da seguinte forma:

### 1. Atividade A2

* **Localização:** Todo o código-fonte e arquivos de projeto da Atividade A2 estão contidos dentro da pasta `/mobileandroid`.
* **Como Executar:** Para visualizar ou executar este projeto, abra a pasta `/mobileandroid` diretamente no Android Studio.

### 2. Atividade A3

* **Localização:** O projeto da Atividade A3 está localizado na **raiz** deste repositório.
* **Arquivos Principais:**
    * `/app`: Contém o módulo principal e o código-fonte da aplicação A3.
    * `/gradle`, `build.gradle.kts`, `settings.gradle.kts`, etc.: São os arquivos de configuração do Gradle para o projeto A3.
    * `Atividade-3.zip`: Um arquivo compactado que provavelmente contém recursos, enunciados ou a entrega final da Atividade A3.
* **Como Executar:** Para visualizar ou executar este projeto, abra a pasta **raiz** (`atividades`) diretamente no Android Studio.
