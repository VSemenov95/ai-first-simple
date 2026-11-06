# 0008. Тесты открывают страницу с GitHub Pages форка

Дата: 2026-11-06
Статус: принято

## Context

ADR-0006 захардкодил в тестах URL Pages исходного репозитория
(`svasenkov.github.io`). Работа идёт в форке `VSemenov95/ai-first-simple`,
а мерж в форк не обновляет чужие Pages: CI в PR проверял бы старую
версию `index.html` и новые тесты (ADR-0007) были бы красными.

## Decision

Во всех тестах (`LoginTest`, `LoginNegativeTest`) и документации URL
заменён на `https://vsemenov95.github.io/ai-first-simple/index.html`.
Остальное из ADR-0006 в силе: один захардкоженный URL, без system
property. В форке должны быть включены GitHub Pages
(Settings → Pages → Deploy from a branch).

## Consequences

Тесты проверяют опубликованную версию форка. Пока Pages выключены или
собираются не с той ветки, прогон красный. Перед мержем в исходный
репозиторий URL нужно вернуть новым ADR.
