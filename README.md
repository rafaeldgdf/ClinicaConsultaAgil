# ClinicaConsultaAgil
Este repositório contém um sistema de gestão de consultas médicas desenvolvido em Java. O sistema foi projetado para automatizar e organizar o gerenciamento de pacientes e consultas em uma clínica médica, proporcionando uma interface de console intuitiva e fácil de usar.


# Sistema de Gestão de Consultas Médicas

## Sumário
1. Introdução
    - 1.1. Descrição Geral do Sistema
    - 1.2. Resolução de Problema
2. Regras de Negócio
    - Horários de Funcionamento da Clínica
    - Especialidades Disponíveis
    - Agendamento de Consultas
    - Cadastro de Pacientes
    - Remoção de Pacientes
    - Listagem de Pacientes e Consultas
    - Cancelamento de Consultas
    - Consulta de Status de Paciente
    - Validação de Entrada
    - Confirmação de Saída
3. Arquitetura do Sistema
    - 3.1. Estrutura dos Pacotes (packages)
    - 3.2. Descrição Geral das Classes
4. Funcionalidades Disponíveis no Sistema
    - 4.1. Cadastro de Pacientes
    - 4.2. Marcação de Consultas
    - 4.3. Listagem de Consultas
    - 4.4. Cancelamento de Consultas
    - 4.5. Listagem de Pacientes
    - 4.6. Remoção de Pacientes
    - 4.7. Consulta de Status de Pacientes
    - 4.8. Sair do Sistema
5. Funcionalidades Extras do Sistema
    - 5.1. Tratamento de Erros
    - 5.2. Funcionalidade "000" para Retornar ao Menu
6. Explicação Detalhada das Classes e Métodos
    - 6.1. DAOGenerico
    - 6.2. PacienteDAO
    - 6.3. MarcacaoConsultaDAO
    - 6.4. Registro
    - 6.5. Paciente
    - 6.6. MarcacaoConsulta
    - 6.7. Clinica
7. Conclusão

## 1. Introdução
### 1.1. Descrição Geral do Sistema
O sistema de gestão de consultas médicas é uma aplicação de console desenvolvida em Java. Ele foi projetado para gerenciar pacientes e suas consultas em uma clínica médica, facilitando o agendamento, listagem e cancelamento de consultas, bem como o gerenciamento de pacientes.

### 1.2. Resolução de problema
Este sistema resolve o problema de gerenciamento manual de consultas e pacientes em clínicas médicas. Ele automatiza o processo de cadastro de pacientes, agendamento de consultas, listagem de consultas e pacientes, cancelamento de consultas e remoção de pacientes, proporcionando uma forma eficiente e organizada de gerenciar essas informações.

## 2. Regras de Negócio
### Horários de Funcionamento da Clínica:
- A clínica funciona de segunda a sexta-feira, das 08:00 às 18:00.
- As consultas devem ser agendadas dentro deste horário.

### Especialidades Disponíveis:
- Cada dia da semana tem duas especialidades específicas disponíveis para consultas.
  - Segunda-feira: Clínica Geral e Cardiologia
  - Terça-feira: Dermatologia e Neurologia
  - Quarta-feira: Ortopedia e Pediatria
  - Quinta-feira: Psiquiatria e Ginecologia
  - Sexta-feira: Psicologia e Endocrinologia

### Agendamento de Consultas:
- Consultas podem ser agendadas em intervalos de uma hora, começando das 08:00 até as 17:00 (último horário de agendamento).
- Não é permitido agendar consultas para finais de semana (sábado e domingo).
- A data da consulta deve ser dentro do intervalo de um ano a partir da data atual.
- O sistema verifica se o horário escolhido está disponível para a especialidade desejada e se não há outras consultas agendadas no mesmo horário e especialidade.

### Cadastro de Pacientes:
- Pacientes devem ser cadastrados com nome e telefone.
- Nomes devem conter apenas letras e espaços, sem números ou caracteres especiais.
- Telefones devem conter apenas números e ter pelo menos 8 dígitos.
- Cada número de telefone deve ser único no sistema, ou seja, não pode haver dois pacientes com o mesmo número de telefone.

### Remoção de Pacientes:
- Pacientes podem ser removidos do sistema, mas ao fazer isso, todas as consultas associadas ao paciente também serão removidas.
- O sistema solicita confirmação antes de remover um paciente e suas consultas associadas.

### Listagem de Pacientes e Consultas:
- O sistema permite listar todos os pacientes cadastrados e todas as consultas agendadas.
- A listagem de pacientes exibe o nome e telefone de cada paciente.
- A listagem de consultas exibe informações detalhadas sobre cada consulta, incluindo ID, nome do paciente, telefone do paciente, especialidade, data e horário da consulta.

### Cancelamento de Consultas:
- Consultas podem ser canceladas fornecendo o ID da consulta.
- O sistema solicita confirmação antes de cancelar a consulta.

### Consulta de Status de Paciente:
- O sistema permite consultar o status de um paciente fornecendo seu número de telefone.
- Exibe todas as consultas agendadas para o paciente, ou informa se o paciente não tem consultas agendadas.

### Validação de Entrada:
- O sistema valida todas as entradas do usuário, garantindo que sejam fornecidos dados corretos e completos.
- Para a maioria das operações, o sistema permite que o usuário retorne ao menu principal inserindo "000".

### Confirmação de Saída:
- Ao selecionar a opção de sair, o sistema pede confirmação ao usuário para evitar saídas acidentais.

Essas regras garantem que o sistema funcione de maneira eficiente, segura e organizada, proporcionando uma experiência de usuário consistente e confiável para o gerenciamento de pacientes e consultas na clínica médica.

## 3. Arquitetura do Sistema
### 3.1. Estrutura dos Pacotes (packages)
- **bancoDeDados:** Contém classes responsáveis pelo armazenamento e manipulação de dados dos pacientes e consultas.
- **entidades:** Contém classes que representam as entidades principais do sistema, como Paciente e MarcacaoConsulta, bem como o arquivo main (Clinica).

