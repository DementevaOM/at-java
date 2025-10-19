package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class FlightsLoginPage {
    SelenideElement Username, Password, LoginButton, Message, Greeting, LogoutButton, Start;
    String successMessage, errorMessage, emptyMessage, lockedMessage;
    public FlightsLoginPage() {
        Username = $("#username");
        Password = $("#password");
        LoginButton = $("#loginButton");
        Message = $("#message");
        Greeting = $("#greeting");
        LogoutButton = $("#logoutButton");
        Start = $("h2");

        successMessage = "Вход в систему выполнен успешно! Загрузка...";
        errorMessage = "Неверное имя пользователя или пароль.";
        emptyMessage = "Username and Password are required.";
        lockedMessage = "Пользователь заблокирован.";
    }

    @Step("Вход в систему")
    public void login(String username, String password) {
        Username.setValue(username);
        Password.setValue(password);
        LoginButton.click();
    }

    @Step("Проверка успешности логина")
    public void verify_successful_login() {
        Message.shouldHave(text(successMessage));
        Greeting.shouldBe(visible, Duration.ofSeconds(20));
        Greeting.shouldHave(text("Добро пожаловать, Иванов Иван Иванович!"));
    }

    @Step("Проверка успешности логина и выхода")
    public void verify_successful_login_quit() {
        Message.shouldHave(text(successMessage));
        Greeting.shouldBe(visible, Duration.ofSeconds(20));
        Greeting.shouldHave(text("Добро пожаловать, Иванов Иван Иванович!"));
        LogoutButton.click();
        Start.shouldHave(text("Аутентификация"));


    }

    @Step("Проверка неуспешности логина")
    public void verify_wrong_username_or_password() {
        Message.shouldHave(text(errorMessage));
    }

    @Step("Проверка входа с пустыми полями")
    public void verify_empty_username_and_password() {
        Message.shouldHave(text(emptyMessage));
    }

    @Step("Проверка входа проверка входа заблокированным пользователем")
    public void verify_locked_user() {
        Message.shouldHave(text(lockedMessage));
    }
}