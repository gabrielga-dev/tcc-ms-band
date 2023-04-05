package br.com.events.band.application.process.musician.create.validation;

import br.com.events.band.application.process.musician.exception.MusicianBelowAgeException;
import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.musician.create.CreateMusicianValidation;
import br.com.events.band.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicianBirthdayCreateMusicianValidation implements CreateMusicianValidation {

    @Value("${minimun.musician.age}")
    private Integer minimumAge;

    @Override
    public void validate(CreateMusicianUseCaseForm toValidate) {
        var ageNow = DateUtil.calculateAgeByBirthday(toValidate.getBirthday());
        var isBelowAge = ageNow < minimumAge;
        if (isBelowAge) {
            throw new MusicianBelowAgeException(minimumAge);
        }
    }
}
