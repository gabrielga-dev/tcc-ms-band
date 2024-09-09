package br.com.events.band;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockConstants {

    public static final BigDecimal BIG_DECIMAL = BigDecimal.ONE;
    public static final Boolean BOOLEAN = Boolean.TRUE;
    public static final Integer INTEGER = 1;
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    public static final Long LONG = 1L;
    public static final String STRING = "Test";

}
