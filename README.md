# Spring Boot Starter для логирования HTTP-запросов

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen.svg)](https://spring.io/projects/spring-boot)

Простой стартер для логирования входящих HTTP-запросов и статусов ответов в Spring Boot приложениях.

## Основные возможности

- 🕵️ Логирование метода и URL запроса (GET /example, POST /example и т.д.)
- ✅ Логирование HTTP-статуса ответа (200, 404, 500 и др.)
- ⚙️ Настройка уровня логирования (INFO, DEBUG, WARN, ERROR)
- 🔌 Автоматическое подключение через зависимость Maven/Gradle

## Быстрый старт

### 1. Подключите зависимость

Добавьте в `build.gradle`:
```gradle
dependencies {
    implementation 'org.kmuradoff:http-logging-starter:1.0.0'
}
```

Или для Maven `pom.xml`:

```maven
<dependency>
    <groupId>org.kmuradoff</groupId>
    <artifactId>http-logging-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2. Настройте (опционально)

Добавьте параметры в `application.yml` (или `application.properties`):

```yaml
t1.logging:
  enabled: true    # Включить логирование (по умолчанию: true)
  level: INFO      # Доступные уровни: DEBUG, INFO, WARN, ERROR
```

Или для ```.properties```:
```properties
t1.logging.enabled=true
t1.logging.level=INFO
```

