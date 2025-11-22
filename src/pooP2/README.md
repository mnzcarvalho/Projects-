# 🏪 Sistema - Loja 

💡 Conceitos Implementados
1. 📊 Estruturas Lógicas
Onde: Loja.java (métodos de busca), Main.java (menu switch)

Implementação: Loops for, while, switch, if/else em validações e buscas

2. 🏗️ Construtores
Onde: Todas as classes em com.loja.model

Implementação: 2 construtores por classe (vazio e com parâmetros)

3. 🔒 Encapsulamento
Onde: Todas as classes do pacote model

Implementação: Atributos privados + gets/sets com validações

4. 🌳 Herança + Abstract
Onde:

Classe abstrata: Pessoa.java

Classes filhas: Cliente.java, Funcionario.java, Fornecedor.java, Gerente.java

Implementação: Herança simples e múltipla, métodos abstratos

5. 🔄 Polimorfismo + ArrayList + Relacionamentos
Onde: Loja.java e Pedido.java

Implementação:

ArrayList polimórfico: List<Pessoa>

Relacionamento 1:N: Loja → Pedidos

Relacionamento N:N: Pedido → Produtos

Sobrescrita com @Override

6. 💾 Serialização de Objetos
Onde: Loja.java e todas as classes do pacote model

Implementação: Serializable, salvar/carregar em loja.dat

7. ⚠️ Exception + Entrada/Saída
Onde: Pacote exception e Main.java

Implementação: 4 exceptions customizadas, try/catch, leitura/gravação de arquivos

🎯 Funcionalidades
✅ Cadastro de Clientes, Funcionários, Fornecedores

✅ Gestão de Produtos e Estoque

✅ Realização de Pedidos

✅ Relatórios de Vendas

✅ Persistência de Dados

✅ Validações de CPF e Estoque

🔄 Fluxo do Sistema
Inicialização: Carrega dados do arquivo loja.dat

Menu Interativo: Navegação por opções numéricas

Operações: CRUD completo para todas entidades

Persistência: Salva automaticamente ao sair
