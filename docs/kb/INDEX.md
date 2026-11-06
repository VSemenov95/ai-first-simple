# База знаний — индекс

Точка входа в `docs/kb/`: найди тему в таблице и открой только нужный
файл — не читай всю базу подряд. Формат каждого файла: «вопрос →
короткий ответ с примером из этого репозитория».

| Файл | Какие вопросы покрывает |
| --- | --- |
| [selenide.md](selenide.md) | Как устроены UI-тесты: TestBase, headless, `open()` на GitHub Pages, `$`/`shouldHave`, проверка ошибок формы и пустых полей, UTF-8 на Windows, где ожидания, где конфиг Selenide и версии |
| [github-actions.md](github-actions.md) | Как устроен `ci.yml`: триггеры, шаги job `test`, pinned-экшены по SHA, версия Allure CLI, артефакт `allure-report`, бейдж, где логи |
| [allure.md](allure.md) | Результаты vs отчёт: `build/allure-results` vs `build/allure-report`, `allure serve` vs `allure generate`, лисенер allure-selenide и скриншоты при падении |
| [jenkins.md](jenkins.md) | Зачем Jenkins в этом репо (фаза 7), готовый сервер jenkins.qa.guru (локально поднимать не нужно), какие плагины нужны (Pipeline, Allure) |

Если по таблице не ясно, куда смотреть, — grep по `docs/kb/` по
ключевым словам запроса, затем открой один-два подходящих файла.
