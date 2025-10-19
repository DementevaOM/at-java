package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FlightsRegistrationPage {
    SelenideElement

    fioPassenger = $("#passengerName"),
    numPass = $("#passportNumber"),
    emailPass = $("#email"),
    phonePass = $("#phone"),
    regButton = $x("//button[text()='Завершить регистрацию']"),
    returnButton = $x("//button[text()='Вернуться к найденным рейсам']"),
    messagePass = $("#registrationMessage"),
    messageFio = $("#registrationMessage"),
    messageFields = $("#registrationMessage"),
    messageReg = $("#registrationMessage"),
    StartRace = $x("//h2[text()='Найденные рейсы']");


    public String
            errorMessageWrongPass = "Номер паспорта должен содержать только цифры и пробелы.";
    public String
            errorMessageWrongFio = "ФИО должно содержать только русские буквы, пробелы и дефис.";
    public String
            errorMessageEmptyFields = "Пожалуйста, заполните все поля.";
    public String
            successMessageReg = "Регистрация успешно завершена!";

    public void regFlights(String fio, String pass, String email, String phone) {
        fioPassenger.setValue(fio);
        numPass.setValue(pass);
        emailPass.setValue(email);
        phonePass.setValue(phone);
        regButton.click();
    }

    public void wrongFio(){
        messageFio.shouldHave(text(errorMessageWrongFio));
    }

    public void wrongPass() {
        messagePass.shouldHave(text(errorMessageWrongPass));
    }

    public void emptyFields() {
        messageFields.shouldHave(text(errorMessageEmptyFields));
    }

    public void successReg() {
        messageReg.shouldHave(text(successMessageReg));
    }
    public void returnRaces() {
        returnButton.click();
        StartRace.shouldBe(visible, Duration.ofSeconds(20));
        StartRace.shouldHave(text("Найденные рейсы"));

    }

}