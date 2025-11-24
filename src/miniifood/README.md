# 📋 LISTA DE TASKS - PROJETO MINI IFOOD

**Disciplina:** Programação Orientada a Objetos (Java)  
**Projeto:** Sistema Mini iFood  
**Total de Tasks:** 40  
**Alunos:** 40 (1 task por aluno)

---

📋 LISTA DE TASKS - PROJETO MINI IFOOD
Disciplina: Programação Orientada a Objetos (Java)
Projeto: Sistema Mini iFood
Total de Tasks: 41 (Task 0 + Tasks 1 a 40)
Alunos: 40 (1 task por aluno) + Task 0 pode ser feita pelo professor como exemplo

🏗️ CLASSE BASE (Task 0)
Task 0 - Entidade Base (BaseEntity)

Criar classe abstrata BaseEntity que servirá como classe pai para todas as outras entidades
Atributos comuns que se repetem em várias entidades:

id (int) - identificador único
dataCriacao (LocalDateTime) - data de criação do registro
dataAtualizacao (LocalDateTime) - data da última atualização
ativo (boolean) - indica se o registro está ativo


Todos os atributos privados
Criar gets e setters para todos os atributos
Método adicional: ativar() - seta ativo como true
Método adicional: desativar() - seta ativo como false
Método adicional: atualizarDataModificacao() - atualiza dataAtualizacao para agora

## 🗂️ ENTIDADES (Tasks 1 a 20)

### **Task 1 - Entidade CategoriaTipoLoja**
- Criar classe `CategoriaTipoLoja` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `ativo` (boolean)
    - `dataCriacao` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 2 - Entidade CategoriaTipoProduto**
- Criar classe `CategoriaTipoProduto` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `ativo` (boolean)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 3 - Entidade Loja**
- Criar classe `Loja` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `descricao` (String)
    - `telefone` (String)
    - `email` (String)
    - `cnpj` (String)
    - `valorMinimo` (double)
    - `taxaEntrega` (double)
    - `tempoEntregaMin` (int)
    - `tempoEntregaMax` (int)
    - `aberta` (boolean)
    - `ativa` (boolean)
    - `categoriaTipoLojaId` (int)
    - `dataCriacao` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 4 - Entidade AvaliacaoLoja**
- Criar classe `AvaliacaoLoja` com todos os atributos:
    - `id` (int)
    - `descricao` (String)
    - `nota` (int)
    - `lojaId` (int)
    - `userId` (int)
    - `dataAvaliacao` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 5 - Entidade CategoriaProduto**
- Criar classe `CategoriaProduto` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `lojaId` (int)
    - `ordem` (int)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 6 - Entidade Produto**
- Criar classe `Produto` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `descricao` (String)
    - `preco` (double)
    - `disponivel` (boolean)
    - `destaque` (boolean)
    - `categoriaProdutoId` (int)
    - `lojaId` (int)
    - `categoriaTipoProdutoId` (int)
    - `dataCriacao` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 7 - Entidade Acompanhamento**
- Criar classe `Acompanhamento` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `descricao` (String)
    - `valorAdicional` (double)
    - `obrigatorio` (boolean)
    - `maximoPermitido` (int)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 8 - Entidade ProdutoAcompanhamento**
- Criar classe `ProdutoAcompanhamento` com todos os atributos:
    - `produtoId` (int)
    - `acompanhamentoId` (int)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 9 - Entidade ifoodProject.Entities.User**
- Criar classe `ifoodProject.Entities.User` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `email` (String)
    - `telefone` (String)
    - `cpf` (String)
    - `senha` (String)
    - `dataNascimento` (LocalDate)
    - `ativo` (boolean)
    - `dataCadastro` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 10 - Entidade Endereco**
- Criar classe `Endereco` com todos os atributos:
    - `id` (int)
    - `apelido` (String)
    - `cep` (String)
    - `rua` (String)
    - `numero` (String)
    - `complemento` (String)
    - `bairro` (String)
    - `cidade` (String)
    - `estado` (String)
    - `referencia` (String)
    - `userId` (int)
    - `principal` (boolean)
    - `ativo` (boolean)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 11 - Entidade Entregador**
- Criar classe `Entregador` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `telefone` (String)
    - `cpf` (String)
    - `cnh` (String)
    - `tipoVeiculo` (String)
    - `placaVeiculo` (String)
    - `disponivel` (boolean)
    - `ativo` (boolean)
    - `dataCadastro` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 12 - Entidade Cupom**
