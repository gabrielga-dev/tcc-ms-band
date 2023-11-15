package br.com.events.band.older.application.process.musician.validations;

import br.com.events.band.older.application.process.musician.exception.MusicianBelowAgeException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MusicianBirthdayMusicianValidation implements MusicianMethodValidation {

    @Value("${minimun.musician.age}")
    private Integer minimumAge;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(MethodValidationType.CREATE, MethodValidationType.EDIT).contains(dto.getMethodType());
    }

    @Override
    public void validate(MusicianValidationDto toValidate) {
        var ageNow = DateUtil.calculateAgeByBirthday(toValidate.getForm().getBirthday());
        var isBelowAge = ageNow < minimumAge;
        if (isBelowAge) {
            throw new MusicianBelowAgeException(minimumAge);
        }
    }
}
