package br.com.events.band.core.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * This class is used when is needed to work with dates
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    private static final String ZONE_ID = "America/Sao_Paulo";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalDateTime millisecondsToLocalDateTime(Long milliseconds) {
        if (Objects.isNull(milliseconds)) {
            return null;
        }
        return Instant
                .ofEpochMilli(milliseconds)
                .atZone(ZoneId.of(ZONE_ID))
                .toLocalDateTime();
    }

    public static Long localDateTimeToMilliseconds(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.atZone(ZoneId.of(ZONE_ID)).toInstant().toEpochMilli();
    }

    public static Integer calculateAgeByBirthday(LocalDateTime birthday) {
        var now = LocalDateTime.now();
        var ageNow = Period.between(birthday.toLocalDate(), now.toLocalDate());
        return ageNow.getYears();
    }

    public static String formatDate(LocalDateTime eventDate) {
        return eventDate.format(DATE_FORMATTER);
    }

    public static String formatTime(LocalTime localTime) {
        return localTime.format(TIME_FORMATTER);
    }
}
