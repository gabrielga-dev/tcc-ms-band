package br.com.events.band.domain.mapper.band;

import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.entity.Contact;
import br.com.events.band.domain.entity.Musician;
import br.com.events.band.domain.entity.address.BandAddress;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.in.FindAuthenticatedPersonBandsRestFilters;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out.BandAddressFindAuthenticatedPersonBandsRestResult;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out.ContactFindAuthenticatedPersonBandsRestResult;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out.FindAuthenticatedPersonBandsRestResult;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out.MusicianFindAuthenticatedPersonBandsRestResult;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.useCase.in.FindAuthenticatedPersonBandsUseCaseFilters;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.useCase.out.BandAddressFindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.useCase.out.ContactFindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.useCase.out.FindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.useCase.out.MusicianFindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.infrastructure.process.address.cityName.GetCityNameByIdProcess;
import br.com.events.band.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class holds every needed mapping method that is needed at filtering the authenticated person's bands feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class FindAuthenticatedPersonBandsMapper {

    private final GetCityNameByIdProcess getCityNameByIdProcess;

    public static FindAuthenticatedPersonBandsUseCaseFilters toUseCaseFilters(
            Pageable pageable, FindAuthenticatedPersonBandsRestFilters filters
    ) {
        return FindAuthenticatedPersonBandsUseCaseFilters
                .builder()
                .name(filters.getName())
                .creationDateStart(
                        DateUtil.millisecondsToLocalDateTime(filters.getCreationDateStartMilliseconds())
                )
                .creationDateEnd(
                        DateUtil.millisecondsToLocalDateTime(filters.getCreationDateEndMilliseconds())
                )
                .pageable(limitPageable(pageable))
                .build();
    }

    private static Pageable limitPageable(Pageable pageable) {
        if (pageable.getPageSize() > 25) {
            return Pageable.ofSize(25);
        }
        return pageable;
    }

    public FindAuthenticatedPersonBandsRestResult toRestControllerResult(
            FindAuthenticatedPersonBandsUseCaseResult result
    ) {
        var mappedMusicians = result.getMusicians()
                .stream()
                .map(this::toUseCaseResult)
                .collect(Collectors.toList());

        var mappedAddress = toUseCaseResult(result.getAddress());

        var mappedContacts = result.getContacts()
                .stream()
                .map(this::toUseCaseResult)
                .collect(Collectors.toList());

        return FindAuthenticatedPersonBandsRestResult
                .builder()
                .uuid(result.getUuid())
                .name(result.getName())
                .description(result.getDescription())
                .active(result.getActive())
                .creationDateMilliseconds(
                        Objects.isNull(result.getCreationDate())
                                ? null
                                : DateUtil.localDateTimeToMilliseconds(result.getCreationDate())
                ).updateDateMilliseconds(
                        Objects.isNull(result.getUpdateDate())
                                ? null
                                : DateUtil.localDateTimeToMilliseconds(result.getUpdateDate())
                ).musicians(mappedMusicians)
                .address(mappedAddress)
                .contacts(mappedContacts)
                .numberOfMusics(result.getNumberOfMusics())
                .build();
    }

    private ContactFindAuthenticatedPersonBandsRestResult toUseCaseResult(
            ContactFindAuthenticatedPersonBandsUseCaseResult contact
    ) {
        return ContactFindAuthenticatedPersonBandsRestResult
                .builder()
                .type(contact.getType())
                .content(contact.getContent())
                .build();
    }

    private BandAddressFindAuthenticatedPersonBandsRestResult toUseCaseResult(
            BandAddressFindAuthenticatedPersonBandsUseCaseResult address
    ) {
        var param = GetCityNameByIdProcessRequest
                .builder()
                .countryIso(address.getCountry())
                .stateIso(address.getState())
                .cityId(address.getCity())
                .build();

        return BandAddressFindAuthenticatedPersonBandsRestResult
                .builder()
                .street(address.getStreet())
                .neighbour(address.getNeighbour())
                .complement(address.getComplement())
                .city(getCityNameByIdProcess.execute(param))
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }

    private MusicianFindAuthenticatedPersonBandsRestResult toUseCaseResult(
            MusicianFindAuthenticatedPersonBandsUseCaseResult musician
    ) {
        return MusicianFindAuthenticatedPersonBandsRestResult
                .builder()
                .uuid(musician.getUuid())
                .firstName(musician.getFirstName())
                .lastName(musician.getLastName())
                .age(musician.getAge())
                .creationDateMilliseconds(
                        Objects.isNull(musician.getCreationDate())
                                ? null
                                : DateUtil.localDateTimeToMilliseconds(musician.getCreationDate()))
                .avatarUuid(musician.getAvatarUuid())
                .build();
    }

    public static FindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(Band band) {
        var mappedMusicians = band.getMusicians()
                .stream()
                .filter(Musician::getActive)
                .map(FindAuthenticatedPersonBandsMapper::toUseCaseResult)
                .collect(Collectors.toList());

        var mappedAddress = toUseCaseResult(band.getAddress());

        var mappedContacts = band.getContacts()
                .stream()
                .map(FindAuthenticatedPersonBandsMapper::toUseCaseResult)
                .collect(Collectors.toList());

        return FindAuthenticatedPersonBandsUseCaseResult
                .builder()
                .uuid(band.getUuid())
                .name(band.getName())
                .description(band.getDescription())
                .active(band.getActive())
                .creationDate(band.getCreationDate())
                .updateDate(band.getUpdateDate())
                .musicians(mappedMusicians)
                .address(mappedAddress)
                .contacts(mappedContacts)
                .numberOfMusics(band.getMusics().size())
                .build();
    }

    private static ContactFindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(Contact contact) {
        return ContactFindAuthenticatedPersonBandsUseCaseResult
                .builder()
                .type(contact.getType())
                .content(contact.getContent())
                .build();
    }

    private static BandAddressFindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(BandAddress address) {
        return BandAddressFindAuthenticatedPersonBandsUseCaseResult
                .builder()
                .street(address.getStreet())
                .neighbour(address.getNeighbour())
                .complement(address.getComplement())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }

    private static MusicianFindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(Musician musician) {
        return MusicianFindAuthenticatedPersonBandsUseCaseResult
                .builder()
                .uuid(musician.getUuid())
                .firstName(musician.getFirstName())
                .lastName(musician.getLastName())
                .age(DateUtil.calculateAgeByBirthday(musician.getBirthday()))
                .creationDate(musician.getCreationDate())
                .avatarUuid(musician.getAvatarUuid())
                .build();
    }
}
