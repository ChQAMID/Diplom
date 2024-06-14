package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDataBase;

public class BuyingByCreditTest {

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
    @DisplayName("Выдача кредита по зарегистрированной карте с валидными данными")
    public void shouldGetCreditByApprovedCardWithValidValues() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getApprovedCard();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkSuccessNotification();
    }

    @Test
    @DisplayName("Выдача кредита по отклоненной карте с валидными данными")
    public void shouldGetCreditByDeclinedCardWithValidValues() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getDeclinedCard();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkErrorNotification();
    }

    @Test
    @DisplayName("Отправка пустой формы")
    public void shouldSendEmptyForm() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getEmptyForm();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
        creditPage.checkRequiredField();
    }

    @Test
    @DisplayName("Отправка формы с невалидным номером карты")
    public void shouldSendInvalidCardNumber() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getInvalidCard();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkErrorNotification();
    }

    @Test
    @DisplayName("Отправка формы с неполным номером карты")
    public void shouldSendIncompleteCardNumber() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getIncompleteCard();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с месяцем 00")
    public void shouldSendMonthUnderBorder() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getMonthUnderBorder();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с месяцем 13")
    public void shouldSendMonthAboveBorder() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getMonthAboveBorder();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongExpirationDate();
    }

    @Test
    @DisplayName("Отправка формы с месяцем до текущего (текущего года)")
    public void shouldSendPreviousMonthCurrentYear() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getPreviousMonthCurrentYear();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongExpirationDate();
    }

    @Test
    @DisplayName("Ввод одной цифры в поле месяц")
    public void shouldSendOneDigitMonth() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getOneDigitMonth();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод букв в поле месяц")
    public void shouldSendLettersInMonth() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getLettersInMonth();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле месяц")
    public void shouldSendSymbolInMonth() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getSymbolInMonth();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Отправка формы с годом из прошлого")
    public void shouldSendYearInPast() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getYearInPast();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkExpiredDate();
    }

    @Test
    @DisplayName("Отправка формы с годом, после окончания срока действия карты")
    public void shouldSendYearInFuture() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getYearInFuture();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongExpirationDate();
    }

    @Test
    @DisplayName("Ввод одной цифры в поле год")
    public void shouldSendOneDigitYear() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getOneDigitYear();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод букв в поле год")
    public void shouldSendLettersInYear() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getLettersInYear();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле год")
    public void shouldSendSymbolInYear() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getSymbolInYear();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод кириллицы в поле Владелец")
    public void shouldSendCyrillicOwner() {
        var debitPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getCyrillicOwner();
        debitPage.completeCreditForm(cardInfo);
        debitPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод цифр в поле Владелец")
    public void shouldSendDigitOwner() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getDigitOwner();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле Владелец")
    public void shouldSendSymbolOwner() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getSymbolOwner();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод двух цифр в поле CVC/CVV")
    public void shouldSendTwoDigitCVC() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getTwoDigitCVC();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод букв в поле CVC/CVV")
    public void shouldSendLetterCVC() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getLetterCVC();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Ввод спецсимволов в поле CVC/CVV")
    public void shouldSendSymbolCVC() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getSymbolCVC();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Проверка в СУБД статуса зарегистрированной карты")
    public void shouldCheckSQLPaymentStatusApprovedCard() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getApprovedCard();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkSuccessNotification();
        var paymentStatus = SQLHelper.getCreditStatusSQL();
        Assertions.assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Проверка в СУБД статуса отклоненной карты")
    public void shouldCheckSQLPaymentStatusDeclinedCard() {
        var creditPage = mainPage.payByCredit();
        var cardInfo = DataHelper.getDeclinedCard();
        creditPage.completeCreditForm(cardInfo);
        creditPage.checkSuccessNotification();
        var paymentStatus = SQLHelper.getCreditStatusSQL();
        Assertions.assertEquals("DECLINED", paymentStatus);
    }

}