### 3.2. Descrição Geral das Classes
#### Pacote: bancoDeDados
- **DAOGenerico:** Classe genérica para operações básicas de CRUD (Create, Read, Update, Delete) para qualquer tipo de objeto.
- **MarcacaoConsultaDAO:** Extensão de DAOGenerico específica para a classe MarcacaoConsulta, gerencia as operações CRUD para as marcações de consulta.
- **PacienteDAO:** Extensão de DAOGenerico específica para a classe Paciente, gerencia as operações CRUD para os pacientes.
- **Registro:** Classe base para MarcacaoConsulta, fornece uma interface para obtenção do ID único.
  - Observação: DAO significa Data Access Objects, em tradução livre, Objeto de Acesso a Dados.

#### Pacote: entidades
- **Paciente:** Representa um paciente da clínica.
- **MarcacaoConsulta:** Representa uma marcação de consulta.
- **Clinica:** Classe principal que executa o sistema de gestão de consultas.

### 3.3 Descrição Geral dos Métodos
#### bancoDeDados
- **DAOGenerico**
  - **adicionar(T objeto):** Adiciona um objeto ao armazenamento.
  - **remover(T objeto):** Remove um objeto do armazenamento.
  - **listar():** Lista todos os objetos do armazenamento.
  - **buscar():** Busca um objeto específico no armazenamento (implementação específica em subclasses).

- **MarcacaoConsultaDAO**
  - **salvar():** Salva as marcações de consultas em um arquivo.
  - **carregar():** Carrega as marcações de consultas de um arquivo.
  - **converterLinhaParaConsulta():** Converte uma linha de texto em um objeto MarcacaoConsulta.

- **PacienteDAO**
  - **salvar():** Salva as marcações de consultas em um arquivo.
  - **carregar():** Carrega as marcações de consultas de um arquivo.
  - **converterLinhaParaPaciente():** Converte uma linha de texto em um objeto Paciente.
- **Registro**
  - **getIdUnico():** Método abstrato para obter o ID único de um registro.

#### entidades
- **MarcacaoConsulta**
  - **getIdUnico():** Retorna o ID único da marcação de consulta.
  - **toString():** Retorna uma representação em string da marcação de consulta.
  - **marcarConsulta():** Realiza o processo de marcação de uma nova consulta para um paciente.
  - **obterEspecialidades():** Obtém as especialidades disponíveis para um dia específico da semana.
  - **escolherEspecialidade():** Permite ao usuário escolher uma especialidade disponível para o dia selecionado.
  - **listarConsultas():** Lista todas as consultas agendadas.
  - **cancelarConsulta():** Cancela uma consulta agendada com base no ID fornecido.
  - **promptEnterKey():** Solicita ao usuário pressionar Enter para continuar.

- **Paciente**
  - **cadastrarPaciente():** Realiza o cadastro de um novo paciente.
  - **listarPacientes():** Lista todos os pacientes cadastrados.
  - **removerPaciente():** Remove um paciente cadastrado com base no telefone fornecido.
  - **consultarStatusPaciente():** Consulta o status de um paciente específico e lista suas consultas.
  - **getIdUnico():** Retorna o ID único do paciente (número de telefone).
  - **toString():** Retorna uma representação em string do paciente.

- **Clinica**
  - **exibirMenu():** Exibe o menu principal e gerencia a navegação do usuário pelas opções disponíveis.
  - **validarEntrada():** Valida a entrada do usuário para garantir que seja um número entre as opções do menu.
  - **promptEnterKey():** Solicita ao usuário pressionar Enter para continuar.
  - **confirmarSaida():** Solicita confirmação do usuário antes de sair do sistema.
  - **exibirRegraDeNegocios():** Exibe as regras de negócios e funcionamento da clínica.

## 4. Funcionalidades Disponíveis no Sistema
### 4.1. Cadastro de Pacientes
O sistema permite o cadastro de novos pacientes na clínica. Ao cadastrar um paciente, são solicitados o nome e o telefone, garantindo que não haja duplicidade de telefones e que o nome não contenha números.

**Passos para cadastrar um paciente:**
1. Selecionar a opção "1. Cadastrar paciente" no menu principal através do dígito numérico do teclado 1.
2. Informar o nome do paciente (somente letras e espaços são permitidos).
3. Informar o telefone do paciente (somente números, mínimo de 8 dígitos).
4. Mensagem de confirmação: "Paciente cadastrado com sucesso."

### 4.2. Marcação de Consultas
O sistema permite marcar consultas para pacientes cadastrados, selecionando uma data, hora e especialidade médica disponível para o dia da semana escolhido.

**Passos para marcar uma consulta:**
1. Selecionar a opção "2. Marcar consulta" no menu principal através do dígito numérico do teclado 2.
2. Informar o telefone do paciente para verificar se está cadastrado.
3. Informar a data da consulta no formato "dd/MM/yyyy".
4. Informar a hora da consulta no formato "HH:MM" (apenas horários cheios são permitidos, como 08:00, 09:00, etc).
5. Escolher a especialidade disponível para o dia da semana selecionado.
6. Mensagem de confirmação: "[Nome do paciente], consulta agendada com sucesso! Você tem um horário reservado das [Hora] às [Hora+1] para especialidade [Especialidade] em [Data] ([Dia da semana])."

### 4.3. Listagem de Consultas
O sistema permite listar todas as consultas agendadas, mostrando detalhes como o ID da marcação, nome do paciente, telefone, especialidade, data e horário.

**Passos para listar as consultas:**
1. Selecionar a opção "3. Listar consultas" no menu principal através do dígito numérico do teclado 3.

**Exemplo de listagem:**
Consultas agendadas:
MarcacaoConsulta{ID_MARCACAO=1, NOME:'Rafael Vitor', TELEFONE:'81992771533', ESPECIALIDADE='Clínica Geral', DATA=2024-06-24, HORÁRIO=10:00}

### 4.4. Cancelamento de Consultas
O sistema permite cancelar uma consulta agendada, removendo-a do sistema.

**Passos para cancelar uma consulta:**
1. Selecionar a opção "4. Cancelar consulta" no menu principal através do dígito numérico do teclado 4.
2. Informar o ID da marcação a ser cancelada.
3. Confirmar o cancelamento da consulta.
4. Mensagem de confirmação: "Consulta cancelada com sucesso."

