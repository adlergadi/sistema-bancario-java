# Sistema Bancário em Java

Projeto acadêmico desenvolvido em **Java** com o objetivo de aplicar conceitos de **Programação Orientada a Objetos (POO)** por meio da simulação de operações básicas de um sistema bancário.

O sistema permite cadastrar contas, realizar depósitos, saques e transferências, controlar o status das contas, registrar transações e processar regras específicas para contas correntes e contas poupança.

Entre os principais tópicos praticados estão herança, abstração, interfaces, polimorfismo, encapsulamento, coleções, enums e manipulação de datas.

## Tecnologias

- Java
- Java Collections Framework
- API `java.time`
- Programação Orientada a Objetos

## Funcionalidades

- Cadastro e gerenciamento de contas bancárias
- Busca de contas pelo número
- Depósitos e saques
- Transferências entre contas
- Registro automático de transações
- Emissão de extrato
- Controle de saldo e saldo disponível
- Autenticação por senha
- Alteração de senha
- Bloqueio, ativação e encerramento de contas
- Processamento de fechamento mensal
- Cobrança de tarifa mensal em conta corrente
- Aplicação de rendimento mensal em conta poupança
- Cadastro de dados do titular e endereço

## Conceitos aplicados

O projeto foi desenvolvido para praticar conceitos fundamentais de Programação Orientada a Objetos:

- **Encapsulamento:** atributos privados e acesso controlado por métodos.
- **Herança:** `ContaCorrente` e `ContaPoupanca` herdam características e comportamentos de `Conta`.
- **Abstração:** `Conta` é uma classe abstrata que define comportamentos comuns às diferentes modalidades de conta.
- **Polimorfismo:** diferentes tipos de conta implementam de forma específica métodos como `processarFechamentoMensal()`.
- **Interfaces:** a interface `Autenticavel` define o contrato de autenticação das contas.
- **Enums:** `StatusConta` e `TipoTransacao` representam conjuntos definidos de estados e tipos de operação.
- **Composição e associação:** contas possuem titulares e transações, enquanto o banco mantém uma coleção de contas.

## Estrutura do projeto

```text
src/
├── app/
│   └── Main.java
│
└── domain/
    ├── Autenticavel.java
    ├── Banco.java
    ├── Conta.java
    ├── ContaCorrente.java
    ├── ContaPoupanca.java
    ├── Endereco.java
    ├── StatusConta.java
    ├── TipoTransacao.java
    ├── TitularConta.java
    └── Transacao.java
```

## Principais classes

### `Banco`

Responsável por administrar as contas cadastradas no banco.

Principais operações:

- adicionar contas;
- buscar uma conta pelo número;
- realizar transferências;
- encerrar contas;
- executar o fechamento mensal de todas as contas.

### `Conta`

Classe abstrata que concentra os atributos e comportamentos comuns às contas bancárias.

Possui recursos como:

- depósito;
- saque;
- transferência;
- autenticação;
- alteração de senha;
- bloqueio e ativação;
- encerramento;
- registro e impressão do extrato.

### `ContaCorrente`

Especialização de `Conta` que acrescenta:

- limite adicional;
- tarifa mensal;
- cobrança automática da tarifa durante o fechamento mensal.

### `ContaPoupanca`

Especialização de `Conta` que acrescenta:

- taxa de rendimento;
- cálculo e crédito automático do rendimento no fechamento mensal.

### `TitularConta`

Representa o proprietário de uma conta e armazena informações como:

- nome;
- CPF;
- e-mail;
- telefone;
- data de nascimento;
- endereço.

Também permite calcular a idade do titular com base na data de nascimento.

### `Transacao`

Representa cada movimentação realizada em uma conta.

Cada transação registra:

- data e hora;
- tipo;
- valor;
- descrição.

### `Autenticavel`

Interface responsável por definir o método de autenticação utilizado pelas contas.

```java
boolean autenticar(String senha);
```

### `StatusConta`

Define os possíveis estados de uma conta:

```text
ATIVA
BLOQUEADA
ENCERRADA
```

### `TipoTransacao`

Define os tipos de movimentação registrados pelo sistema:

```text
DEPOSITO
SAQUE
TRANSFERENCIA_ENVIADA
TRANSFERENCIA_RECEBIDA
RENDIMENTO
TARIFA
```

## Exemplo de uso

```java
Banco banco = new Banco("Banco UnDF", "001");

TitularConta titular = new TitularConta(
    "Adler Gadioli",
    "000.000.000-00"
);

Conta contaCorrente = new ContaCorrente(
    titular,
    "1001",
    "0001",
    "1234",
    500.00,
    25.00
);

banco.adicionarConta(contaCorrente);

contaCorrente.depositar(1000.00);
contaCorrente.sacar(200.00);

contaCorrente.imprimirExtrato();
```

## Exemplo de operações demonstradas

A classe `Main` apresenta um cenário de teste contendo:

1. criação de um banco;
2. cadastro de titulares;
3. criação de uma conta corrente e uma conta poupança;
4. autenticação de uma conta;
5. realização de depósitos;
6. transferência entre contas;
7. saque;
8. impressão dos extratos.

## Como executar

### Pré-requisitos

- **Java JDK 11 ou superior**
- Uma IDE Java, como IntelliJ IDEA, Eclipse ou VS Code com extensões para Java

### Executando pela IDE

1. Clone este repositório.
2. Abra o projeto na sua IDE.
3. Certifique-se de que os arquivos estejam organizados conforme os pacotes `app` e `main.java.domain`.
4. Execute a classe:

```text
app.Main
```

### Executando pelo terminal

A partir da pasta que contém o diretório `src`:

```bash
javac -d out src/domain/*.java src/app/Main.java
java -cp out app.Main
```




