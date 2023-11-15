package br.com.events.band.older.domain.mapper.musician;

import br.com.events.band.newer.data.table.MusicianTable;
import br.com.events.band.newer.data.table.addresses.MusicianAddressTable;
import br.com.events.band.older.domain.io.musician.update.rest.in.AddressUpdateMusicianRestForm;
import br.com.events.band.older.domain.io.musician.update.rest.in.UpdateMusicianRestForm;
import br.com.events.band.older.domain.io.musician.update.useCase.in.AddressUpdateMusicianUseCaseForm;
import br.com.events.band.older.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.older.util.DateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateMusicianMapper {

    public static UpdateMusicianUseCaseForm from(UpdateMusicianRestForm form, String bandUuid, String musicianUuid){
        return UpdateMusicianUseCaseForm
                .builder()
                .bandUuid(bandUuid)
                .musicianUuid(musicianUuid)
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .birthday(DateUtil.millisecondsToLocalDateTime(form.getBirthday()))
                .cpf(form.getCpf())
                .email(form.getEmail())
                .address(UpdateMusicianMapper.from(form.getAddress()))
                .build();
    }

    private static AddressUpdateMusicianUseCaseForm from(AddressUpdateMusicianRestForm address) {
        return AddressUpdateMusicianUseCaseForm
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

    public static void transferData(MusicianTable musician, UpdateMusicianUseCaseForm data) {
        musician.setFirstName(data.getFirstName());
        musician.setLastName(data.getLastName());
        musician.setBirthday(data.getBirthday());
        musician.setCpf(data.getCpf());
        musician.setEmail(data.getEmail());
        musician.setUpdateDate(LocalDateTime.now());
        transferData(musician.getAddress(), data.getAddress());
    }

    private static void transferData(
            MusicianAddressTable musicianAddress, AddressUpdateMusicianUseCaseForm address
    ) {
        musicianAddress.setStreet(address.getStreet());
        musicianAddress.setNeighbour(address.getNeighbour());
        musicianAddress.setComplement(address.getComplement());
        musicianAddress.setCity(address.getCityId());
        musicianAddress.setState(address.getStateIso());
        musicianAddress.setCountry(address.getCountryIso());
        musicianAddress.setZipCode(address.getZipCode());
        musicianAddress.setLatitude(null);
        musicianAddress.setLongitude(null);
    }
}