### 4.5. Listagem de Pacientes
O sistema permite listar todos os pacientes cadastrados, mostrando o nome e telefone de cada um.

**Passos para listar os pacientes:**
1. Selecionar a opção "5. Listar pacientes" no menu principal através do dígito numérico do teclado 5.

**Exemplo de listagem:**
Pacientes cadastrados:
Nome: Rafael Vitor, Telefone: 81992771533
Nome: João Lucas, Telefone: 8199556644


### 4.6. Remoção de Pacientes
O sistema permite remover um paciente cadastrado e todas as suas consultas associadas.

**Passos para remover um paciente:**
1. Selecionar a opção "6. Remover paciente" no menu principal através do dígito numérico do teclado 6.
2. Informar o telefone do paciente a ser removido.
3. Confirmar a remoção do paciente e de todas as suas consultas.
4. Mensagem de confirmação: "Paciente e suas consultas removidos com sucesso."

### 4.7. Consulta de Status de Pacientes
O sistema permite consultar o status de um paciente, mostrando todas as consultas agendadas para ele.

**Passos para consultar o status de um paciente:**
1. Selecionar a opção "7. Consultar status de paciente" no menu principal através do dígito numérico do teclado 7.
2. Informar o telefone do paciente para verificar suas consultas agendadas.

**Exemplo de consulta de status:**
Paciente encontrado: Paciente{NOME='Rafael Vitor', TELEFONE='81992771533'}
Consultas marcadas para este paciente:
MarcacaoConsulta{ID_MARCACAO=1, NOME:'Rafael Vitor', TELEFONE:'81992771533', ESPECIALIDADE='Clínica Geral', DATA=2024-06-24, HORÁRIO=10:00}


### 4.8. Sair do Sistema
O sistema permite ao usuário sair do programa com uma confirmação para evitar saídas acidentais.

**Passos para sair do sistema:**
1. Selecionar a opção "8. Sair" no menu principal através do dígito do teclado numérico 8.
2. Confirmar a saída do sistema.
3. Mensagem de confirmação: "Tem certeza que deseja sair? (s/n):"

## 5. Funcionalidades Extras do Sistema
O sistema de gestão de consultas médicas possui algumas funcionalidades extras para melhorar a experiência do usuário e garantir a robustez da aplicação. Estas funcionalidades incluem tratamentos de erros e uma funcionalidade específica para retornar ao menu principal.

### 5.1 Tratamentos de Erros
O sistema foi projetado para lidar com diferentes tipos de erros e fornecer feedback apropriado aos usuários. Aqui estão todos os tratamentos de erros implementados:

#### Validação de Entrada no Menu Principal:
- Sempre que o sistema solicita uma escolha no menu principal, ele valida se a entrada é um número entre 1 e 8. Caso contrário, o sistema notifica o usuário sobre a entrada inválida e solicita uma nova entrada.

#### Validação de Nome:
- Durante o cadastro de um paciente, o sistema valida se o nome contém apenas letras e espaços. Se o nome contiver números ou caracteres especiais, o sistema notifica o usuário sobre a entrada inválida e solicita a inserção de um nome válido.

#### Validação de Telefone:
- Durante o cadastro de um paciente, o sistema valida se o telefone contém pelo menos 8 dígitos e apenas números. Se o telefone não atender a esses critérios, o sistema notifica o usuário sobre a entrada inválida e solicita a inserção de um telefone válido.
- Durante a marcação de consulta e remoção de pacientes, o sistema valida se o telefone contém apenas números. Se a entrada for inválida, o sistema notifica o usuário e solicita uma nova entrada.

#### Validação de Data:
- Durante a marcação de uma consulta, o sistema valida se a data fornecida não é no passado, está dentro do intervalo permitido de 1 ano e é um dia útil (segunda a sexta-feira). Se a data não atender a esses critérios, o sistema notifica o usuário e solicita a inserção de uma data válida.
- O sistema também valida se a data está no formato "dd/MM/yyyy". Se o formato for inválido, o sistema notifica o usuário e solicita uma nova entrada.

#### Validação de Hora:
- Durante a marcação de uma consulta, o sistema valida se a hora fornecida não é no passado (para o dia atual), está dentro do horário de funcionamento da clínica (08:00 às 17:00), e é um horário completo (intervalos de uma hora). Se a hora não atender a esses critérios, o sistema notifica o usuário e solicita a inserção de uma hora válida.

#### Verificação de Especialidades Disponíveis:
- Durante a marcação de uma consulta, o sistema verifica se a especialidade escolhida está disponível no dia da semana selecionado. Se não estiver disponível, o sistema notifica o usuário e solicita a escolha de uma especialidade válida.

#### Verificação de Consulta Existente:
- O sistema verifica se já existe uma consulta marcada para a data, horário e especialidade selecionados. Se uma consulta existente for encontrada, o sistema notifica o usuário e solicita a escolha de um novo horário ou data.

#### Cancelamento de Consultas:
- O sistema valida se o ID da marcação fornecido para cancelamento é um número. Se a entrada for inválida, o sistema notifica o usuário e solicita uma nova entrada.

#### Confirmação de Remoção:
- Durante a remoção de pacientes, o sistema solicita a confirmação do usuário. Se a confirmação for diferente de "s" ou "n", o sistema notifica o usuário sobre a entrada inválida e solicita uma nova confirmação.

### 5.2 Funcionalidade "000" para Retornar ao Menu
Para facilitar a navegação do usuário e permitir um retorno rápido ao menu principal, foi implementada a funcionalidade "000". Em qualquer ponto em que o sistema solicita uma entrada, o usuário pode digitar "000" para retornar ao menu principal. Isso é útil em várias situações, como durante o cadastro de pacientes, marcação de consultas ou remoção de registros. Aqui estão alguns exemplos de como essa funcionalidade é aplicada:

- **Cadastro de Pacientes:** Se o usuário deseja cancelar o processo de cadastro a qualquer momento, ele pode digitar "000", e o sistema o levará de volta ao menu principal.
- **Marcação de Consultas:** Durante o processo de marcação de uma consulta, se o usuário digitar "000", o sistema interromperá o processo e retornará ao menu principal.
- **Remoção de Pacientes:** Se o usuário decidir não prosseguir com a remoção de um paciente, ele pode digitar "000" para voltar ao menu principal sem realizar nenhuma ação.

