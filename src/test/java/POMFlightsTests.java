import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.FlightsFoundList;
import pages.FlightsLoginPage;
import pages.FlightsRegistrationPage;
import pages.FlightsSearchPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    void setUp() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        getWebDriver().manage().window().maximize();
    }

    //Тест-кейсы

    @Test
    @DisplayName("POM-01. Неуспешный логин")
    void test01() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "wrong_stand_pass1");
        login_page.verify_wrong_username_or_password();
    }

    @Test
    @DisplayName("POM-02. Успешный логин")
    void test02() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();
    }

    @Test
    @DisplayName("POM-03. Не задана дата вылета")
    void test03() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Париж", "");
        searchPage.verifyEmptyDate();
    }

    @Test
    @DisplayName("POM-04. Не найдены рейсы")
    void test04() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Казань","Париж", "17.11.2025");
        searchPage.notFindRace(); //проверка, что не найдены рейсы
    }

    @Test
    @DisplayName("POM-05. Регистрация - некорректно заполнен номер паспорта")
    void test05() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Нью-Йорк", "17.11.2025");

        FlightsFoundList flightsList = new FlightsFoundList();
        flightsList.verifySuccessfullSearch();
        flightsList.chooseFirstFlight();

        //Добавить класс RegistrationPage и использовать его
        FlightsRegistrationPage regPage = new FlightsRegistrationPage();
        regPage.regFlights("Тестов Тест Тестович","Номер паспорта", "test@test.com", "+7 (123) 456-7890");
        regPage.wrongPass();

    }

    @Test
    @DisplayName("POM-06. Регистрация - некорректно заполнено ФИО")
    void test06() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Нью-Йорк", "17.11.2025");

        FlightsFoundList flightsList = new FlightsFoundList();
        flightsList.verifySuccessfullSearch();
        flightsList.chooseFirstFlight();

        //Добавить класс RegistrationPage и использовать его
        FlightsRegistrationPage regPage = new FlightsRegistrationPage();
        regPage.regFlights("Testov test testovich","1234", "test@test.com", "+7 (123) 456-7890");
        regPage.wrongFio();


    }

    @Test
    @DisplayName("POM-07. Регистрация - отправка формы с пустыми полями")
    void test07() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Нью-Йорк", "17.11.2025");

        FlightsFoundList flightsList = new FlightsFoundList();
        flightsList.verifySuccessfullSearch();
        flightsList.chooseFirstFlight();

        //Добавить класс RegistrationPage и использовать его
        FlightsRegistrationPage regPage = new FlightsRegistrationPage();
        regPage.regFlights("","", "", "");
        regPage.emptyFields();


    }
    @Test
    @DisplayName("POM-08. Регистрация - успешная")
    void test08() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Нью-Йорк", "17.11.2025");

        FlightsFoundList flightsList = new FlightsFoundList();
        flightsList.verifySuccessfullSearch();
        flightsList.chooseFirstFlight();

        //Добавить класс RegistrationPage и использовать его
        FlightsRegistrationPage regPage = new FlightsRegistrationPage();
        regPage.regFlights("Иванов Иван Иванович","1234 567890", "ivanov@example.com", "+7 (123) 456-7890");
        regPage.successReg();
        sleep(10_000);

    }
    @Test
    @DisplayName("POM-09. Попытка входа с пустыми полями")
    void test09() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("", "");
        login_page.verify_empty_username_and_password();
    }

    @Test
    @DisplayName("POM-10. Попытка входа заблокированным пользователем")
    void test10() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("locked_out_user", "lock_pass2");
        login_page.verify_locked_user();
    }

    @Test
    @DisplayName("POM-11. Успешный логин и выход")
    void test11() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login_quit();
    }

    @Test
    @DisplayName("POM-12. Возврат к найденным рейсам")
    void test12() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Нью-Йорк", "17.11.2025");

        FlightsFoundList flightsList = new FlightsFoundList();
        flightsList.verifySuccessfullSearch();
        flightsList.chooseFirstFlight();

        //Добавить класс RegistrationPage и использовать его
        FlightsRegistrationPage regPage = new FlightsRegistrationPage();
        regPage.returnRaces();


    }





}