- Criar classe `Cupom` com todos os atributos:
    - `id` (int)
    - `codigo` (String)
    - `descricao` (String)
    - `tipoDesconto` (String) - "percentual" ou "valor_fixo"
    - `valorDesconto` (double)
    - `valorMinimoPedido` (double)
    - `dataValidadeInicio` (LocalDate)
    - `dataValidadeFim` (LocalDate)
    - `quantidadeDisponivel` (int)
    - `quantidadeUsada` (int)
    - `ativo` (boolean)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 13 - Entidade FormaPagamento**
- Criar classe `FormaPagamento` com todos os atributos:
    - `id` (int)
    - `nome` (String)
    - `ativo` (boolean)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 14 - Entidade StatusPagamento**
- Criar classe `StatusPagamento` com todos os atributos:
    - `id` (int)
    - `nome` (String)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 15 - Entidade StatusPedido**
- Criar classe `StatusPedido` com todos os atributos:
    - `id` (int)
    - `nome` (String)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 16 - Entidade Sacola (Pedido)**
- Criar classe `Sacola` com todos os atributos:
    - `id` (int)
    - `numeroPedido` (String)
    - `userId` (int)
    - `lojaId` (int)
    - `enderecoId` (int)
    - `entregadorId` (int)
    - `cupomId` (int)
    - `formaPagamentoId` (int)
    - `subtotal` (double)
    - `taxaEntrega` (double)
    - `desconto` (double)
    - `valorTotal` (double)
    - `trocoPara` (double)
    - `observacao` (String)
    - `dataPedido` (LocalDateTime)
    - `dataConfirmacao` (LocalDateTime)
    - `dataEntrega` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 17 - Entidade ProdutoSacola**
- Criar classe `ProdutoSacola` com todos os atributos:
    - `id` (int)
    - `sacolaId` (int)
    - `produtoId` (int)
    - `quantidade` (int)
    - `precoUnitario` (double)
    - `observacao` (String)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 18 - Entidade HistoricoPagamento**
- Criar classe `HistoricoPagamento` com todos os atributos:
    - `id` (int)
    - `sacolaId` (int)
    - `statusPagamentoId` (int)
    - `dataStatus` (LocalDateTime)
    - `observacao` (String)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 19 - Entidade HistoricoPedidoStatus**
- Criar classe `HistoricoPedidoStatus` com todos os atributos:
    - `id` (int)
    - `sacolaId` (int)
    - `statusPedidoId` (int)
    - `dataStatus` (LocalDateTime)
    - `observacao` (String)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

### **Task 20 - Entidade AvaliacaoPedido**
- Criar classe `AvaliacaoPedido` com todos os atributos:
    - `id` (int)
    - `sacolaId` (int)
    - `nota` (int)
    - `comentario` (String)
    - `dataAvaliacao` (LocalDateTime)
- Todos os atributos **privados**
- Criar **gets e setters** para todos os atributos
- Criar **construtores** (vazio e completo)
- Sobrescrever método `toString()`

---

## ✅ VALIDADORES (Tasks 21 a 28)

### **Task 21 - Validador de CPF**
- Criar classe `ValidadorCPF` com método estático `validar(String cpf)`
- **Regras de validação:**
    - Remover caracteres especiais (pontos e traços)
    - Verificar se tem exatamente 11 dígitos
    - Verificar se não são todos dígitos iguais (ex: 111.111.111-11)
- Retornar `true` se válido, `false` se inválido
- 
**Exemplo de CPF válido:** 123.456.789-09

---

### **Task 22 - Validador de CNPJ**
- Criar classe `ValidadorCNPJ` com método estático `validar(String cnpj)`
- **Regras de validação:**
    - Remover caracteres especiais (pontos, traços e barra)
    - Verificar se tem exatamente 14 dígitos
    - Verificar se não são todos dígitos iguais
- Retornar `true` se válido, `false` se inválido
- 
**Exemplo de CNPJ válido:** 11.222.333/0001-81

---

### **Task 23 - Validador de Email**
- Criar classe `ValidadorEmail` com método estático `validar(String email)`
- **Regras de validação:**
    - Verificar se contém exatamente um @
    - Verificar se tem texto antes e depois do @
    - Verificar se tem pelo menos um ponto após o @
    - Verificar se não termina com ponto
    - Verificar se não tem espaços
- Retornar `true` se válido, `false` se inválido
- 
**Exemplos válidos:** usuario@email.com, nome.sobrenome@empresa.com.br

