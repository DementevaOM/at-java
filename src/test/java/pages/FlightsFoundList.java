package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightsFoundList {
    SelenideElement
            firstRegButton = $x("//button[@class='register-btn']"),
            flightsCount = $x("//div[@id='flightsCount']");

//    ElementsCollection
//        flightsList = $$x("//table[@id='flightsContainer']/tbody/tr/td");

    public void chooseFirstFlight() {
        firstRegButton.shouldBe(clickable, Duration.ofSeconds(20));
        firstRegButton.click();
    }

    public void verifySuccessfullSearch() {
        flightsCount.shouldNotHave(text("Найдено рейсов: 0"));
        //assertEquals(7, flightsList.size());
        firstRegButton.shouldBe(visible);
    }
}