Essa funcionalidade torna o sistema mais amigável e eficiente, permitindo que os usuários corrijam rapidamente suas ações sem precisar reiniciar o programa ou navegar por vários menus.

## 6. Explicação Detalhada das Classes e Métodos
### 6.1 DAOGenerico
**Funcionalidade da Classe no Sistema:**
A classe DAOGenerico é fundamental para o sistema, pois abstrai as operações básicas de persistência e manipulação de dados, fornecendo uma base reutilizável para as classes específicas de dados, como PacienteDAO e MarcacaoConsultaDAO. Ao centralizar essas operações, a classe garante consistência e simplicidade no gerenciamento dos dados do sistema.

**Explicação das Bibliotecas e dos Imports:**
- `import java.util.ArrayList;`: Importa a classe ArrayList que é uma implementação de lista dinâmica, utilizada para armazenar os objetos de forma ordenada e dinâmica.
- `import java.util.List;`: Importa a interface List que define uma coleção ordenada, permitindo acesso e manipulação de elementos com base em seu índice.

**Explicação dos Construtores:**
A classe DAOGenerico não possui construtores explícitos. Em Java, se nenhum construtor for definido, a classe terá um construtor padrão (sem parâmetros) que é fornecido automaticamente pelo compilador. O construtor padrão inicializa a lista como uma nova instância de ArrayList<>, preparando o armazenamento dos objetos do tipo genérico T.

**Importância da Classe para o Sistema:**
A classe DAOGenerico é crucial para a escalabilidade e manutenção do sistema, pois facilita a adição de novas entidades de dados sem duplicação de código. Ela garante que todas as entidades sigam as mesmas regras e procedimentos para CRUD, promovendo boas práticas de desenvolvimento e reduzindo a probabilidade de erros.

**Explicação dos Métodos:**
- `adicionar(T objeto)`: Adiciona um objeto ao armazenamento.
  - Descrição: Este método recebe um objeto do tipo genérico T e o adiciona à lista interna. Após a adição, o método salvar() é chamado para persistir a alteração.
  - Lógica Implementada: Adiciona o objeto à lista e persiste a lista atualizada chamando o método salvar().
  - Importância para o Sistema: Permite a adição de novos registros no sistema de forma consistente e assegura que as alterações sejam salvas persistentemente.
  - Uso no Sistema: Utilizado pelas subclasses PacienteDAO e MarcacaoConsultaDAO para adicionar novos pacientes e consultas, respectivamente.

- `remover(T objeto)`: Remove um objeto do armazenamento.
  - Descrição: Este método recebe um objeto do tipo genérico T e o remove da lista interna. Após a remoção, o método salvar() é chamado para persistir a alteração.
  - Lógica Implementada: Remove o objeto da lista e persiste a lista atualizada chamando o método salvar().
  - Importância para o Sistema: Garante que os registros podem ser removidos corretamente e que a persistência reflete essas alterações, mantendo os dados atualizados.
  - Uso no Sistema: Utilizado pelas subclasses PacienteDAO e MarcacaoConsultaDAO para remover pacientes e consultas, respectivamente.

- `listar()`: Lista todos os objetos do armazenamento.
  - Descrição: Este método retorna uma nova lista contendo todos os objetos armazenados na lista interna.
  - Lógica Implementada: Cria e retorna uma nova instância de ArrayList<> contendo todos os elementos da lista lista.
  - Importância para o Sistema: Facilita a recuperação de todos os registros armazenados, permitindo que o sistema exiba ou manipule os dados conforme necessário.
  - Uso no Sistema: Utilizado pelas subclasses PacienteDAO e MarcacaoConsultaDAO para listar todos os pacientes e consultas, respectivamente.

- `salvar()`: Método protegido que deve ser sobrescrito nas subclasses para implementar a lógica de persistência de dados.
  - Descrição: Este método é uma estrutura para ser implementada nas subclasses específicas, onde a lógica de salvamento será definida.
  - Importância para o Sistema: Garante que os dados dos pacientes sejam carregados do armazenamento persistente, tornando-os disponíveis para o sistema.
  - Uso no Sistema: Chamado no construtor da classe PacienteDAO para inicializar a lista de pacientes.

- `converterLinhaParaPaciente(String linha)`: Este método converte uma linha de texto em um objeto Paciente.
  - Lógica Implementada: Divide a linha de texto nos campos nome e telefone, cria e retorna um novo objeto Paciente com esses valores.
  - Importância para o Sistema: Facilita a conversão de dados textuais do arquivo para objetos Paciente, permitindo a manipulação fácil dos dados no sistema.
  - Uso no Sistema: Utilizado no método carregar() para converter cada linha do arquivo em um objeto Paciente.

**Como a Classe e os Métodos Estão Sendo Usados no Sistema:**
- Classe:
  - PacienteDAO gerencia a persistência e a recuperação dos dados dos pacientes, fornecendo uma interface consistente para operações CRUD.
- Métodos:
  - `salvar()` é utilizado para persistir as alterações feitas na lista de pacientes.
  - `carregar()` é utilizado para carregar os dados dos pacientes do armazenamento persistente ao iniciar o sistema.
  - `converterLinhaParaPaciente(String linha)` é utilizado no método carregar() para converter cada linha do arquivo em um objeto Paciente.

**Resumo da Implementação:**
A classe PacienteDAO herda de DAOGenerico e implementa métodos específicos para gerenciar a persistência de dados dos pacientes. A lógica principal envolve a leitura e a escrita dos dados de pacientes em um arquivo de texto, assegurando que as informações sejam armazenadas e recuperadas de maneira eficiente e consistente. Isso permite que o sistema mantenha um registro atualizado e confiável dos pacientes, essencial para o funcionamento das operações clínicas.

### 6.3. MarcacaoConsultaDAO
**Funcionalidade da Classe no Sistema:**
A classe MarcacaoConsultaDAO é responsável pela gestão dos dados de marcação de consultas, incluindo a persistência e a recuperação dessas informações a partir de um arquivo de texto. Herda de DAOGenerico e implementa métodos específicos para manipulação dos dados de marcações de consultas, garantindo a integridade e a consistência das operações relacionadas às consultas no sistema.