---

### **Task 24 - Validador de Telefone/Celular**
- Criar classe `ValidadorTelefone` com método estático `validar(String telefone)`
- **Regras de validação:**
    - Remover caracteres especiais (parênteses, traços e espaços)
    - Verificar se tem 10 dígitos (fixo) ou 11 dígitos (celular)
    - Verificar se começa com DDD válido (11 a 99)
    - Para celular (11 dígitos), verificar se o terceiro dígito é 9
    - Para fixo (10 dígitos), verificar se o terceiro dígito não é 9
- Retornar `true` se válido, `false` se inválido
- 
**Exemplos válidos:** (81) 3333-4444 (fixo), (81) 9 8888-7777 (celular)

---

### **Task 25 - Validador de CEP**
- Criar classe `ValidadorCEP` com método estático `validar(String cep)`
- **Regras de validação:**
    - Remover caracteres especiais (traço)
    - Verificar se tem exatamente 8 dígitos
    - Verificar se são apenas números
    - Verificar se não é CEP inválido (00000-000)
- Retornar `true` se válido, `false` se inválido
- 
**Exemplo válido:** 50050-000

---

### **Task 26 - Validador de Nota (1 a 5 estrelas)**
- Criar classe `ValidadorNota` com método estático `validar(int nota)`
- **Regras de validação:**
    - Verificar se a nota está entre 1 e 5 (inclusive)
- Retornar `true` se válido, `false` se inválido
- 
---

### **Task 27 - Validador de Nome Completo**
- Criar classe `ValidadorNomeCompleto` com método estático `validar(String nome)`
- **Regras de validação:**
    - Verificar se o nome não está vazio
    - Verificar se tem pelo menos um espaço (indica nome e sobrenome)
    - Verificar se cada parte (nome e sobrenome) tem pelo menos 2 caracteres
    - Verificar se contém apenas letras e espaços (aceitar acentos)
    - Remover espaços extras no início, fim e entre palavras
- Retornar `true` se válido, `false` se inválido
- 
**Exemplos válidos:** João Silva, Maria da Silva Santos

---

### **Task 28 - Validador de Senha Forte**
- Criar classe `ValidadorSenha` com método estático `validar(String senha)`
- **Regras de validação:**
    - Verificar se tem no mínimo 8 caracteres
    - Verificar se contém pelo menos uma letra maiúscula
    - Verificar se contém pelo menos uma letra minúscula
    - Verificar se contém pelo menos um número
- Retornar `true` se válido, `false` se inválido
- Criar método adicional `obterMensagemErro(String senha)` que retorna qual critério não foi atendido
- 
**Exemplo válido:** Senha123

---

## 🎨 FORMATADORES (Tasks 29 a 33)

### **Task 29 - pooP2.util.Formatador de CPF**
- Criar classe `FormatadorCPF` com método estático `formatar(String cpf)`
- **Funcionalidade:**
    - Receber CPF sem formatação (apenas números)
    - Retornar CPF formatado no padrão: **000.000.000-00**
    - Se o CPF não tiver 11 dígitos, retornar mensagem de erro
- Criar método adicional `removerFormatacao(String cpf)` que remove a formatação
- 
**Exemplo:**  
Entrada: `12345678909`  
Saída: `123.456.789-09`

---

### **Task 30 - pooP2.util.Formatador de CNPJ**
- Criar classe `FormatadorCNPJ` com método estático `formatar(String cnpj)`
- **Funcionalidade:**
    - Receber CNPJ sem formatação (apenas números)
    - Retornar CNPJ formatado no padrão: **00.000.000/0000-00**
    - Se o CNPJ não tiver 14 dígitos, retornar mensagem de erro
- Criar método adicional `removerFormatacao(String cnpj)` que remove a formatação
- 
**Exemplo:**  
Entrada: `11222333000181`  
Saída: `11.222.333/0001-81`

---

### **Task 31 - pooP2.util.Formatador de Telefone**
- Criar classe `FormatadorTelefone` com método estático `formatar(String telefone)`
- **Funcionalidade:**
    - Receber telefone sem formatação (apenas números)
    - Se tiver 10 dígitos (fixo): **(00) 0000-0000**
    - Se tiver 11 dígitos (celular): **(00) 0 0000-0000**
    - Se não tiver 10 ou 11 dígitos, retornar mensagem de erro
