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



    public void getSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
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
