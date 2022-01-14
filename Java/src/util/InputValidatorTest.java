package util;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class InputValidatorTest {
    private InputValidator iValidator = new InputValidator();

    @Test
    void testIsLeapYear() {
        assertEquals(true, iValidator.isLeapYear(2000));
        assertEquals(true, iValidator.isLeapYear(2400));
        assertEquals(true, iValidator.isLeapYear(2004));
        assertEquals(true, iValidator.isLeapYear(2008));
        assertEquals(false, iValidator.isLeapYear(1800));
        assertEquals(false, iValidator.isLeapYear(1900));
        assertEquals(false, iValidator.isLeapYear(2500));
    }

    @Test
    void checkValidDate() {
        assertEquals(true, iValidator.checkValidDate("07012022"));
        assertEquals(false, iValidator.checkValidDate("0701202"));
        assertEquals(false, iValidator.checkValidDate("07132022"));
        assertEquals(false, iValidator.checkValidDate("31022022"));
        assertEquals(true, iValidator.checkValidDate("29022020"));
        assertEquals(false, iValidator.checkValidDate("29022022"));
        assertEquals(false, iValidator.checkValidDate("31042022"));
        assertEquals(false, iValidator.checkValidDate("91012022"));
        assertEquals(false, iValidator.checkValidDate("0012022"));
    }

    @Test
    void checkValidDateInts() {
        assertEquals(true, iValidator.checkValidDate(2022, 1, 7));
        assertEquals(false, iValidator.checkValidDate(202, 1, 7));
        assertEquals(false, iValidator.checkValidDate(2022, 13, 7));
        assertEquals(false, iValidator.checkValidDate(2022, 2, 31));
        assertEquals(true, iValidator.checkValidDate(2020, 2, 29));
        assertEquals(false, iValidator.checkValidDate(2022, 2, 29));
        assertEquals(false, iValidator.checkValidDate(2022, 4, 31));
        assertEquals(false, iValidator.checkValidDate(2022, 12, 91));
        assertEquals(false, iValidator.checkValidDate(2022, 1, 0));
    }

    @Test
    void testCheckValidCreditCardLuhn() {
        assertEquals(false, iValidator.checkValidCreditCard("1234123412341234"));
        assertEquals(false, iValidator.checkValidCreditCard("4321432143214321"));
        assertEquals(true, iValidator.checkValidCreditCard("1111222233334444"));
        assertEquals(true, iValidator.checkValidCreditCard("378282246310005"));
        assertEquals(true, iValidator.checkValidCreditCard("371449635398431"));
        assertEquals(true, iValidator.checkValidCreditCard("378734493671000"));
        assertEquals(true, iValidator.checkValidCreditCard("30569309025904"));
        assertEquals(true, iValidator.checkValidCreditCard("6011111111111117"));
        assertEquals(true, iValidator.checkValidCreditCard("6011000990139424"));
        assertEquals(true, iValidator.checkValidCreditCard("3530111333300000"));
        assertEquals(true, iValidator.checkValidCreditCard("3566002020360505"));
        assertEquals(true, iValidator.checkValidCreditCard("2221000000000009"));
        assertEquals(true, iValidator.checkValidCreditCard("2223000048400011"));
        assertEquals(true, iValidator.checkValidCreditCard("2223016768739313"));
        assertEquals(true, iValidator.checkValidCreditCard("5555555555554444"));
        assertEquals(true, iValidator.checkValidCreditCard("5105105105105100"));
        assertEquals(true, iValidator.checkValidCreditCard("4111111111111111"));
        assertEquals(true, iValidator.checkValidCreditCard("4012888888881881"));
        assertEquals(true, iValidator.checkValidCreditCard("4222222222222"));
    }
}
