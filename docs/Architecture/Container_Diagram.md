# C4 Container Diagram
## VK-Book Android

```mermaid
C4Container
    title Контейнерная диаграмма приложения «VK-Book Android»

    Person(worker, "Сменный персонал")

    System_Ext(backend, "VK-Book Server", "Spring Boot API")

    Container_Boundary(mobile_app, "VK-Book Android App") {
        Container(main_activity, "MainActivity", "Kotlin", "Вкладки, общий поиск, кнопка обновления")
        Container(table_fragments, "ArmatureFragment / KipFragment / DataFragment", "Kotlin", "Справочники оборудования")
        Container(pdf_viewer, "PdfViewerActivity", "Kotlin", "Схемы с отметками")
        Container(checks_fragment, "ChecksScheduleFragment", "Kotlin", "График проверок")
        Container(schedule_fragment, "ScheduleFragment", "Kotlin", "График смен")
        Container(editor_fragment, "EditorFragment", "Kotlin", "Редактор (администратор)")

        Container(search, "PersistentSearchEngine", "Kotlin", "Поисковый индекс")
        Container(upload, "EditorUploadService", "Kotlin", "Отправка баз на сервер")
        Container(sync, "ManualSyncWorker / SyncService", "WorkManager", "Обновление баз, SHA-256")
        Container(reminders, "ShiftReminderScheduler", "AlarmManager", "Напоминания о проверках")
        Container(network, "NetworkModule", "Retrofit/OkHttp", "Сетевой слой")

        ContainerDb(files, "Файлы баз", "File System", "Excel, PDF, JSON")
        ContainerDb(index, "Индекс и кэш", "File System", "Поисковый индекс, кэш таблиц")
    }

    Rel(worker, main_activity, "Использует")
    Rel(main_activity, table_fragments, "Вкладки")
    Rel(main_activity, checks_fragment, "Вкладки")
    Rel(main_activity, schedule_fragment, "Вкладки")
    Rel(main_activity, editor_fragment, "Вкладки")
    Rel(table_fragments, pdf_viewer, "Открыть схему")

    Rel(table_fragments, search, "Поиск")
    Rel(search, index, "Чтение/запись")
    Rel(table_fragments, files, "Чтение")
    Rel(checks_fragment, files, "Чтение")
    Rel(reminders, files, "График проверок")

    Rel(main_activity, sync, "Запуск обновления")
    Rel(sync, network, "Использует")
    Rel(sync, files, "Атомарная замена")
    Rel(editor_fragment, upload, "Отправка баз")
    Rel(upload, network, "Использует")
    Rel(network, backend, "REST API", "HTTPS")
```

## Описание контейнеров

### MainActivity
Главный экран: вкладки (ViewPager2), общая строка поиска для справочников, кнопка обновления баз.

### Экраны
- **ArmatureFragment / KipFragment / DataFragment** — справочники «Арматура», «КИП» и «Сигналы БЩУ» на общей табличной основе; переход к схеме по нажатию
- **PdfViewerActivity** — просмотр плана или схемы с отметкой оборудования
- **ChecksScheduleFragment** — график проверок
- **ScheduleFragment** — график смен
- **EditorFragment** — редактор баз и отметок (только администратор)

### Сервисы
- **PersistentSearchEngine** — собственный поисковый индекс на устройстве
- **EditorUploadService** — отправка отредактированных баз на сервер с повторными попытками
- **ManualSyncWorker / SyncService** — фоновое обновление баз с проверкой SHA-256
- **ShiftReminderScheduler** — планирование напоминаний о проверках смены
- **NetworkModule** — сетевой слой (Retrofit, OkHttp)

### Хранилища
- **Файлы баз** — Excel, PDF и JSON с координатами отметок
- **Индекс и кэш** — поисковый индекс и постраничный кэш таблиц
