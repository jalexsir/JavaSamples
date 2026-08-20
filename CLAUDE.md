# CLAUDE.md

Контекст проєкту для Claude Code. Читається автоматично на старті кожної сесії — тут стан
проєкту, прийняті рішення та журнал змін, щоб нова сесія могла продовжити розробку без
повторного дослідження коду з нуля.

## Що це за проєкт

Навчальний Spring Boot застосунок. Мета — не готовий продукт, а майданчик: кожна нова сесія
додає окрему фічу (JPA, Security, кешування, AOP, events, Testcontainers тощо) поверх наявної
архітектури, щоб на практиці вивчити, як Spring працює під капотом. Проєкт відкривається і
запускається в IntelliJ IDEA.

Стек: Java 21, Spring Boot 3.3, Maven (є `mvnw`), JUnit 5 + MockMvc.

## Архітектура

Шаруватий поділ, кожен шар — окремий пакет під `com.javasamples`:

```
web          — REST-контролери
service      — бізнес-логіка (інтерфейс + Impl)
repository   — доступ до даних (інтерфейс + Impl; зараз in-memory)
domain       — доменні моделі (без анотацій фреймворків)
dto          — record'и запиту/відповіді
exception    — власні винятки + @RestControllerAdvice
config       — @Configuration класи з бінами
```

Патерн для кожної нової фічі: `XController` → `XService`/`XServiceImpl` →
`XRepository`/`InMemoryXRepository` → `X` (domain) + `XRequest`/`XResponse` (dto).
Дивись реалізацію `Task` як референс.

## Команди

```bash
./mvnw spring-boot:run   # запуск (http://localhost:8080)
./mvnw test               # тести
./mvnw compile             # тільки компіляція
```

Health-check: `GET /actuator/health`.

## Правила для нових сесій

- Кожна нова фіча — окремий комміт(и) з описовим повідомленням.
- Дотримуватись наявної шаруватої структури, не змішувати шари (контролер не звертається
  напряму до репозиторію).
- Репозиторії — спершу in-memory (`ConcurrentHashMap`), як `InMemoryTaskRepository`; перехід
  на JPA — окрема майбутня фіча, а не побічний ефект іншої задачі.
- Мінімум коментарів у коді; пояснення "чому" — тут, у CLAUDE.md, або в описі комміту.
- Після кожної завершеної сесії — додати запис у розділ "Журнал змін" нижче (дата, що
  зроблено, що далі) і за потреби оновити README.md.

## Журнал змін

### 2026-08-20 — Каркас проєкту
- Створено Maven/Spring Boot 3.3 скелет (Java 21), шарувата архітектура
  (`web/service/repository/domain/dto/exception/config`).
- Референс-фіча `Task` (CRUD): `TaskController` → `TaskService` → `TaskRepository`,
  валідація через `@Valid`, обробка помилок через `GlobalExceptionHandler`.
- Демонстрація DI: `Clock` як окремий біна (`ClockConfig`), інжектиться в сервіс.
- Тести: `TaskServiceImplTest` (unit), `TaskControllerTest` (MockMvc) — усі проходять.
- `application.yml` з увімкненим `actuator health`, Maven Wrapper, `.gitignore`.
- README оновлено: інструкція запуску в IntelliJ IDEA, опис архітектури, API-таблиця.

**Наступний крок (ще не зроблено):** перша "реальна" тема під вивчення — наприклад
JPA/Hibernate (замінити in-memory репозиторій на Spring Data JPA + H2/Postgres) або
Spring Security. Обрати одну тему за сесію.
