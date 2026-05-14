# Event-Driven Anomaly Detection System

Sistema de detecção de anomalias em tempo real para monitoramento de pipelines industriais, utilizando arquitetura orientada a eventos e práticas de Site Reliability Engineering (SRE).

## 📋 Visão Geral

Este projeto simula um ambiente industrial onde sensores monitoram um pipeline de petróleo/gás e detectam anomalias (especialmente vazamentos) em tempo real usando streaming de eventos.

**Inspirado em:** Sistemas de monitoramento usados em indústrias pesadas (petróleo, gás, utilities) e práticas modernas de observabilidade.

## 🎯 Objetivos

- Simular sensores industriais com comportamento realista
- Detectar anomalias em tempo real usando streaming
- Demonstrar arquitetura event-driven escalável
- Implementar práticas de SRE (observabilidade, confiabilidade)
- Correlacionar eventos de múltiplos sensores

## 🏗️ Arquitetura
```
┌─────────────────────┐
│ Sensor Simulator    │ (Java + Spring Boot)
│ - Pressure          │
│ - Flow              │
│ - Temperature       │
└──────────┬──────────┘
           │ Kafka Topics
           ▼
┌─────────────────────┐
│ Edge Gateway        │ (Validação + Normalização)
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Apache Kafka        │ (Event Backbone)
│ - pipeline.pressure │
│ - pipeline.flow     │
│ - pipeline.temp     │
└──────────┬──────────┘
           │
           ├──────────────────┐
           ▼                  ▼
┌──────────────────┐  ┌─────────────────┐
│ Anomaly Detector │  │ Storage Writer  │
│ (Kafka Streams)  │  │ (InfluxDB)      │
└────────┬─────────┘  └─────────────────┘
         │
         ▼
┌─────────────────────┐
│ Incident Service    │
│ + Alerting          │
└─────────────────────┘
         │
         ▼
┌─────────────────────┐
│ Observability Stack │
│ - Prometheus        │
│ - Grafana           │
│ - OpenTelemetry     │
└─────────────────────┘
```
## 🔧 Componentes

### 1. Sensor Simulator Service

Simula sensores industriais gerando dados de telemetria realistas.

**Tecnologias:**
- Java 17+
- Spring Boot 3.x
- Kafka Producer

**Funcionalidades:**
- Geração contínua de eventos (1 evento/segundo por sensor)
- Ruído Gaussiano (variações naturais)
- Ruído Perlin (variações suaves e contínuas)
- Cenários de simulação:
  - ✅ Operação normal
  - ⚠️ Vazamento gradual
  - 🔥 Falha de sensor
  - 📉 Instabilidade no fluxo

**Estrutura de evento:**

```json
{
  "sensorId": "segment-1-pressure",
  "type": "PRESSURE",
  "value": 94.2,
  "timestamp": 1710000000,
  "segment": "segment-1",
  "metadata": {}
}
```

### 2. Edge Gateway Service
Recebe dados dos sensores, valida e normaliza antes de publicar no Kafka.

Tecnologias:

Spring Boot
Resilience4j (Circuit Breaker, Retry)
Kafka Producer
Responsabilidades:

Validação de dados (range, formato)
Normalização de unidades
Enriquecimento de metadados
Resiliência (retry, fallback)

### 3. Anomaly Detection Service
Processa streams de eventos e detecta anomalias em tempo real.

Tecnologias:

Kafka Streams / Apache Flink
Apache Commons Math (estatística)
Métodos de detecção:

Moving Average
Z-Score (desvio padrão)
Change Detection (variação abrupta)
Correlação entre sensores

**Exemplo de lógica:**

Se (pressão cai 15% E fluxo cai 10%) → VAZAMENTO

### 4. Storage Writer Service
Persiste dados históricos em banco de séries temporais.

Tecnologias:

InfluxDB ou TimescaleDB
Kafka Consumer
Armazena:

Histórico de sensores
Eventos de anomalia
Métricas operacionais

### 5. Incident Service
Gerencia incidentes detectados e dispara alertas.

**Funcionalidades:**

- Criação de incidentes
- Agregação de eventos relacionados
- Integração com Alertmanager
- API REST para consulta

## 📊 Observabilidade

- Métricas (Prometheus)
- Taxa de eventos/segundo por sensor
- Latência de processamento
- Kafka consumer lag
- Taxa de anomalias detectadas
- Dashboards (Grafana)
- Pressão por segmento do pipeline
- Fluxo ao longo do tempo
- Eventos de anomalia (timeline)
- Latência do sistema (p50, p95, p99)
- Throughput de eventos
- Tracing (OpenTelemetry)
- Rastreamento distribuído de eventos
- Latência por componente
- Correlação de requisições
- Logs (Loki/ELK)
- Logs estruturados (JSON)
- Correlação por trace-id
- Alertas baseados em padrões
  
## 🚀 Como Executar

Pré-requisitos
Java 17+
Docker & Docker Compose
Gradle 8+


## 🛠️ Tecnologias
| Camada | Tecnologia |
|--------|-----------|
| **Linguagem** | Java 21 |
| **Framework** | Spring Boot 3.x |
| **Messaging** | Apache Kafka |
| **Streaming** | Kafka Streams |
| **Database** | InfluxDB / TimescaleDB |
| **Metrics** | Prometheus + Micrometer |
| **Dashboards** | Grafana |
| **Tracing** | OpenTelemetry |
| **Logs** | Logback + Loki |
| **Containers** | Docker + Docker Compose |
| **Orchestration** | Kubernetes (opcional) |
