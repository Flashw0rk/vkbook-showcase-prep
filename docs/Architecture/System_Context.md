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
    title Контекстная схема системы «VkBook»

    Person(worker, "Персонал предприятия", "Оператор/обходчик: контроль оборудования,<br/>работа с регламентами и схемами.")

    Boundary(mobile_env, "Мобильная среда (Offline-first)") {
        System(vkbook, "VK-Book Android", "Kotlin/Material 3: Интеллектуальная оболочка<br/>цифровой трансформации.")
        
        System_Ext(sqlite, "Local Cache (SQLite)", "Кэширование данных (Local Sync).<br/>Локальное хранилище данных для работы в зонах без связи.")
    }

    System_Ext(backend, "VK-Book Server", "Spring Boot API: Синхронизация данных,<br/>управление сессиями.")
    System_Ext(excel, "Big Data Source (Excel)", "Техническая документация и массивы<br/>данных оборудования (POI).")

    Rel_D(worker, vkbook, "Управление задачами", "UI/UX")
    Rel_D(vkbook, sqlite, " ", " ")
    
    Rel_R(vkbook, backend, "Синхронизация", "JSON/HTTPS")
    Rel_D(backend, excel, "Импорт данных", "Apache POI")

    UpdateElementStyle(worker, $bgColor="#0052cc", $fontColor="#ffffff", $borderColor="#002e73")
    UpdateElementStyle(vkbook, $bgColor="#15aabf", $fontColor="#ffffff", $borderColor="#0b7285")
    UpdateElementStyle(backend, $bgColor="#40c057", $fontColor="#ffffff", $borderColor="#2b8a3e")
    UpdateElementStyle(excel, $bgColor="#fab005", $fontColor="#000000", $borderColor="#f59f00")
    UpdateElementStyle(sqlite, $bgColor="#868e96", $fontColor="#ffffff", $borderColor="#343a40")

    UpdateLayoutConfig($c4ShapeInRow="2", $c4BoundaryInRow="1")
```

## Описание компонентов

### Персонал предприятия
Операторы и обходчики, которые используют приложение для работы с оборудованием, регламентами и схемами.

### VK-Book Android
Мобильное приложение на Kotlin с Material 3, обеспечивающее цифровую трансформацию рабочих процессов.

### Local Cache (SQLite)
Локальное хранилище данных для работы в офлайн-режиме в экранированных зонах без связи.

### VK-Book Server
Backend API на Spring Boot для синхронизации данных и управления сессиями.

### Big Data Source (Excel)
Источник технических данных в формате Excel, обрабатываемых через Apache POI.
