# C4 System Context Diagram
## VK-Book Android

```mermaid
%%{init: {
  'theme': 'base',
  'themeVariables': {
    'primaryColor': '#f8f9fa',
    'primaryTextColor': '#212529',
    'primaryBorderColor': '#adb5bd',
    'lineColor': '#495057',
    'secondaryColor': '#e9ecef',
    'tertiaryColor': '#ffffff',
    'fontFamily': 'Segoe UI, Roboto, sans-serif',
    'fontSize': '24px'
  }
}}%%
C4Context
    title Контекстная схема системы «VK-Book»

    Person(worker, "Сменный персонал", "Оператор/обходчик: справочники,<br/>схемы, проверки, график смен.")
    Person(admin, "Администратор", "Редактирование баз<br/>и отметок на схемах.")

    Boundary(mobile_env, "Мобильная среда (Offline-first)") {
        System(vkbook, "VK-Book Android", "Kotlin: справочники, схемы,<br/>графики, напоминания.")
        System_Ext(storage, "Локальное хранилище", "Файлы баз, поисковый индекс,<br/>кэш Excel. Работа без связи.")
    }

    System_Ext(backend, "VK-Book Server", "Spring Boot: раздача и приём баз,<br/>метаданные версий, панель администратора.")
    System_Ext(files, "Файловое хранилище", "Excel-базы, PDF-схемы,<br/>координаты отметок (JSON).")

    Rel_D(worker, vkbook, "Работа в смене", "UI")
    Rel_D(admin, vkbook, "Редактирование", "UI")
    Rel_D(vkbook, storage, " ", " ")
    Rel_R(vkbook, backend, "Синхронизация", "JSON/HTTPS")
    Rel_D(backend, files, "Хранение", "S3 API")

    UpdateElementStyle(worker, $bgColor="#0052cc", $fontColor="#ffffff", $borderColor="#002e73")
    UpdateElementStyle(admin, $bgColor="#5f3dc4", $fontColor="#ffffff", $borderColor="#3b2483")
    UpdateElementStyle(vkbook, $bgColor="#15aabf", $fontColor="#ffffff", $borderColor="#0b7285")
    UpdateElementStyle(backend, $bgColor="#40c057", $fontColor="#ffffff", $borderColor="#2b8a3e")
    UpdateElementStyle(files, $bgColor="#fab005", $fontColor="#000000", $borderColor="#f59f00")
    UpdateElementStyle(storage, $bgColor="#868e96", $fontColor="#ffffff", $borderColor="#343a40")

    UpdateLayoutConfig($c4ShapeInRow="2", $c4BoundaryInRow="1")
```

## Описание компонентов

### Сменный персонал
Операторы и обходчики: поиск оборудования, схемы, график проверок, напоминания, график смен.

### Администратор
Редактирует справочники и отметки на схемах, публикует обновлённые базы на сервер. Доступ защищён паролем.

### VK-Book Android
Мобильное приложение на Kotlin. Все рабочие функции доступны без связи.

### Локальное хранилище
Файлы баз (Excel, PDF, JSON), собственный поисковый индекс и постраничный кэш таблиц на устройстве.

### VK-Book Server
Серверная часть на Spring Boot: список и раздача файлов, метаданные версий для проверки обновлений, приём баз от администратора, веб-панель управления.

### Файловое хранилище
S3-совместимое хранилище исходных файлов баз.
