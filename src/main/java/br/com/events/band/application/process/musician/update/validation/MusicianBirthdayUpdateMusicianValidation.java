package br.com.events.band.application.process.musician.update.validation;

import br.com.events.band.application.process.musician.exception.MusicianBelowAgeException;
import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.musician.update.UpdateMusicianValidation;
import br.com.events.band.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicianBirthdayUpdateMusicianValidation implements UpdateMusicianValidation {

    @Value("${minimun.musician.age}")
    private Integer minimumAge;

    @Override
    public void validate(UpdateMusicianUseCaseForm toValidate) {
        var ageNow = DateUtil.calculateAgeByBirthday(toValidate.getBirthday());
        var isBelowAge = ageNow < minimumAge;
        if (isBelowAge) {
            throw new MusicianBelowAgeException(minimumAge);
        }
    }
}
