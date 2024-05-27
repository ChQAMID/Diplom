package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    public static String generateCardNumber() {
        return faker.numerify("################");
    }

    public static String generateIncompleteCardNumber() {
        return faker.numerify("###############");
    }

    public static String generateCardNumberBeyondLimit() {
        return faker.numerify("#################");
    }

    public static String generateEmptyCardNumber() {
        return ("");
    }

    public static String generateValidMonth() {
        return LocalDate.now().plusMonths(faker.number().numberBetween(1, 12)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateEmptyMonth() {
        return ("");
    }

    public static String generateMonthUnderBorder() {
        return ("00");
    }

    public static String generateMonthAboveBoder() {
        return ("13");
    }

    public static String generateOneDigitMonth() {
        return faker.numerify("#");
    }

    public static String generateThreeDigitMonth() {
        return faker.numerify("###");
    }

    public static String generateLetterInMonth() {
        return faker.letterify("##");
    }

    public static String generateSymbolInMonth() {
        return ("*%");
    }

    public static String generateValidYear() {
        return LocalDate.now().plusYears(faker.number().numberBetween(1, 5)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateEmptyYear() {
        return ("");
    }

    public static String generateYearInPast() {
        return LocalDate.now().plusYears(faker.number().numberBetween(-1, -5)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateYearInFuture() {
        return LocalDate.now().plusYears(faker.number().numberBetween(6, 20)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateThreeDigitYear() {
        return ("111");
    }

    public static String generateOneDigitYear() {
        return faker.numerify("#");
    }

    public static String generateLetterYear() {
        return faker.letterify("##");
    }

    public static String generateSymbolYear() {
        return ("*%");
    }


    public static String generateValidOwner() {
        return faker.name().fullName();
    }

    public static String generateEmptyOwner() {
        return ("");
    }

    public static String generateCyrillicOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String generateDigitOwner() {
        return faker.numerify("#############");
    }

    public static String generateSymbolOwner() {
        return ("@#$%%^&&*");
    }

    public static String generateOwnerOverLimit() {
        return ("#####################################################################################");
    }

    public static String generateValidCVC() {
        return faker.numerify("###");
    }

    public static String generateEmptyCVC() {
        return ("");
    }

    public static String generateTwoDigitCVC() {
        return faker.numerify("##");
    }

    public static String generateFourDigitCVC() {
        return faker.numerify("####");
    }

    public static String generateLetterCVC() {
        return faker.letterify("###");
    }

    public static String generateSymbolCVC() {
        return ("@#$");
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getEmptyForm() {
        return new CardInfo(generateEmptyCardNumber(), generateEmptyMonth(), generateEmptyYear(),
                generateEmptyOwner(), generateEmptyCVC());
    }

    public static CardInfo getInvalidCard() {
        return new CardInfo(generateCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getIncompleteCard() {
        return new CardInfo(generateIncompleteCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getInvalidCardWith17Characters() {
        return new CardInfo(generateCardNumberBeyondLimit(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getMonthUnderBorder() {
        return new CardInfo(getApprovedCardNumber(), generateMonthUnderBorder(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getMonthAboveBorder() {
        return new CardInfo(getApprovedCardNumber(), generateMonthAboveBoder(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getOneDigitMonth() {
        return new CardInfo(getApprovedCardNumber(), generateOneDigitMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getThreeDigitMonth() {
        return new CardInfo(getApprovedCardNumber(), generateThreeDigitMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getLettersInMonth() {
        return new CardInfo(getApprovedCardNumber(), generateLetterInMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getSymbolInMonth() {
        return new CardInfo(getApprovedCardNumber(), generateSymbolInMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getYearInPast() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateYearInPast(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getYearInFuture() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateYearInFuture(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getOneDigitYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateOneDigitYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getThreeDigitYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateThreeDigitYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getLettersInYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateLetterYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getSymbolInYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateSymbolYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getCyrillicOwner() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateCyrillicOwner(), generateValidCVC());
    }

    public static CardInfo getDigitOwner() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateDigitOwner(), generateValidCVC());
    }

    public static CardInfo getSymbolOwner() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateSymbolOwner(), generateValidCVC());
    }

    public static CardInfo getOwnerOverLimit() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateOwnerOverLimit(), generateValidCVC());
    }

    public static CardInfo getTwoDigitCVC() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateTwoDigitCVC());
    }

    public static CardInfo getFourDigitCVC() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateFourDigitCVC());
    }

    public static CardInfo getLetterCVC() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateLetterCVC());
    }

    public static CardInfo getSymbolCVC() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateSymbolCVC());
    }


    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String owner;
        String cvc;
    }
}
