package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDataBase;

public class BuyingTourTest {

    MainPage mainPage = new MainPage();

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

    @AfterEach
    void tearDown() {
        cleanDataBase();
    }

    @Test
    @DisplayName("Оплата зарегистрированной картой с валидными данными")
    public void shouldPayByApprovedCardWithValidValues() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getApprovedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkSuccessNotification();
    }
    @Test
    @DisplayName("Оплата отклоненной картой с валидными данными")
    public void shouldPayByDeclinedCardWithValidValues() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getDeclinedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkErrorNotification();
    }
    @Test
    @DisplayName("Отправка пустой формы")
    public void shouldSendEmptyForm() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getEmptyForm();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
        debitPage.checkRequiredField();
    }
    @Test
    @DisplayName("Отправка формы с невалидным номером карты")
    public void shouldSendInvalidCardNumber() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getInvalidCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkErrorNotification();
    }

    @Test
    @DisplayName("Отправка формы с неполным номером карты")
    public void shouldSendIncompleteCardNumber() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getIncompleteCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с месяцем 00")
    public void shouldSendMonthUnderBorder() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getMonthUnderBorder();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с месяцем 13")
    public void shouldSendMonthAboveBorder() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getMonthAboveBorder();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongExpirationDate();
    }

    @Test
    @DisplayName("Отправка формы с месяцем до текущего (текущего года)")
    public void shouldSendPreviousMonthCurrentYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getPreviousMonthCurrentYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongExpirationDate();
    }

    @Test
    @DisplayName("Ввод одной цифры в поле месяц")
    public void shouldSendOneDigitMonth() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getOneDigitMonth();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }


    @Test
    @DisplayName("Ввод букв в поле месяц")
    public void shouldSendLettersInMonth() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getLettersInMonth();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле месяц")
    public void shouldSendSymbolInMonth() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolInMonth();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с годом из прошлого")
    public void shouldSendYearInPast() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getYearInPast();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkExpiredDate();
    }

    @Test
    @DisplayName("Отправка формы с годом, после окончания срока действия карты")
    public void shouldSendYearInFuture() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getYearInFuture();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongExpirationDate();
    }

    @Test
    @DisplayName("Ввод одной цифры в поле год")
    public void shouldSendOneDigitYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getOneDigitYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }


    @Test
    @DisplayName("Ввод букв в поле год")
    public void shouldSendLettersInYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getLettersInYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле год")
    public void shouldSendSymbolInYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolInYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод кириллицы в поле Владелец")
    public void shouldSendCyrillicOwner() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getCyrillicOwner();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод цифр в поле Владелец")
    public void shouldSendDigitOwner() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getDigitOwner();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле Владелец")
    public void shouldSendSymbolOwner() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolOwner();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }


    @Test
    @DisplayName("Ввод двух цифр в поле CVC/CVV")
    public void shouldSendTwoDigitCVC() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getTwoDigitCVC();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }


    @Test
    @DisplayName("Ввод букв в поле CVC/CVV")
    public void shouldSendLetterCVC() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getLetterCVC();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле CVC/CVV")
    public void shouldSendSymbolCVC() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolCVC();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Проверка в СУБД оплаты по зарегистрированной карте")
    public void shouldCheckSQLPaymentSumApprovedCard() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getApprovedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkSuccessNotification();
        var paymentSum = SQLHelper.getDebitSumSQL();
        Assertions.assertEquals(4500000, paymentSum);
    }

    @Test
    @DisplayName("Проверка в СУБД оплаты по отклоненной карте")
    public void shouldCheckSQLPaymentSumDeclinedCard() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getDeclinedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkSuccessNotification();
        var paymentSum = SQLHelper.getDebitSumSQL();
        Assertions.assertEquals(0, paymentSum);
    }

    @Test
    @DisplayName("Проверка в СУБД статуса зарегистрированной карты")
    public void shouldCheckSQLPaymentStatusApprovedCard() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getApprovedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkSuccessNotification();
        var paymentStatus = SQLHelper.getDebitStatusSQL();
        Assertions.assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Проверка в СУБД статуса отклоненной карты")
    public void shouldCheckSQLPaymentStatusDeclinedCard() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getDeclinedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.checkSuccessNotification();
        var paymentStatus = SQLHelper.getDebitStatusSQL();
        Assertions.assertEquals("DECLINED", paymentStatus);
    }

}
