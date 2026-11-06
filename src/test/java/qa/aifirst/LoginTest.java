package qa.aifirst;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class LoginTest extends TestBase {

    @Test
    @DisplayName("Успешный вход с валидными учётными данными")
    void successfulLogin() {
        open("https://vsemenov95.github.io/ai-first-simple/index.html");
        $("#username").setValue("admin");
        $("#password").setValue("admin123");
        $("button[type=submit]").click();
        $("#message").shouldHave(text("Вход выполнен успешно"));
    }

    @Test
    @DisplayName("Чекбокс «Запомнить меня» есть на форме и кликабелен")
    void rememberMeCheckboxIsClickable() {
        open("https://vsemenov95.github.io/ai-first-simple/index.html");
        $("label[for=remember]").shouldHave(text("Запомнить меня"));
        $("#remember").shouldNotBe(selected).click();
        $("#remember").shouldBe(selected);
    }
}
