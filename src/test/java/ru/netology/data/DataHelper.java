package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

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


    public static String getEmptyFieldValue() {
        return ("");
    }

    public static String generateValidMonth() {
        return LocalDate.now().plusMonths(faker.number().numberBetween(1, 12)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateMonthUnderBorder() {
        return ("00");
    }

    public static String generateMonthAboveBorder() {
        return ("13");
    }

    public static String getPreviousMonth(int month) {
        return LocalDate.now().minusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateOneDigitValue() {
        return faker.numerify("#");
    }

    public static String generateTwoDigitValue() {
        return faker.numerify("##");
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

    public static String generateInvalidYear(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
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


    public static String generateValidCVC() {
        return faker.numerify("###");
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
        return new CardInfo(getEmptyFieldValue(), getEmptyFieldValue(), getEmptyFieldValue(),
                getEmptyFieldValue(), getEmptyFieldValue());
    }

    public static CardInfo getInvalidCard() {
        return new CardInfo(generateCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getIncompleteCard() {
        return new CardInfo(generateIncompleteCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }


    public static CardInfo getMonthUnderBorder() {
        return new CardInfo(getApprovedCardNumber(), generateMonthUnderBorder(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getMonthAboveBorder() {
        return new CardInfo(getApprovedCardNumber(), generateMonthAboveBorder(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getPreviousMonthCurrentYear() {
        return new CardInfo(getApprovedCardNumber(), getPreviousMonth(1), generateInvalidYear(0), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getOneDigitMonth() {
        return new CardInfo(getApprovedCardNumber(), generateOneDigitValue(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getLettersInMonth() {
        return new CardInfo(getApprovedCardNumber(), generateLetterInMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getSymbolInMonth() {
        return new CardInfo(getApprovedCardNumber(), generateSymbolInMonth(), generateValidYear(), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getYearInPast() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateInvalidYear(-1), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getYearInFuture() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateInvalidYear(15), generateValidOwner(), generateValidCVC());
    }

    public static CardInfo getOneDigitYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateOneDigitValue(), generateValidOwner(), generateValidCVC());
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


    public static CardInfo getTwoDigitCVC() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner(), generateTwoDigitValue());
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