**Explicação das Bibliotecas e dos Imports:**
- `import java.io.BufferedReader;`: Importa a classe BufferedReader para leitura eficiente de texto a partir de um arquivo de entrada.
- `import java.io.BufferedWriter;`: Importa a classe BufferedWriter para escrita eficiente de texto em um arquivo de saída.
- `import java.io.FileReader;`: Importa a classe FileReader para leitura de arquivos de texto.
- `import java.io.FileWriter;`: Importa a classe FileWriter para escrita em arquivos de texto.
- `import java.io.IOException;`: Importa a classe IOException para tratamento de exceções de entrada e saída.
- `import java.io.File;`: Importa a classe File para manipulação de arquivos e diretórios.
- `import java.time.LocalDate;`: Importa a classe LocalDate para manipulação de datas.
- `import java.time.LocalTime;`: Importa a classe LocalTime para manipulação de horários.

**Explicação dos Construtores:**
- `MarcacaoConsultaDAO()`: Construtor padrão que chama o método carregar() para inicializar a lista de marcações de consultas a partir do arquivo de armazenamento.
  - Importância para o Sistema: Garante que os dados das consultas sejam carregados na inicialização do objeto MarcacaoConsultaDAO, assegurando que o sistema tenha acesso imediato aos dados persistentes.

**Importância da Classe para o Sistema:**
A classe MarcacaoConsultaDAO é crucial para o sistema, pois gerencia os dados das marcações de consultas, garantindo que as informações das consultas estejam sempre disponíveis e atualizadas. Isso permite que o sistema mantenha um registro organizado e confiável das consultas agendadas, essencial para a operação eficiente da clínica.

**Explicação dos Métodos:**
- `salvar()`: Este método sobrescreve o método abstrato da classe DAOGenerico e salva a lista de marcações de consultas no arquivo consultas.txt.
  - Lógica Implementada: Abre um BufferedWriter para o arquivo consultas.txt e escreve cada consulta na lista, linha por linha.
  - Importância para o Sistema: Assegura que as alterações nos dados das consultas sejam persistidas corretamente no armazenamento, evitando perda de dados.
  - Uso no Sistema: Chamado após operações que modificam a lista de consultas, como adição ou remoção de consultas.

- `carregar()`: Este método sobrescreve o método abstrato da classe DAOGenerico e carrega a lista de marcações de consultas a partir do arquivo consultas.txt.
  - Lógica Implementada: Abre um BufferedReader para o arquivo consultas.txt e lê cada linha, convertendo-a em um objeto MarcacaoConsulta e adicionando-o à lista.
  - Importância para o Sistema: Garante que os dados das consultas sejam carregados do armazenamento persistente, tornando-os disponíveis para o sistema.
  - Uso no Sistema: Chamado no construtor da classe MarcacaoConsultaDAO para inicializar a lista de consultas.

- `converterLinhaParaConsulta(String linha)`: Este método converte uma linha de texto em um objeto MarcacaoConsulta.
  - Lógica Implementada: Divide a linha de texto nos campos idMarcacao, nome, telefone, especialidade, data e horário, cria e retorna um novo objeto MarcacaoConsulta com esses valores.
  - Importância para o Sistema: Facilita a conversão de dados textuais do arquivo para objetos MarcacaoConsulta, permitindo a manipulação fácil dos dados no sistema.
  - Uso no Sistema: Utilizado no método carregar() para converter cada linha do arquivo em um objeto MarcacaoConsulta.

**Como a Classe e os Métodos Estão Sendo Usados no Sistema:**
- Classe:
  - MarcacaoConsultaDAO gerencia a persistência e a recuperação dos dados das consultas, fornecendo uma interface consistente para operações CRUD.
- Métodos:
  - `salvar()` é utilizado para persistir as alterações feitas na lista de consultas.
  - `carregar()` é utilizado para carregar os dados das consultas do armazenamento persistente ao iniciar o sistema.
  - `converterLinhaParaConsulta(String linha)` é utilizado no método carregar() para converter cada linha do arquivo em um objeto MarcacaoConsulta.

**Resumo da Implementação:**
A classe MarcacaoConsultaDAO herda de DAOGenerico e implementa métodos específicos para gerenciar a persistência de dados das marcações de consultas. A lógica principal envolve a leitura e a escrita dos dados de consultas em um arquivo de texto, assegurando que as informações sejam armazenadas e recuperadas de maneira eficiente e consistente. Isso permite que o sistema mantenha um registro atualizado e confiável das consultas agendadas, essencial para o funcionamento das operações clínicas.

### 6.4. Registro
**Funcionalidade da Classe no Sistema:**
A classe Registro é uma classe abstrata que fornece uma estrutura básica para qualquer entidade que precise de um identificador único dentro do sistema. Ela é projetada para ser estendida por outras classes que representam entidades do sistema, como Paciente e MarcacaoConsulta.

**Explicação das Bibliotecas e dos Imports:**
A classe Registro não requer nenhuma biblioteca ou importação adicional além do que já está disponível no pacote padrão do Java.

**Explicação dos Construtores:**
A classe Registro não define nenhum construtor explícito. Em Java, se uma classe não define um construtor, o compilador fornece um construtor padrão sem parâmetros. Este construtor padrão é suficiente para a classe Registro, pois ela não possui nenhum campo que precise ser inicializado.

**Importância da Classe para o Sistema:**
A classe Registro é importante porque estabelece um contrato para qualquer entidade que precise de um identificador único, garantindo que todas as subclasses implementem o método getIdUnico(). Isso facilita a gestão de entidades dentro do sistema, permitindo operações de CRUD de forma mais organizada e consistente.

**Explicação dos Métodos:**
- `getIdUnico()`: Método abstrato que deve ser implementado por qualquer classe que estenda Registro. Ele retorna um identificador único para a entidade.
  - Lógica Implementada: Sendo um método abstrato, ele não contém lógica própria. A lógica é fornecida pelas subclasses que implementam este método.
  - Importância para o Sistema: Garante que todas as entidades que estendem Registro possuam um identificador único, o que é crucial para a identificação e manipulação das entidades no sistema.
  - Uso no Sistema: Utilizado pelas classes Paciente e MarcacaoConsulta para fornecer identificadores únicos para pacientes e marcações de consulta, respectivamente.