- Criar método adicional `removerFormatacao(String telefone)` que remove a formatação
- 
**Exemplos:**  
Entrada: `8133334444` → Saída: `(81) 3333-4444`  
Entrada: `81988887777` → Saída: `(81) 9 8888-7777`

---

### **Task 32 - pooP2.util.Formatador de Moeda**
- Criar classe `FormatadorMoeda` com método estático `formatar(double valor)`
- **Funcionalidade:**
    - Receber valor numérico (ex: 10.5)
    - Retornar valor formatado em **Real brasileiro: R$ 10,50**
    - Sempre mostrar 2 casas decimais
    - Separar milhares com ponto: R$ 1.234,56
    - Tratar valores negativos: -R$ 10,50 ou R$ -10,50
- 10.5)
    - Valor grande (1234.56)
    - Valor zero (0.0)
    - Valor negativo (-50.0)
    - Valor com muitas casas decimais (10.999)

**Exemplos:**  
Entrada: `10.5` → Saída: `R$ 10,50`  
Entrada: `1234.56` → Saída: `R$ 1.234,56`

---

### **Task 33 - pooP2.util.Formatador de Data/Hora**
- Criar classe `FormatadorDataHora` com métodos estáticos:
    - `formatarData(LocalDate data)` → retorna **DD/MM/YYYY**
    - `formatarHora(LocalTime hora)` → retorna **HH:MM**
    - `formatarDataHora(LocalDateTime dataHora)` → retorna **DD/MM/YYYY HH:MM**
    - `formatarDataHoraCompleta(LocalDateTime dataHora)` → retorna **DD/MM/YYYY às HH:MM:SS**
- 
**Exemplos:**  
`LocalDate.now()` → `15/10/2024`  
`LocalTime.now()` → `14:30`  
`LocalDateTime.now()` → `15/10/2024 14:30`

---

## 🔧 GERADORES (Tasks 34 a 36)

### **Task 34 - Gerador de Número de Pedido**
- Criar classe `GeradorNumeroPedido` com método estático `gerar()`
- **Funcionalidade:**
    - Gerar número único no formato: **PED + AAAAMMDD + sequencial de 3 dígitos**
    - Exemplo: PED20241015001, PED20241015002, PED20241015003
    - Usar `LocalDate.now()` para pegar a data atual
    - Manter um **contador estático** para o sequencial
    - O contador deve reiniciar quando mudar o dia
- Criar método `gerarComPrefixo(String prefixo)` que permite customizar o prefixo
- 
**Exemplo de saída:**
```
PED20241015001
PED20241015002
PED20241015003
```

---

### **Task 35 - Gerador de Código de Cupom**
- Criar classe `GeradorCodigoCupom` com método estático `gerar(String prefixo)`
- **Funcionalidade:**
    - Gerar código com: **prefixo + 4 letras maiúsculas aleatórias + 4 números aleatórios**
    - Exemplo com prefixo "PROMO": PROMOXYWZ1234
    - Usar `Random` para gerar letras (A-Z) e números (0-9) aleatórios
    - Se prefixo for vazio ou null, usar apenas letras e números
- Criar método `gerarSemPrefixo()` que gera apenas 8 caracteres aleatórios
- 
**Exemplos de saída:**
```
PROMOXYWZ1234
DESCABCD5678
OFERTAGHIJ9012
```

---

### **Task 36 - Gerador de Hash de Senha**
- Criar classe `GeradorHashSenha` com métodos estáticos:
    - `gerar(String senha)` → retorna o hash da senha como String
    - `verificar(String senha, String hash)` → verifica se a senha corresponde ao hash
- **Funcionalidade:**
    - Gerar hash simples usando o método `hashCode()` da String
    - Converter o hash para valor absoluto usando `Math.abs()` (sempre positivo)
    - Converter o resultado para String
    - O método verificar deve gerar o hash da senha e comparar com o hash fornecido
- **IMPORTANTE:** Explicar nos comentários que este é um método simples para fins didáticos e que em produção deve-se usar algoritmos como BCrypt ou SHA-256
-  de 3 senhas diferentes
    - Verificação de senha correta
    - Verificação de senha incorreta

**Exemplo de uso:**
```java
String hash = GeradorHashSenha.gerar("senha123");
boolean valida = GeradorHashSenha.verificar("senha123", hash); // true
boolean invalida = GeradorHashSenha.verificar("outrasenha", hash); // false
```

---

## 🧮 CALCULADORAS (Tasks 37 a 40)

