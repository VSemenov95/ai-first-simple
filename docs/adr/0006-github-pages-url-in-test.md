# 0006. Тест открывает страницу с GitHub Pages по захардкоженному URL

Дата: 2026-09-22
Статус: заменён ADR-0008 (URL страницы)

## Context

`TestBase` собирал `BASE_URL` из system property `-DbaseUrl` с фолбэком
на `file://index.html`: локально тест ходил в файл из checkout'а,
в Jenkins — на GitHub Pages (Selenoid не видит `file://`). Двойной
режим — лишняя сложность для учебного репо: страница и так всегда
опубликована на Pages, а механизм с property выглядит как
необъяснимая магия.

## Decision

`LoginTest` открывает страницу напрямую:
`open("https://svasenkov.github.io/ai-first-simple/index.html")`.
`BASE_URL` из `TestBase` и `-DbaseUrl` из `JAVA_TOOL_OPTIONS` в
`Jenkinsfile` удалены; `-Dselenide.remote` остаётся — Selenoid по-прежнему
нужен, локального Chrome на агентах нет.

## Consequences

Проще: один URL в одном месте, нет system property и двух режимов
запуска. Плата — тесты всегда проверяют опубликованную версию
страницы: правки `index.html` прогон не увидит, пока они не в `main`
и Pages не перевыложен. Локально это же ограничение уже действовало
в Jenkins — теперь оно единообразно везде. Для учебного репозитория
принимаем.