**Como a Classe e os Métodos Estão Sendo Usados no Sistema:**
- Classe:
  - Registro é utilizada como uma classe base para entidades que necessitam de um identificador único. Ela define um contrato que garante que todas as subclasses implementem o método getIdUnico().
- Métodos:
  - `getIdUnico()` é utilizado pelas subclasses Paciente e MarcacaoConsulta para fornecer um identificador único para cada instância dessas entidades.

**Resumo da Implementação:**
A classe Registro é uma classe abstrata que estabelece uma estrutura para entidades que precisam de identificadores únicos. Ela não possui lógica própria além de definir o método abstrato getIdUnico(), que deve ser implementado pelas subclasses. Isso garante que todas as entidades que estendem Registro tenham um identificador único, facilitando a gestão de entidades no sistema.

### 6.5. Paciente
**Funcionalidade da Classe no Sistema:**
A classe Paciente representa um paciente no sistema de gestão de consultas médicas. Ela contém informações básicas sobre o paciente, como nome e telefone, e métodos para manipular e gerenciar esses dados.

**Explicação das Bibliotecas e dos Imports:**
- `import bancoDeDados.PacienteDAO;`: Importa a classe PacienteDAO para interagir com a persistência de dados dos pacientes.
- `import bancoDeDados.Registro;`: Importa a classe Registro para herdar a funcionalidade de obter um identificador único.
- `import bancoDeDados.MarcacaoConsultaDAO;`: Importa a classe MarcacaoConsultaDAO para gerenciar as marcações de consultas associadas aos pacientes.
- `import java.util.List;`: Importa a interface List para trabalhar com listas de objetos.
- `import java.util.Scanner;`: Importa a classe Scanner para entrada de dados pelo usuário.

**Explicação dos Construtores:**
- `Paciente(String nome, long telefone)`: Inicializa um novo paciente com o nome e telefone fornecidos.
  - Lógica Implementada: Atribui os valores dos parâmetros aos campos correspondentes (nome e telefone) da classe Paciente.

**Importância da Classe para o Sistema:**
A classe Paciente é essencial para o sistema, pois representa a entidade principal que interage com a clínica. Ela permite o cadastro, listagem, remoção e consulta de pacientes, facilitando a gestão dos mesmos.

**Explicação dos Métodos:**
- `getIdUnico()`: Retorna o telefone do paciente como seu identificador único.
  - Lógica Implementada: Converte o telefone do paciente em uma string e o retorna.
  - Importância para o Sistema: Garante que cada paciente tenha um identificador único baseado no número de telefone.
  - Uso no Sistema: Utilizado para identificar unicamente cada paciente.

- `toString()`: Retorna uma representação em string do objeto Paciente.
  - Lógica Implementada: Concatena o nome e telefone do paciente em uma string formatada.
  - Importância para o Sistema: Facilita a exibição dos dados do paciente.
  - Uso no Sistema: Utilizado ao exibir informações dos pacientes.

- `cadastrarPaciente(Scanner scanner)`: Método estático que cadastra um novo paciente.
  - Lógica Implementada: Lê os dados do paciente a partir da entrada do usuário, valida o nome e o telefone, verifica se o telefone já está cadastrado e, se estiver tudo correto, adiciona o paciente ao PacienteDAO.
  - Importância para o Sistema: Permite o cadastro de novos pacientes no sistema.
  - Uso no Sistema: Utilizado ao adicionar um novo paciente.

- `listarPacientes()`: Método estático que lista todos os pacientes cadastrados.
  - Lógica Implementada: Recupera a lista de pacientes do PacienteDAO e a exibe no console.
  - Importância para o Sistema: Facilita a visualização de todos os pacientes cadastrados.
  - Uso no Sistema: Utilizado para exibir a lista de pacientes.

- `removerPaciente(Scanner scanner)`: Método estático que remove um paciente pelo telefone.
  - Lógica Implementada: Lê o telefone do paciente a ser removido, verifica se o paciente existe, e se confirmado, remove o paciente e suas consultas associadas.
  - Importância para o Sistema: Permite a remoção de pacientes do sistema.
  - Uso no Sistema: Utilizado ao excluir um paciente.

- `consultarStatusPaciente(Scanner scanner)`: Método estático que consulta o status de um paciente pelo telefone.
  - Lógica Implementada: Lê o telefone do paciente, verifica se o paciente existe, e exibe suas consultas agendadas.
  - Importância para o Sistema: Permite a consulta de informações detalhadas sobre um paciente.
  - Uso no Sistema: Utilizado para visualizar o status e as consultas de um paciente.

**Como a Classe e os Métodos Estão Sendo Usados no Sistema:**
- Classe:
  - A classe Paciente é utilizada para representar pacientes no sistema, fornecendo métodos para manipulação e gerenciamento de seus dados.
- Métodos:
  - `getIdUnico()` é utilizado para identificar unicamente cada paciente.
  - `toString()` é utilizado para exibir informações dos pacientes.
  - `cadastrarPaciente(Scanner scanner)` é utilizado para cadastrar novos pacientes.
  - `listarPacientes()` é utilizado para listar todos os pacientes cadastrados.
  - `removerPaciente(Scanner scanner)` é utilizado para remover pacientes do sistema.
  - `consultarStatusPaciente(Scanner scanner)` é utilizado para consultar o status de um paciente e suas consultas.

**Resumo da Implementação:**
A classe Paciente é crucial para a gestão de pacientes no sistema. Ela implementa métodos para cadastrar, listar, remover e consultar pacientes, utilizando a classe PacienteDAO para persistência de dados. A lógica de validação e manipulação de dados é implementada nos métodos da classe, garantindo que as operações sejam realizadas de forma consistente e eficiente.

### 6.6. MarcacaoConsulta
**Funcionalidade da Classe no Sistema:**
A classe MarcacaoConsulta representa uma marcação de consulta no sistema de gestão de consultas médicas. Ela armazena informações sobre a consulta, como o ID da marcação, o paciente, a data, o horário e a especialidade da consulta.

