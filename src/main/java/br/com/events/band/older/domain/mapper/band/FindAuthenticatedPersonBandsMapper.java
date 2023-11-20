package br.com.events.band.older.domain.mapper.band;

import br.com.events.band.newer.data.model.table.BandTable;
import br.com.events.band.newer.data.model.table.ContactTable;
import br.com.events.band.newer.data.model.table.MusicianTable;
import br.com.events.band.newer.data.model.table.addresses.BandAddressTable;
import br.com.events.band.newer.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.newer.data.io.address.response.AddressResponse;
import br.com.events.band.newer.data.io.contact.response.ContactResponse;
import br.com.events.band.newer.data.io.band.response.BandResponse;
import br.com.events.band.newer.data.io.musician.response.MusicianResponse;
import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.in.FindAuthenticatedPersonBandsUseCaseFilters;
import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.out.BandAddressFindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.out.ContactFindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.out.FindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.out.MusicianFindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.older.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.older.infrastructure.process.address.cityName.GetCityNameByIdProcess;
import br.com.events.band.older.util.DateUtil;
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
            Pageable pageable, AuthenticatedPersonBandsCriteria filters
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

    public BandResponse toRestControllerResult(
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

        return BandResponse
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
                ).profilePictureUuid(result.getProfilePictureUuid())
                .musicians(mappedMusicians)
                .address(mappedAddress)
                .contacts(mappedContacts)
                .numberOfMusics(result.getNumberOfMusics())
                .build();
    }

    private ContactResponse toUseCaseResult(
            ContactFindAuthenticatedPersonBandsUseCaseResult contact
    ) {
        return ContactResponse
                .builder()
                .type(contact.getType())
                .content(contact.getContent())
                .build();
    }

    private AddressResponse toUseCaseResult(
            BandAddressFindAuthenticatedPersonBandsUseCaseResult address
    ) {
        var param = GetCityNameByIdProcessRequest
                .builder()
                .countryIso(address.getCountry())
                .stateIso(address.getState())
                .cityId(address.getCity())
                .build();

        return AddressResponse
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

    private MusicianResponse toUseCaseResult(
            MusicianFindAuthenticatedPersonBandsUseCaseResult musician
    ) {
        return MusicianResponse
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

    public static FindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(BandTable band) {
        var mappedMusicians = band.getMusicians()
                .stream()
                .filter(MusicianTable::getActive)
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
                .profilePictureUuid(band.getProfilePictureUuid())
                .musicians(mappedMusicians)
                .address(mappedAddress)
                .contacts(mappedContacts)
                .numberOfMusics(band.getMusics().size())
                .build();
    }

    private static ContactFindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(ContactTable contact) {
        return ContactFindAuthenticatedPersonBandsUseCaseResult
                .builder()
                .type(contact.getType())
                .content(contact.getContent())
                .build();
    }

    private static BandAddressFindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(BandAddressTable address) {
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

    private static MusicianFindAuthenticatedPersonBandsUseCaseResult toUseCaseResult(MusicianTable musician) {
        return MusicianFindAuthenticatedPersonBandsUseCaseResult
                .builder()
                .uuid(musician.getUuid())
                .firstName(musician.getFirstName())
                .lastName(musician.getLastName())
                .age(DateUtil.calculateAgeByBirthday(musician.getBirthday()))
                .creationDate(musician.getCreationDate())
                .avatarUuid(musician.getProfilePictureUuid())
                .build();
    }
}
