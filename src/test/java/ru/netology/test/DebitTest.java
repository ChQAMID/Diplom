package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.FirstPage;

import static com.codeborne.selenide.Selenide.open;

public class DebitTest {

    FirstPage firstPage = new FirstPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Оплата зарегистрированной картой с валидными данными")
    public void shouldPayByApprovedCardWithValidValues() {
        var debitPage = firstPage.payByDebitCard();
        var cardInfo = DataHelper.getApprovedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getSuccessNotification();
    }
}
