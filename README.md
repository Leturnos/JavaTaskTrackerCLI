# 📖 Sobre o Projeto

**Task Tracker CLI** é um gerenciador de tarefas simples e eficiente, operado via linha de comando. Este projeto foi desenvolvido como um desafio de programação para praticar habilidades backend da plataforma roadmap.sh, incluindo manipulação de arquivos (I/O), tratamento de entradas do usuário e a criação de uma aplicação de console interativa.

A aplicação permite que o usuário adicione, atualize, delete e liste suas tarefas, salvando todos os dados localmente em um arquivo de texto com formato similar a JSON.

---

## ✨ Funcionalidades

- **Adicionar, Atualizar e Deletar Tarefas:** Gerenciamento completo do ciclo de vida das tarefas.
- **Alterar Status:** Marque tarefas como `TODO`, `IN_PROGRESS` ou `DONE`.
- **Listagem Completa:** Visualize todas as tarefas cadastradas.
- **Filtros de Listagem:** Liste tarefas por status específico (concluídas, não concluídas, em progresso).
- **Persistência de Dados:** As tarefas são salvas no arquivo `TaskTracker.txt` para que nenhuma informação seja perdida ao fechar o programa.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **APIs Nativas:**
  - `java.io` para manipulação de arquivos (leitura e escrita).
  - `java.time` para gerenciamento de datas de criação e atualização.
  - `java.util` para estruturas de dados como `List` e `Scanner`.

*Este projeto foi construído **sem o uso de bibliotecas ou frameworks externos**, como uma restrição do desafio.*

---

## 🚀 Como Executar

Como o projeto é feito em Java puro, você pode compilá-lo e executá-lo diretamente pelo terminal.

**1. Pré-requisitos:**
   - Ter o [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) instalado.

**2. Compilação:**
   - Navegue até o diretório raiz do projeto onde os arquivos `.java` estão localizados.
   - Execute o comando abaixo para compilar todos os arquivos:
     ```bash
     javac Main.java Task.java Status.java
     ```

**3. Execução:**
   - Após a compilação, execute o programa com o seguinte comando:
     ```bash
     java Main
     ```

**4. Interagindo com o Programa:**
   - Ao executar, um menu interativo será exibido no console.
   - Basta digitar o número da opção desejada e pressionar `Enter` para realizar as operações.

   ```
   ======================================
   1- Add, Update, and Delete tasks
   2- Mark a task as in progress or done
   3- List all tasks
   4- List all tasks that are done
   5- List all tasks that are not done
   6- List all tasks that are in progress
   7- exit
   ======================================
   ```

---

## 📁 Estrutura do Projeto

```
src/
  ├── Main.java        # Classe principal, contém a lógica da interface do usuário (menu)
  ├── Task.java        # Classe que representa o modelo de uma Tarefa
  ├── Status.java      # Enum que define os status possíveis de uma tarefa
└── TaskTracker.txt    # Arquivo de texto onde os dados das tarefas são armazenados
```

---

## 📝 Sobre o Desafio

Este projeto foi uma resposta a um desafio de programação que tinha como objetivo construir um CLI de tarefas com as seguintes restrições principais:
- Não utilizar bibliotecas ou frameworks externos.
- Utilizar um arquivo no formato JSON para persistência.
- Implementar funcionalidades de CRUD e listagem por status.

A abordagem de um menu interativo foi escolhida para a interface do usuário.
