<h1 align="center">AURA Risk Engine</h1>

<p align="center">

<img alt="GitHub issues" src="https://img.shields.io/github/issues-raw/KonstantinLi/aura-risk">
<img alt="GitHub watchers" src="https://img.shields.io/github/watchers/KonstantinLi/aura-risk">
<img alt="GitHub contributors" src="https://img.shields.io/github/contributors/KonstantinLi/aura-risk">
<img alt="GitHub labels" src="https://img.shields.io/github/labels/KonstantinLi/aura-risk/help%20wanted">
<img alt="GitHub labels" src="https://img.shields.io/github/labels/KonstantinLi/aura-risk/invalid">
<img alt="GitHub labels" src="https://img.shields.io/github/labels/KonstantinLi/aura-risk/bug">

</p>

**AURA Risk Engine** is a risk evaluation microservice that provides real-time user risk scoring for adaptive authentication. It integrates with `aura-auth` to enforce step-up authentication and works with `aura-realtime` to supply risk context for event-driven dashboards.

---

## Features

* **Real-Time Risk Scoring**: Calculates a risk score for each user session based on contextual and behavioral factors.
* **Adaptive Authentication Support**: Notifies `aura-auth` when step-up verification is required.
* **Risk Profile Management**: Maintains per-user risk profiles including trusted devices, behavioral baselines, and geolocation context.
* **Event-Driven Architecture**: Consumes events from Kafka, enriches them, evaluates risk, and publishes results for other services.
* **Persistence & Caching**: Uses R2DBC for persistent storage and Redis for fast risk context caching.
* **High Availability**: Designed for distributed environments with failover and scaling support.
* **Metrics & Observability**: Provides monitoring for event processing latency, risk calculations, and alerts.

---

## Architecture

```
Event Sources ──▶ Kafka Consumer ──▶ RiskPipeline ──▶ RiskEvaluator ──▶ ContextRepository/Cache
                                                     │
                                                     ├─▶ StepUpNotifier ──▶ Aura Auth
                                                     └─▶ Event Publisher ──▶ Aura Realtime
```

* **Config**: Kafka, Redis, DB, Vault, and service-specific configuration
* **DTO**: Models for incoming events, risk contexts, and risk scoring results
* **Ingestion**: Kafka listeners and pre-processing of events
* **Enrichment**: Adds contextual data (geolocation, device, historical profiles)
* **Evaluation**: Computes risk scores using weighted factors:

    * Geolocation – 20%
    * Time interval – 10%
    * Failed attempts – 20%
    * Device – 30%
    * Behavior – 20%
* **Pipeline**: Orchestrates event flow through enrich → score → persist → alert stages
* **Repository**: CRUD operations for risk context and user profiles
* **Publish**: Sends risk results to Kafka and triggers notifications for step-up
* **Observability**: Metrics, logging, and alerting

---

## Configuration

Configurable parameters in `application.yml` include:

* Kafka brokers, topics, and consumer groups
* Redis connection for caching risk context
* Vault secret paths for encryption keys
* Risk factor weights and threshold levels (LOW/MEDIUM/HIGH)
* Database connection settings

---

## Security

* Risk-sensitive data is stored encrypted using Vault-managed keys
* Redis cache access is restricted and ephemeral
* Step-up notifications use secure channels to `aura-auth`
* Risk calculations are deterministic but do not expose sensitive user data

---

## Observability

* Metrics via Prometheus for event ingestion rate, pipeline latency, and processing errors
* Logging for enrichment, evaluation, and pipeline stages
* Alerts for processing failures or threshold breaches