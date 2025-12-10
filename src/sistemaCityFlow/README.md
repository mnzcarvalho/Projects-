# 🚦 CityFlow - Simulador de Tráfego Urbano

O Simulador de Tráfego Urbano (CityFlow) é um dos temas mais ricos pra estudar Java, porque permite aplicar POO, exceções, threads, polimorfismo, classes utilitárias, datas, IO e até deadlocks, tudo de forma orgânica e divertida.

## 🧠 Conceito Geral

O sistema CityFlow simula o tráfego de uma cidade. Os veículos (carros, motos, ônibus, caminhões) são threads que circulam pelas vias e cruzamentos. Os semáforos controlam o fluxo e são compartilhados entre threads, permitindo estudar concorrência, sincronização e deadlocks.

## 🎯 Objetivo do Projeto

Desenvolver um sistema que:

- Crie e gerencie veículos e semáforos;
- Controle o fluxo do tráfego usando threads;
- Monitore eventos (engarrafamentos, acidentes, infrações);
- Gere relatórios (via IO);
- Use polimorfismo e abstração para diferentes tipos de veículo;
- Trabalhe com datas e tempos de viagem (usando LocalTime, Duration, etc.);
- Trate exceções personalizadas (ex: SemaforoForaDeServicoException);
- Utilize Regex, Locale e arquivos em partes secundárias (logs, placas, mensagens).

## 🧩 Estrutura de Pacotes
```
br.com.cityflow
│
├── app
│ └── desafioCadastro.Main.java
│
├── model
│ ├── Veiculo.java
│ ├── Carro.java
│ ├── Moto.java
│ ├── Onibus.java
│ ├── Caminhao.java
│ ├── Semaforo.java
│ ├── Cruzamento.java
│ ├── Rua.java
│ ├── Cidade.java
│ ├── TipoCombustivel.java (enum)
│ ├── Direcao.java (enum)
│ └── EstadoVeiculo.java (enum)
│
├── service
│ ├── TrafegoService.java
│ ├── MonitoramentoService.java
│ ├── RelatorioService.java
│ └── LogService.java
│
├── exception
│ ├── AcidenteException.java
│ ├── SemaforoForaDeServicoException.java
│ └── VeiculoSemCombustivelException.java
│
├── util
│ ├── DataUtils.java
│ ├── RegexUtils.java
│ ├── IOUtils.java
│ ├── LocaleUtils.java
│ └── RandomUtils.java
│
└── resources
├── logs/
├── relatorios/
├── messages_pt.properties
└── messages_en.properties 
```

## 🧱 Aplicação dos Conceitos Java

| Conceito | Aplicação prática |
|----------|-------------------|
| **Classes / Métodos / Sobrecarga** | Classes de veículos e métodos como `mover()`, `frear()`, `abastecer()` |
| **Construtores / Blocos / Estático** | ID automático de veículo, contador de instâncias |
| **Associação** | Veiculo → Rua → Cruzamento → Semaforo |
| **Herança / Polimorfismo** | Subclasses de Veiculo com comportamentos diferentes |
| **Classes abstratas / Interfaces** | Veiculo abstrato e interface Movimentavel |
| **Enum** | Direcao, TipoCombustivel, EstadoVeiculo |
| **Final / Constantes** | Velocidade máxima, tempos de semáforo |
| **Exception** | Exceções para acidentes, combustível, falha de semáforo |
| **Wrappers / Strings / StringBuilder** | Geração de relatórios e mensagens formatadas |
| **Date/Time API** | Duração da viagem, tempo parado, logs |
| **Locale / ResourceBundle** | Mensagens multilíngue no console |
| **Regex** | Validação de placas (ex: "ABC-1234") |
| **Scanner** | Configuração inicial da simulação |
| **IO (FileWriter, BufferedReader)** | Logs e relatórios do tráfego |
| **Threads / Deadlock / Starvation** | Semáforos compartilhados entre veículos |
| **CompletableFuture** (mais tarde) | Simulação assíncrona de múltiplas regiões da cidade |

## ⚙️ Funcionalidades Iniciais

- Criar veículos e iniciar a simulação (cada veículo é uma thread);
- Controlar semáforos com alternância de estados (verde, amarelo, vermelho);
- Registrar logs de eventos (tempo de espera, infrações, falhas);
- Tratar exceções específicas (ex: semáforo quebrado);
- Calcular tempo de viagem (Duration);
- Gerar relatórios (RelatorioService);
- Permitir mudar idioma (Português/Inglês).

## 🚦 Evoluções Futuras

- Adicionar colisões aleatórias com RandomUtils;
- Implementar sistema de prioridades (ônibus e ambulâncias);
- Introduzir deadlock proposital entre veículos em um cruzamento;
- Adicionar menu interativo via Scanner;
- Simular climas ou horários com impacto no tráfego;
- Exportar logs para .txt ou .csv.