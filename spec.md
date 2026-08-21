# spec.md

Журнал змін і стан проєкту для наступних сесій Claude Code. `CLAUDE.md` описує
архітектуру та правила, цей файл — що вже зроблено сесія за сесією і що далі. Оновлювати
після кожної завершеної сесії (дата, що зроблено, наступний крок).

## Поточний стан

Каркас проєкту готовий і працює: Maven/Spring Boot 3.3 (Java 21), шарувата архітектура,
референс-фіча `Task` (CRUD) на всіх шарах, тести проходять.

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
