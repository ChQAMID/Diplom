package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

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

    @Test
    @DisplayName("Оплата зарегистрированной картой с валидными данными")
    public void shouldPayByApprovedCardWithValidValues() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getApprovedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getSuccessNotification();
    }

    @Test
    @DisplayName("Выдача кредита по зарегистрированной карте с валидными данными")
    public void shouldGetCreditByApprovedCardWithValidValues() {
        var debitPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getApprovedCard();
        debitPage.completeCreditForm(cardInfo);
        debitPage.getSuccessNotification();
    }

    @Test
    @DisplayName("Оплата отклоненной картой с валидными данными")
    public void shouldPayByDeclinedCardWithValidValues() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getDeclinedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getErrorNotification();
    }

    @Test
    @DisplayName("Выдача кредита по отклоненной карте с валидными данными")
    public void shouldGetCreditByDeclinedCardWithValidValues() {
        var debitPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getDeclinedCard();
        debitPage.completeCreditForm(cardInfo);
        debitPage.getErrorNotification();
    }

    @Test
    @DisplayName("Отправка пустой формы")
    public void shouldSendEmptyForm() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getEmptyForm();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
        debitPage.getRequiredField();
    }
    @Test
    @DisplayName("Отправка формы с невалидным номером карты")
    public void shouldSendInvalidCardNumber() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getInvalidCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getErrorNotification();
    }

    @Test
    @DisplayName("Отправка формы с неполным номером карты")
    public void shouldSendIncompleteCardNumber() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getIncompleteCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с месяцем 00")
    public void shouldSendMonthUnderBorder() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getMonthUnderBorder();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с месяцем 13")
    public void shouldSendMonthAboveBorder() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getMonthAboveBorder();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongExpirationDate();
    }

    @Test
    @DisplayName("Отправка формы с месяцем до текущего (текущего года)")
    public void shouldSendPreviousMonthCurrentYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getPreviousMonthCurrentYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongExpirationDate();
    }

    @Test
    @DisplayName("Ввод одной цифры в поле месяц")
    public void shouldSendOneDigitMonth() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getOneDigitMonth();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

//    @Test
//    @DisplayName("Ввод трех цифр в поле месяц")
//    public void shouldSendThreeDigitMonth() {
//        var debitPage = mainPage.payByDebitCard();
//        var cardInfo = DataHelper.getThreeDigitMonth();
//        debitPage.completeDebitForm(cardInfo);
//        debitPage.getWrongExpirationDate();
//    }

    @Test
    @DisplayName("Ввод букв в поле месяц")
    public void shouldSendLettersInMonth() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getLettersInMonth();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле месяц")
    public void shouldSendSymbolInMonth() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolInMonth();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с годом из прошлого")
    public void shouldSendYearInPast() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getYearInPast();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getExpiredDate();
    }

    @Test
    @DisplayName("Отправка формы с годом, после окончания срока действия карты")
    public void shouldSendYearInFuture() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getYearInFuture();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongExpirationDate();
    }

    @Test
    @DisplayName("Ввод одной цифры в поле год")
    public void shouldSendOneDigitYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getOneDigitYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

//    @Test
//    @DisplayName("Ввод трех цифр в поле год")
//    public void shouldSendThreeDigitYear() {
//        var debitPage = mainPage.payByDebitCard();
//        var cardInfo = DataHelper.getThreeDigitYear();
//        debitPage.completeDebitForm(cardInfo);
//        debitPage.getWrongFormat();
//    }

    @Test
    @DisplayName("Ввод букв в поле год")
    public void shouldSendLettersInYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getLettersInYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле год")
    public void shouldSendSymbolInYear() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolInYear();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Ввод кириллицы в поле Владелец")
    public void shouldSendCyrillicOwner() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getCyrillicOwner();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Ввод цифр в поле Владелец")
    public void shouldSendDigitOwner() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getDigitOwner();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле Владелец")
    public void shouldSendSymbolOwner() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolOwner();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

//    @Test
//    @DisplayName("Ввод в поле Владелец 85 букв на латинице")
//    public void shouldSendOwnerOverLimit() {
//        var debitPage = mainPage.payByDebitCard();
//        var cardInfo = DataHelper.getOwnerOverLimit();
//        debitPage.completeDebitForm(cardInfo);
//        debitPage.getWrongFormat();
//    }

    @Test
    @DisplayName("Ввод двух цифр в поле CVC/CVV")
    public void shouldSendTwoDigitCVC() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getTwoDigitCVC();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

//    @Test
//    @DisplayName("Ввод четырех цифр в поле CVC/CVV")
//    public void shouldSendFourDigitCVC() {
//        var debitPage = mainPage.payByDebitCard();
//        var cardInfo = DataHelper.getFourDigitCVC();
//        debitPage.completeDebitForm(cardInfo);
//        debitPage.getWrongFormat();
//    }

    @Test
    @DisplayName("Ввод букв в поле CVC/CVV")
    public void shouldSendLetterCVC() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getLetterCVC();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле CVC/CVV")
    public void shouldSendSymbolCVC() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getSymbolCVC();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getWrongFormat();
    }

    @Test
    @DisplayName("Проверка в СУБД оплаты по зарегистрированной карте")
    public void shouldCheckSQLPaymentStatusApprovedCard() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getApprovedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getSuccessNotification();
        var PaymentStatus = SQLHelper.getSumSQL();
        Assertions.assertEquals(45000, PaymentStatus);
    }

    @Test
    @DisplayName("Проверка в СУБД оплаты по отклоненной карте")
    public void shouldCheckSQLPaymentStatusDeclinedCard() {
        var debitPage = mainPage.payByDebitCard();
        var cardInfo = DataHelper.getDeclinedCard();
        debitPage.completeDebitForm(cardInfo);
        debitPage.getSuccessNotification();
        var PaymentStatus = SQLHelper.getSumSQL();
        Assertions.assertEquals(0, PaymentStatus);
    }

//    @Test
//    @DisplayName("Ввод 17 цифр в поле номера карты")
//    public void shouldEnter17CharactersInCardNumberForm() {
//        var debitPage = mainPage.payByDebitCard();
//        var cardInfo = DataHelper.getInvalidCardWith17Characters();
//        debitPage.completeDebitForm(cardInfo);
//        debitPage.getSuccessNotification();
//    }





}
