package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DebitPage {

    private static final SelenideElement number = $("[placeholder=\"0000 0000 0000 0000\"]");
    private static final SelenideElement month = $("[placeholder=\"08\"]");
    private static final SelenideElement year = $("[placeholder=\"22\"]");
    private static final SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private static final SelenideElement cvc = $("[placeholder=\"999\"]");
    private static final SelenideElement button = $(byText("Продолжить"));
    private static final SelenideElement successNotification = $(byText("Успешно")).parent().$(".notification__title");
    private static final SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private static final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private static final SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private static final SelenideElement wrongExpirationDate = $(byText("Неверно указан срок действия карты"));

    public void getSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void getErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void getWrongFormat() {
        wrongFormat.shouldBe(Condition.visible);
    }

    public void getRequiredField() {
        requiredField.shouldBe(Condition.visible);
    }

    public void getWrongExpirationDate() {
        wrongExpirationDate.shouldBe(Condition.visible);
    }

    public void completeDebitForm(DataHelper.CardInfo cardInfo) {
        number.setValue(cardInfo.getNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        cvc.setValue(cardInfo.getCvc());
        button.click();
    }
}
