# Selenide: как устроены тесты

## Где лежат тесты и конфиг?

Тесты — в `src/test/java/qa/aifirst/`: `TestBase.java`,
`LoginTest.java` (успешный вход, чекбокс «Запомнить меня»),
`LoginNegativeTest.java` (неверная пара, пустые поля; ADR-0007).
Версии стека (Java, JUnit, Selenide, Allure) — в `build.gradle`
(`toolchain` и `dependencies`), здесь не дублируются.

## Зачем TestBase и что в нём?

`TestBase` — общий базовый класс: `LoginTest extends TestBase`.
В `@BeforeAll setUp()` две настройки:

- `Configuration.headless = true` — браузер без окна, тест работает
  на CI без дисплея;
- `SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true))` —
  лисенер прикрепляет скриншот к шагам в Allure (см. `allure.md`).

Не дублируй эту настройку в тестах — конвенция зафиксирована
в `.devin/rules/tests.md`.

## Откуда тест берёт страницу?

`open("https://svasenkov.github.io/ai-first-simple/index.html")` —
тест открывает форму, опубликованную на GitHub Pages из `main`
(ADR-0006). Локальные правки `index.html` прогон не увидит, пока
они не уехали в `main` и Pages не перевыложил страницу.

## Как выглядят шаги теста?

Selenide-стиль: `$("css")` находит элемент, `.setValue()/.click()` —
действия, `.shouldHave(text("..."))` — проверка. Весь сценарий
из `LoginTest.successfulLogin`:

```java
open("https://svasenkov.github.io/ai-first-simple/index.html");
$("#username").setValue("admin");
$("#password").setValue("admin123");
$("button[type=submit]").click();
$("#message").shouldHave(text("Вход выполнен успешно"));
```

Локаторы и тексты берутся из `index.html` (`#username`, `#password`,
`#remember`, `button[type=submit]`, `#message`), валидные креды —
`admin`/`admin123`. Ошибки проверяются по тексту и классу:
`$("#message").shouldHave(exactText("Введите пароль"), cssClass("error"))`.
Пустое поле — просто не заполнять его; пустая строка в `@CsvSource` — `''`.

## Почему на Windows падают сравнения русского текста?

javac читает исходники в кодировке ОС (cp1251) и портит строки.
Лечится `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8` и пересборкой
тестов (`./gradlew cleanCompileTestJava test`). На CI (Linux) проблемы нет.

## Где ожидания? Почему нет sleep/wait?

Явных ожиданий нет и не нужно: Selenide сам ждёт элементы и условия
до таймаута (по умолчанию 4 с) — `shouldHave`, `click`, `setValue`
уже «умные». `Thread.sleep` и `WebDriverWait` в проекте не используются.

## Как запустить?

`./gradlew test` — Gradle Wrapper в git, установленный Gradle не нужен.
Драйвер браузера скачивает Selenium Manager автоматически.

## Как добавить новый тест?

Класс в `src/test/java/qa/aifirst/`, наследование от `TestBase`,
один метод = один сценарий, `@DisplayName` на русском. Процедура —
skill `.devin/skills/add-ui-test/SKILL.md`.
