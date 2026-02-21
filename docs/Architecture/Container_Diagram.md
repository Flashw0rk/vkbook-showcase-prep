# C4 Container Diagram
## VK-Book Android

```mermaid
C4Container
    title Контейнерная диаграмма системы «VkBook»

    Person(worker, "Персонал предприятия")

    System_Ext(backend, "VK-Book Server", "Spring Boot API")

    Container_Boundary(mobile_app, "VK-Book Android App") {
        Container(main_activity, "MainActivity", "Kotlin", "Главная активность с ViewPager")
        Container(armature_fragment, "ArmatureFragment", "Kotlin", "Модуль арматуры")
        Container(schemes_fragment, "SchemesFragment", "Kotlin", "Модуль схем")
        Container(checks_fragment, "ChecksScheduleFragment", "Kotlin", "График проверок")
        Container(schedule_fragment, "ScheduleFragment", "Kotlin", "График смен")
        Container(editor_fragment, "EditorFragment", "Kotlin", "Редактор данных")
        
        Container(repository, "ArmatureRepository", "Kotlin", "Репозиторий данных")
        Container(sync_service, "SyncService", "Kotlin", "Сервис синхронизации")
        Container(network_module, "NetworkModule", "Kotlin", "Сетевая логика")
        
        ContainerDb(sqlite_cache, "SQLite Cache", "SQLite", "Локальное хранилище")
        ContainerDb(file_cache, "File Cache", "File System", "Кэш файлов")
    }

    Rel(worker, main_activity, "Использует")
    Rel(main_activity, armature_fragment, "Навигация")
    Rel(main_activity, schemes_fragment, "Навигация")
    Rel(main_activity, checks_fragment, "Навигация")
    Rel(main_activity, schedule_fragment, "Навигация")
    Rel(main_activity, editor_fragment, "Навигация")
    
    Rel(armature_fragment, repository, "Запрос данных")
    Rel(schemes_fragment, repository, "Запрос данных")
    Rel(checks_fragment, repository, "Запрос данных")
    
    Rel(repository, sqlite_cache, "Чтение/запись")
    Rel(repository, file_cache, "Чтение/запись")
    Rel(repository, network_module, "Сетевые запросы")
    
    Rel(sync_service, network_module, "Использует")
    Rel(sync_service, file_cache, "Обновление кэша")
    Rel(network_module, backend, "REST API", "HTTPS")
```

## Описание контейнеров

### MainActivity
Главная активность приложения, управляющая навигацией между модулями через ViewPager2.

### Фрагменты
- **ArmatureFragment:** Модуль работы с арматурой
- **SchemesFragment:** Модуль просмотра схем
- **ChecksScheduleFragment:** Модуль графика проверок
- **ScheduleFragment:** Модуль графика смен
- **EditorFragment:** Модуль редактирования данных (административный)

### Репозиторий и сервисы
- **ArmatureRepository:** Абстракция работы с данными
- **SyncService:** Сервис синхронизации с сервером
- **NetworkModule:** Сетевая логика (Retrofit, OkHttp)

### Хранилища данных
- **SQLite Cache:** Локальная база данных для структурированных данных
- **File Cache:** Файловый кэш для Excel и PDF файлов
