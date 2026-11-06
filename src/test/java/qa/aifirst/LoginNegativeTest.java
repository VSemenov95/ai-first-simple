package qa.aifirst;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class LoginNegativeTest extends TestBase {

    private static final String PAGE_URL = "https://svasenkov.github.io/ai-first-simple/index.html";
    private static final String ERROR_TEXT = "Неверное имя пользователя или пароль";

    @ParameterizedTest(name = "Неверная пара: логин «{0}», пароль «{1}» → сообщение об ошибке")
    @CsvSource({
            "admin, wrong-password",
            "wrong-user, admin123",
            "wrong-user, wrong-password"
    })
    @DisplayName("Неверная пара логин/пароль показывает сообщение об ошибке")
    void wrongCredentialsShowError(String username, String password) {
        open(PAGE_URL);
        $("#username").setValue(username);
        $("#password").setValue(password);
        $("button[type=submit]").click();
        $("#message").shouldHave(text(ERROR_TEXT), cssClass("error"));
    }

    @Test
    @DisplayName("Пустой логин показывает своё сообщение об ошибке")
    void emptyUsernameShowsError() {
        open(PAGE_URL);
        $("#password").setValue("admin123");
        $("button[type=submit]").click();
        $("#message").shouldHave(exactText("Введите имя пользователя"), cssClass("error"));
    }

    @Test
    @DisplayName("Пустой пароль показывает своё сообщение об ошибке")
    void emptyPasswordShowsError() {
        open(PAGE_URL);
        $("#username").setValue("admin");
        $("button[type=submit]").click();
        $("#message").shouldHave(exactText("Введите пароль"), cssClass("error"));
    }

    @Test
    @DisplayName("Оба пустых поля показывают сообщение об ошибке, вход не выполнен")
    void bothEmptyShowsError() {
        open(PAGE_URL);
        $("button[type=submit]").click();
        $("#message").shouldHave(exactText("Введите имя пользователя и пароль"), cssClass("error"));
        $("#message").shouldNotHave(cssClass("success"));
    }
}
