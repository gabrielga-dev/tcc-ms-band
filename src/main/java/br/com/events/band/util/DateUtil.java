package br.com.events.band.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * This class is used when is needed to work with dates
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {


    public static LocalDateTime millisecondsToLocalDateTime(Long milliseconds){
        if (Objects.isNull(milliseconds)){
            return null;
        }
        var instant = Instant.ofEpochMilli(milliseconds);
        var zonedDateTime = instant.atZone(ZoneOffset.UTC);

        return zonedDateTime.toLocalDateTime();
    }

    public static Long localDateTimeToMilliseconds(LocalDateTime localDateTime) {
        var zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime.toInstant().toEpochMilli();
    }

    public static Integer calculateAgeByBirthday(LocalDateTime birthday) {
        var now = LocalDateTime.now();
        var ageNow = Period.between(birthday.toLocalDate(), now.toLocalDate());
        return ageNow.getYears();
    }
}