### **Task 37 - Calculadora de Pedido**
- Criar classe `CalculadoraPedido` com métodos estáticos:
    - `calcularSubtotal(ArrayList<Double> precosProdutos)` → soma todos os preços dos produtos
    - `aplicarDesconto(double subtotal, double desconto)` → subtotal - desconto (não pode ficar negativo)
    - `adicionarTaxa(double valor, double taxa)` → valor + taxa
    - `calcularTotal(double subtotal, double desconto, double taxa)` → (subtotal - desconto) + taxa
- **Regras:**
    - O desconto não pode ser maior que o subtotal
    - Todos os valores devem ser positivos ou zero
    - Retornar valores com 2 casas decimais
- s de 4 produtos
    - Aplicar desconto de R$ 5,00
    - Adicionar taxa de entrega de R$ 8,00
    - Mostrar subtotal, desconto, taxa e total final

**Exemplo de saída:**
```
Produtos: R$ 50,00
Desconto: R$ 5,00
Taxa de Entrega: R$ 8,00
TOTAL: R$ 53,00
```

---

### **Task 38 - Calculadora de Desconto de Cupom**
- Criar classe `CalculadoraDescontoCupom` com métodos estáticos:
    - `calcularDescontoPercentual(double valorPedido, double percentual)` → retorna o valor do desconto
    - `calcularDescontoValorFixo(double valorPedido, double valorDesconto)` → retorna o valor do desconto (limitado ao valor do pedido)
    - `aplicarDesconto(double valorPedido, String tipoDesconto, double valorDesconto)` → aplica o desconto correto baseado no tipo
- **Regras:**
    - Percentual deve estar entre 0 e 100
    - Desconto percentual: valorPedido * (percentual / 100)
    - Desconto valor fixo: não pode ser maior que o valor do pedido
    - Se tipoDesconto for "percentual", usar o método de percentual
    - Se tipoDesconto for "valor_fixo", usar o método de valor fixo
-  pedido de R$ 100,00
    - Cupom de 15% em pedido de R$ 50,00
    - Cupom de R$ 20,00 em pedido de R$ 100,00
    - Cupom de R$ 20,00 em pedido de R$ 15,00 (desconto maior que o pedido)

**Exemplo de saída:**
```
Pedido: R$ 100,00
Cupom: 10% de desconto
Desconto: R$ 10,00
Total com desconto: R$ 90,00
```

---


### **Task 39 - Verificador de Valor Mínimo do Pedido**
- Criar classe `VerificadorValorMinimo` com métodos estáticos:
    - `atingiuMinimo(double valorPedido, double valorMinimo)` → retorna true/false
    - `quantoFalta(double valorPedido, double valorMinimo)` → retorna quanto falta para atingir (0 se já atingiu)
    - `percentualAtingido(double valorPedido, double valorMinimo)` → retorna percentual de 0 a 100
    - `obterMensagem(double valorPedido, double valorMinimo)` → retorna mensagem amigável
- **Regras:**
    - Se valorPedido >= valorMinimo, o mínimo foi atingido
    - O percentual não pode passar de 100%
    - quantoFalta deve retornar 0 se já atingiu o mínimo
    - Valores devem ser positivos
- ,00 (acima do mínimo)
    - Pedido de R$ 30,00 (exatamente no mínimo)
    - Pedido de R$ 20,00 (abaixo do mínimo)
    - Pedido de R$ 15,00 (50% do mínimo)
    - Pedido de R$ 0,00 (vazio)

**Exemplo de saída:**
```
Valor do pedido: R$ 20,00
Valor mínimo: R$ 30,00
Status: NÃO ATINGIU o mínimo
Falta: R$ 10,00
Percentual atingido: 66.67%

Valor do pedido: R$ 50,00
Valor mínimo: R$ 30,00
Status: Mínimo ATINGIDO ✓
Você pode finalizar o pedido!
```

---

## 📝 OBSERVAÇÕES IMPORTANTES PARA TODOS OS ALUNOS

### **Padrões de Código:**
1. **Nomenclatura:**
    - Classes: PascalCase (ex: `ValidadorCPF`, `CalculadoraPedido`)
    - Métodos: camelCase (ex: `calcularTotal`, `validar`)
    - Variáveis: camelCase (ex: `valorTotal`, `numeroPedido`)
    - Constantes: UPPER_SNAKE_CASE (ex: `VALOR_MINIMO`, `TAXA_PADRAO`)
