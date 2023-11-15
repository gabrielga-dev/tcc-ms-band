package br.com.events.band.older.domain.mapper.musician;

import br.com.events.band.older.domain.entity.Musician;
import br.com.events.band.older.domain.entity.address.MusicianAddress;
import br.com.events.band.older.domain.io.musician.create.rest.in.AddressCreateMusicianRestForm;
import br.com.events.band.older.domain.io.musician.create.rest.in.CreateMusicianRestForm;
import br.com.events.band.older.domain.io.musician.create.useCase.in.AddressCreateMusicianUseCaseForm;
import br.com.events.band.older.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.older.util.DateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class holds every needed mapping process that the create musician feature needs
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateMusicianMapper {

    public static CreateMusicianUseCaseForm from(CreateMusicianRestForm form, String bandUuid){
        return CreateMusicianUseCaseForm
                .builder()
                .bandUuid(bandUuid)
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .birthday(DateUtil.millisecondsToLocalDateTime(form.getBirthday()))
                .cpf(form.getCpf())
                .email(form.getEmail())
                .address(CreateMusicianMapper.from(form.getAddress()))
                .build();
    }

    private static AddressCreateMusicianUseCaseForm from(AddressCreateMusicianRestForm address) {
        return AddressCreateMusicianUseCaseForm
                .builder()
                .street(address.getStreet())
                .neighbour(address.getNeighbour())
                .complement(address.getComplement())
                .cityId(address.getCityId())
                .stateIso(address.getStateIso())
                .countryIso(address.getCountryIso())
                .zipCode(address.getZipCode())
                .build();
    }

    public static Musician toEntity(CreateMusicianUseCaseForm form) {
        var toReturn = new Musician();

        toReturn.setFirstName(form.getFirstName());
        toReturn.setLastName(form.getLastName());
        toReturn.setBirthday(form.getBirthday());
        toReturn.setCpf(form.getCpf());
        toReturn.setEmail(form.getEmail());
        toReturn.setCreationDate(LocalDateTime.now());

        var address = toEntity(form.getAddress());
        address.setMusician(toReturn);
        toReturn.setAddress(address);

        return toReturn;
    }

    private static MusicianAddress toEntity(AddressCreateMusicianUseCaseForm address) {
        var toReturn = new MusicianAddress();
        toReturn.setStreet(address.getStreet());
        toReturn.setNeighbour(address.getNeighbour());
        toReturn.setComplement(address.getComplement());
        toReturn.setCity(address.getCityId());
        toReturn.setState(address.getStateIso());
        toReturn.setCountry(address.getCountryIso());
        toReturn.setZipCode(address.getZipCode());
        toReturn.setLatitude(null);
        toReturn.setLongitude(null);

        return toReturn;
    }
}