**Explicação das Bibliotecas e dos Imports:**
- `import bancoDeDados.Registro;`: Importa a classe Registro para herdar a funcionalidade de obter um identificador único.
- `import bancoDeDados.PacienteDAO;`: Importa a classe PacienteDAO para interagir com a persistência de dados dos pacientes.
- `import bancoDeDados.MarcacaoConsultaDAO;`: Importa a classe MarcacaoConsultaDAO para gerenciar a persistência das marcações de consultas.
- `import java.time.DayOfWeek;`: Importa a enum DayOfWeek para representar os dias da semana.
- `import java.time.LocalDate;`: Importa a classe LocalDate para trabalhar com datas.
- `import java.time.LocalTime;`: Importa a classe LocalTime para trabalhar com horários.
- `import java.time.format.DateTimeFormatter;`: Importa a classe DateTimeFormatter para formatar e analisar datas.
- `import java.time.format.DateTimeParseException;`: Importa a classe DateTimeParseException para tratar exceções de análise de datas.
- `import java.util.List;`: Importa a interface List para trabalhar com listas de objetos.
- `import java.util.Scanner;`: Importa a classe Scanner para entrada de dados pelo usuário.
- `import java.util.concurrent.atomic.AtomicInteger;`: Importa a classe AtomicInteger para gerenciar o ID das consultas de forma thread-safe.

**Explicação dos Construtores:**
- `MarcacaoConsulta(int idMarcacao, Paciente paciente, LocalDate dataMarcacao, LocalTime horarioMarcacao, String especialidade)`: Inicializa uma nova marcação de consulta com os parâmetros fornecidos.
  - Lógica Implementada: Atribui os valores dos parâmetros aos campos correspondentes da classe MarcacaoConsulta.

**Importância da Classe para o Sistema:**
A classe MarcacaoConsulta é essencial para o sistema, pois gerencia as informações das consultas médicas, permitindo o agendamento, listagem e cancelamento de consultas, além de manter um registro organizado das mesmas.

**Explicação dos Métodos:**
- `getIdUnico()`: Retorna o ID da marcação como seu identificador único.
  - Lógica Implementada: Converte o ID da marcação em uma string e o retorna.
  - Importância para o Sistema: Garante que cada marcação de consulta tenha um identificador único.
  - Uso no Sistema: Utilizado para identificar unicamente cada marcação de consulta.

- `toString()`: Retorna uma representação em string do objeto MarcacaoConsulta.
  - Lógica Implementada: Concatena o ID, nome do paciente, telefone, especialidade, data e horário da consulta em uma string formatada.
  - Importância para o Sistema: Facilita a exibição dos dados da consulta.
  - Uso no Sistema: Utilizado ao exibir informações das consultas.

- `marcarConsulta(Scanner scanner)`: Método estático que marca uma nova consulta.
  - Lógica Implementada: Lê os dados da consulta a partir da entrada do usuário, valida a data e horário, verifica a disponibilidade e adiciona a consulta ao MarcacaoConsultaDAO.
  - Importância para o Sistema: Permite o agendamento de novas consultas no sistema.
  - Uso no Sistema: Utilizado ao agendar uma nova consulta.

- `obterEspecialidades(LocalDate data)`: Retorna as especialidades disponíveis para um dia específico.
  - Lógica Implementada: Verifica o dia da semana e retorna as especialidades correspondentes.
  - Importância para o Sistema: Garante que as especialidades sejam oferecidas corretamente conforme o dia da semana.
  - Uso no Sistema: Utilizado ao agendar uma nova consulta para determinar as especialidades disponíveis.

- `escolherEspecialidade(Scanner scanner, LocalDate data)`: Permite ao usuário escolher uma especialidade para a consulta.
  - Lógica Implementada: Exibe as especialidades disponíveis e permite que o usuário escolha uma.
  - Importância para o Sistema: Facilita a seleção da especialidade da consulta.
  - Uso no Sistema: Utilizado ao agendar uma nova consulta para selecionar a especialidade.

- `listarConsultas()`: Lista todas as consultas agendadas.
  - Lógica Implementada: Recupera a lista de consultas do MarcacaoConsultaDAO e a exibe no console.
  - Importância para o Sistema: Facilita a visualização de todas as consultas agendadas.
  - Uso no Sistema: Utilizado para exibir a lista de consultas.

- `cancelarConsulta(Scanner scanner)`: Método estático que cancela uma consulta pelo ID da marcação.
  - Lógica Implementada: Lê o ID da consulta a ser cancelada, verifica se a consulta existe, e se confirmado, remove a consulta do MarcacaoConsultaDAO.
  - Importância para o Sistema: Permite o cancelamento de consultas no sistema.
  - Uso no Sistema: Utilizado ao cancelar uma consulta.

- `promptEnterKey(Scanner scanner)`: Aguarda o usuário pressionar Enter para continuar.
  - Lógica Implementada: Exibe uma mensagem solicitando ao usuário pressionar Enter e aguarda a entrada.
  - Importância para o Sistema: Facilita a navegação no console, pausando a execução até que o usuário esteja pronto para continuar.
  - Uso no Sistema: Utilizado em várias partes do sistema para pausar a execução e aguardar a interação do usuário.

**Como a Classe e os Métodos Estão Sendo Usados no Sistema:**
- Classe:
  - A classe MarcacaoConsulta é utilizada para representar e gerenciar as consultas médicas no sistema, fornecendo métodos para agendar, listar e cancelar consultas.
- Métodos:
  - `getIdUnico()` é utilizado para identificar unicamente cada marcação de consulta.
  - `toString()` é utilizado para exibir informações das consultas.
  - `marcarConsulta(Scanner scanner)` é utilizado para agendar novas consultas.
  - `obterEspecialidades(LocalDate data)` é utilizado para determinar as especialidades disponíveis para uma data específica.
  - `escolherEspecialidade(Scanner scanner, LocalDate data)` é utilizado para selecionar a especialidade da consulta.
  - `listarConsultas()` é utilizado para listar todas as consultas agendadas.
  - `cancelarConsulta(Scanner scanner)` é utilizado para cancelar consultas agendadas.
  - `promptEnterKey(Scanner scanner)` é utilizado para pausar a execução e aguardar a interação do usuário.

