package br.com.events.band.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Tests for {@link DateUtil}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DateUtilTest {

    @Test
    @DisplayName("millisecondsToLocalDateTime - when milliseconds is null, then return null")
    void millisecondsToLocalDateTimeWhenMillisecondsIsNullThenReturnNull() {
        Assertions.assertNull(DateUtil.millisecondsToLocalDateTime(null));
    }

    @Test
    @DisplayName("millisecondsToLocalDateTime - when milliseconds is not null, then return correct date")
    void millisecondsToLocalDateTimeWhenMillisecondsIsNotNullThenReturnCorrectDate() {
        var milliseconds = 1721499489122L;
        var returned = DateUtil.millisecondsToLocalDateTime(milliseconds);
        Assertions.assertNotNull(returned);
        Assertions.assertEquals(20, returned.getDayOfMonth());
        Assertions.assertEquals(7, returned.getMonthValue());
        Assertions.assertEquals(2024, returned.getYear());
        Assertions.assertEquals(15, returned.getHour());
        Assertions.assertEquals(18, returned.getMinute());
        Assertions.assertEquals(9, returned.getSecond());
        Assertions.assertTrue(String.valueOf(returned.getNano()).startsWith("122"));
    }

    @Test
    @DisplayName("localDateTimeToMilliseconds - when LocalDateTime is null, then return null")
    void localDateTimeToMillisecondsWhenLocalDateTimeIsNullThenReturnNull() {
        Assertions.assertNull(DateUtil.localDateTimeToMilliseconds(null));
    }

    @Test
    @DisplayName("localDateTimeToMilliseconds - when LocalDateTime is not null, then return correct value")
    void localDateTimeToMillisecondsWhenLocalDateTimeIsNotNullThenReturnCorrectValue() {
        var date = LocalDateTime.of(2024, 7, 20, 15, 18, 9, 122000000);
        var returned = DateUtil.localDateTimeToMilliseconds(date);
        Assertions.assertNotNull(returned);
        Assertions.assertEquals(1721499489122L, returned);
    }

    @Test
    @DisplayName("calculateAgeByBirthday - when called return correct year")
    void calculateAgeByBirthdayWhenCalledReturnCorrectYear() {
        var returned = DateUtil.calculateAgeByBirthday(LocalDateTime.now());
        Assertions.assertNotNull(returned);
        Assertions.assertEquals(0, returned);
    }

    @Test
    @DisplayName("formatDate - when called return formatted date")
    void formatDateWhenCalledReturnFormattedDate() {
        var date = LocalDateTime.of(2024, 1, 1, 0, 0);
        var returned = DateUtil.formatDate(date);
        Assertions.assertNotNull(returned);
        Assertions.assertEquals("01/01/2024", returned);
    }

    @Test
    @DisplayName("formatTime - when called return formatted time")
    void formatTimeWhenCalledReturnFormattedTime() {
        var date = LocalTime.of(12, 0);
        var returned = DateUtil.formatTime(date);
        Assertions.assertNotNull(returned);
        Assertions.assertEquals("12:00", returned);
    }
}
