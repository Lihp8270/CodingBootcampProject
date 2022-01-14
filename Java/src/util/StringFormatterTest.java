package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringFormatterTest {
    private StringFormatter sFormatter = new StringFormatter();

    @Test
    void testFormatPrice() {
        assertEquals("£1.79", sFormatter.formatPrice(1.7900000000));
    }

    @Test
    void testLikeSearchTerm() {
        assertEquals("%testSearch%", sFormatter.createLikeSearchString("testSearch"));
    }

    @Test
    void testCreateDateString() {
        assertEquals("2022-1-6", sFormatter.createDateString(2022, 01, 06));
    }

    @Test
    void testGetDateInts() {
        assertEquals(7, sFormatter.getDateInts("date", "07012022"));
        assertEquals(7, sFormatter.getDateInts("Date", "07012022"));
        assertEquals(7, sFormatter.getDateInts("DATE", "07012022"));
        assertEquals(1, sFormatter.getDateInts("month", "07012022"));
        assertEquals(2022, sFormatter.getDateInts("year", "07012022"));
        assertEquals(1, sFormatter.getDateInts("mOnth", "07012022"));
        assertEquals(2022, sFormatter.getDateInts("yeaR", "07012022"));

        assertEquals("2022-1-7", sFormatter.createDateString(sFormatter.getDateInts("yeaR", "07012022"),
                                                                sFormatter.getDateInts("month", "07012022"),
                                                                sFormatter.getDateInts("date", "07012022")));
    }

    @Test
    void testDoubleDigitDate() {
        assertEquals("01", sFormatter.doubleDigitDate("1"));
        assertEquals("09", sFormatter.doubleDigitDate("9"));
        assertEquals("07", sFormatter.doubleDigitDate("7"));
        assertEquals("11", sFormatter.doubleDigitDate("11"));
        assertEquals("123", sFormatter.doubleDigitDate("123"));
        assertEquals("00", sFormatter.doubleDigitDate("0"));
    }

    @Test
    void testRemoveNonNumeric() {
        assertEquals("123456789", sFormatter.removeNonNumeric("1.2.3.4.5.6.7.8.9"));
        assertEquals("123456789", sFormatter.removeNonNumeric("1 2 3 4 5 6 7 89"));
        assertEquals("123456789", sFormatter.removeNonNumeric("1a2b3c4d5e6f7g8h9"));
        assertEquals("123456789", sFormatter.removeNonNumeric("1.2.3#4£$5£6£7%8-9"));
    }
}
