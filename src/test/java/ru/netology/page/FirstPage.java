package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class FirstPage {

    private static final SelenideElement debitButton = $(byText("Купить"));
    private static final SelenideElement creditButton = $(byText("Купить в кредит"));

    public DebitPage payByDebitCard() {
        debitButton.click();
        return new DebitPage();
    }

    public CreditPage payByCredit() {
        creditButton.click();
        return new CreditPage();
    }
}
