package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightsSearchPage {
    SelenideElement
            fromCity = $x("//*[@id='departureCity']"),
            toCity = $("#arrivalCity"),
            flightDate = $("#departureDate"),
            findButton = $x("//button[.='Найти']"),
            message = $("#searchMessage"),
            messageRace = $x("//body");



    public String
            notexsistMessage = "Рейсов по вашему запросу не найдено.";

    public String
            errorMessageEmptyDate = "Пожалуйста, укажите дату вылета.";


    public void findFlights(String from, String to, String date) {
        fromCity.selectOption(from);
        toCity.selectOption(to);
        flightDate.setValue(date);
        findButton.click();
    }

    public void verifyEmptyDate(){
        //Проверяет, что есть сообщение о том, что дата не задана

        message.shouldHave(text(errorMessageEmptyDate));
    }

    public void notFindRace() {
        messageRace.shouldHave(text(notexsistMessage));
    }
}