**Resumo da Implementação:**
A classe MarcacaoConsulta é crucial para a gestão de consultas no sistema. Ela implementa métodos para agendar, listar e cancelar consultas, utilizando a classe MarcacaoConsultaDAO para persistência de dados. A lógica de validação e manipulação de dados é implementada nos métodos da classe, garantindo que as operações sejam realizadas de forma consistente e eficiente.

### 6.7. Clinica
**Funcionalidade da Classe no Sistema:**
A classe Clinica é a classe principal que executa o sistema de gestão de consultas médicas. Ela é responsável por interagir com o usuário, exibindo menus, capturando entradas e chamando métodos apropriados das outras classes para realizar operações como cadastrar pacientes, marcar consultas, listar consultas e pacientes, cancelar consultas, remover pacientes, consultar status de pacientes, e sair do sistema.

**Explicação das Bibliotecas e dos Imports:**
- `import bancoDeDados.PacienteDAO;`: Importa a classe PacienteDAO para interagir com a persistência de dados dos pacientes.
- `import bancoDeDados.MarcacaoConsultaDAO;`: Importa a classe MarcacaoConsultaDAO para interagir com a persistência de dados das marcações de consultas.
- `import java.util.List;`: Importa a interface List para trabalhar com listas de objetos.
- `import java.util.Scanner;`: Importa a classe Scanner para entrada de dados pelo usuário.

**Explicação dos Construtores:**
A classe Clinica não possui construtores explícitos. Em Java, se nenhum construtor for definido, a classe terá um construtor padrão (sem parâmetros) que é fornecido automaticamente pelo compilador.

**Importância da Classe para o Sistema:**
A classe Clinica é a interface principal do sistema, sendo responsável por gerenciar o fluxo de operações e a interação do usuário com o sistema. Ela centraliza a lógica de navegação do menu e invoca métodos das classes Paciente e MarcacaoConsulta para realizar as operações necessárias.

**Explicação dos Métodos:**
- `main(String[] args)`: O ponto de entrada do sistema. Inicializa o scanner e entra no loop principal do menu.
  - Lógica Implementada: Cria um objeto Scanner, exibe as regras de negócios e chama o método exibirMenu em um loop infinito para manter o sistema em execução.
  - Importância para o Sistema: Inicia o sistema e mantém o loop do menu ativo.
  - Uso no Sistema: Utilizado para iniciar a aplicação.

- `exibirMenu(Scanner scanner)`: Exibe o menu de opções do sistema e captura a escolha do usuário.
  - Lógica Implementada: Exibe as opções do menu, valida a entrada do usuário e chama os métodos apropriados com base na escolha.
  - Importância para o Sistema: Controla a navegação do usuário no sistema, permitindo acesso a diferentes funcionalidades.
  - Uso no Sistema: Utilizado para exibir o menu e direcionar o usuário para as operações desejadas.

- `validarEntrada(Scanner scanner)`: Valida se a entrada do usuário é um número inteiro.
  - Lógica Implementada: Usa um loop para garantir que a entrada é um número inteiro válido.
  - Importância para o Sistema: Garante que as opções do menu recebam entradas válidas.
  - Uso no Sistema: Utilizado para validar a entrada do usuário no menu.

- `promptEnterKey(Scanner scanner)`: Exibe uma mensagem para o usuário pressionar Enter para continuar.
  - Lógica Implementada: Usa o scanner para esperar que o usuário pressione Enter.
  - Importância para o Sistema: Pausa a execução para permitir que o usuário veja as informações exibidas antes de continuar.
  - Uso no Sistema: Utilizado após operações que exibem informações para o usuário.

- `confirmarSaida(Scanner scanner)`: Pede confirmação ao usuário antes de sair do sistema.
  - Lógica Implementada: Captura a entrada do usuário e verifica se a resposta é 's' ou 'n'.
  - Importância para o Sistema: Evita saídas acidentais do sistema.
  - Uso no Sistema: Utilizado para confirmar a saída do sistema.

- `exibirRegraDeNegocios()`: Exibe as regras de negócios e horários de funcionamento da clínica.
  - Lógica Implementada: Exibe um banner com informações sobre os horários e especialidades da clínica.
  - Importância para o Sistema: Informa o usuário sobre os horários e especialidades disponíveis.
  - Uso no Sistema: Utilizado para exibir as regras de negócios no início do sistema.

**Como a Classe e os Métodos Estão Sendo Usados no Sistema:**
- Classe:
  - A classe Clinica é a interface principal do sistema e é responsável por gerenciar a interação do usuário com o sistema.
- Métodos:
  - `main(String[] args)` inicia a aplicação e mantém o loop do menu.
  - `exibirMenu(Scanner scanner)` exibe o menu e direciona o usuário para as operações desejadas.
  - `validarEntrada(Scanner scanner)` valida a entrada do usuário no menu.
  - `promptEnterKey(Scanner scanner)` pausa a execução para permitir que o usuário veja as informações exibidas.
  - `confirmarSaida(Scanner scanner)` confirma a saída do sistema.
  - `exibirRegraDeNegocios()` exibe as regras de negócios e horários de funcionamento da clínica.

**Resumo da Implementação:**
A classe Clinica gerencia a interação do usuário com o sistema, exibindo menus, capturando entradas e chamando métodos apropriados das outras classes para realizar operações como cadastro, marcação de consultas, listagem de consultas e pacientes, cancelamento de consultas, remoção de pacientes, consulta de status de pacientes e saída do sistema. A lógica principal envolve a captura de entradas do usuário, validação dessas entradas e execução das operações correspondentes, garantindo que o sistema funcione de forma eficiente e amigável para o usuário.

## 7. Conclusão
A documentação apresentada detalha o desenvolvimento, funcionalidades e regras de negócio do sistema de gestão de consultas médicas. Esta aplicação, construída em Java, visa resolver problemas comuns enfrentados em clínicas médicas, automatizando e organizando o gerenciamento de pacientes e consultas.

O desenvolvimento do sistema segue uma abordagem modular e estruturada, garantindo que cada componente desempenhe um papel específico e integrado no funcionamento geral do sistema. A escolha da linguagem Java proporciona robustez e escalabilidade, características essenciais para sistemas que necessitam de alta confiabilidade e desempenho.

