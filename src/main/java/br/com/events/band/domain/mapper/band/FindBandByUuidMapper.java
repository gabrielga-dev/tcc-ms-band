package br.com.events.band.domain.mapper.band;

import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.entity.Contact;
import br.com.events.band.domain.entity.Musician;
import br.com.events.band.domain.entity.address.BandAddress;
import br.com.events.band.domain.io.band.findByUuid.rest.out.BandAddressFindBandByUuidRestResult;
import br.com.events.band.domain.io.band.findByUuid.rest.out.ContactFindBandByUuidRestResult;
import br.com.events.band.domain.io.band.findByUuid.rest.out.FindBandByUuidRestResult;
import br.com.events.band.domain.io.band.findByUuid.rest.out.MusicianFindBandByUuidRestResult;
import br.com.events.band.domain.io.band.findByUuid.useCase.out.BandAddressFindBandByUuidUseCaseResult;
import br.com.events.band.domain.io.band.findByUuid.useCase.out.ContactFindBandByUuidUseCaseResult;
import br.com.events.band.domain.io.band.findByUuid.useCase.out.FindBandByUuidUseCaseResult;
import br.com.events.band.domain.io.band.findByUuid.useCase.out.MusicianFindBandByUuidUseCaseResult;
import br.com.events.band.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.infrastructure.process.address.cityName.GetCityNameByIdProcess;
import br.com.events.band.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindBandByUuidMapper {

    private final GetCityNameByIdProcess getCityNameByIdProcess;

    public static FindBandByUuidUseCaseResult from(Band band) {
        var mappedMusicians = band.getMusicians()
                .stream()
                .filter(Musician::getActive)
                .map(FindBandByUuidMapper::toUseCaseResult)
                .collect(Collectors.toList());

        var mappedAddress = toUseCaseResult(band.getAddress());

        var mappedContacts = band.getContacts()
                .stream()
                .map(FindBandByUuidMapper::toUseCaseResult)
                .collect(Collectors.toList());

        return FindBandByUuidUseCaseResult
                .builder()
                .uuid(band.getUuid())
                .name(band.getName())
                .description(band.getDescription())
                .creationDate(band.getCreationDate())
                .profilePictureUuid(band.getProfilePictureUuid())
                .ownerUuid(band.getOwnerUuid())
                .active(band.getActive())
                .musicians(mappedMusicians)
                .address(mappedAddress)
                .contacts(mappedContacts)
                .numberOfMusics(band.getMusics().size())
                .build();
    }

    private static ContactFindBandByUuidUseCaseResult toUseCaseResult(Contact contact) {
        return ContactFindBandByUuidUseCaseResult
                .builder()
                .uuid(contact.getUuid())
                .type(contact.getType())
                .content(contact.getContent())
                .build();
    }

    private static BandAddressFindBandByUuidUseCaseResult toUseCaseResult(BandAddress address) {
        return BandAddressFindBandByUuidUseCaseResult
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

    private static MusicianFindBandByUuidUseCaseResult toUseCaseResult(Musician musician) {
        return MusicianFindBandByUuidUseCaseResult
                .builder()
                .uuid(musician.getUuid())
                .firstName(musician.getFirstName())
                .lastName(musician.getLastName())
                .avatarUuid(musician.getAvatarUuid())
                .age(DateUtil.calculateAgeByBirthday(musician.getBirthday()))
                .creationDate(musician.getCreationDate())
                .build();
    }


    public FindBandByUuidRestResult from(FindBandByUuidUseCaseResult band) {
        var mappedAddress = toUseCaseResult(band.getAddress());
        var mappedMusicians = toRestResult(band.getMusicians());
        var mappedContacts = toRestResultContacts(band.getContacts());

        return FindBandByUuidRestResult
                .builder()
                .uuid(band.getUuid())
                .name(band.getName())
                .description(band.getDescription())
                .active(band.getActive())
                .profilePictureUuid(band.getProfilePictureUuid())
                .ownerUuid(band.getOwnerUuid())
                .creationDateMilliseconds(DateUtil.localDateTimeToMilliseconds(band.getCreationDate()))
                .musicians(mappedMusicians)
                .address(mappedAddress)
                .contacts(mappedContacts)
                .numberOfMusics(band.getNumberOfMusics())
                .build();
    }

    private List<ContactFindBandByUuidRestResult> toRestResultContacts(List<ContactFindBandByUuidUseCaseResult> contacts) {
        return contacts.stream().map(this::toRestResultContact).collect(Collectors.toList());
    }

    private ContactFindBandByUuidRestResult toRestResultContact(ContactFindBandByUuidUseCaseResult contact) {
        return ContactFindBandByUuidRestResult
                .builder()
                .uuid(contact.getUuid())
                .type(contact.getType())
                .content(contact.getContent())
                .build();
    }

    private List<MusicianFindBandByUuidRestResult> toRestResult(List<MusicianFindBandByUuidUseCaseResult> musicians) {
        return musicians.stream().map(this::toRestResult).collect(Collectors.toList());
    }

    private MusicianFindBandByUuidRestResult toRestResult(MusicianFindBandByUuidUseCaseResult musicians) {
        return MusicianFindBandByUuidRestResult
                .builder()
                .uuid(musicians.getUuid())
                .firstName(musicians.getFirstName())
                .lastName(musicians.getLastName())
                .avatarUuid(musicians.getAvatarUuid())
                .age(musicians.getAge())
                .creationDateMilliseconds(DateUtil.localDateTimeToMilliseconds(musicians.getCreationDate()))
                .build();
    }

    private BandAddressFindBandByUuidRestResult toUseCaseResult(BandAddressFindBandByUuidUseCaseResult address) {
        var param = GetCityNameByIdProcessRequest
                .builder()
                .countryIso(address.getCountry())
                .stateIso(address.getState())
                .cityId(address.getCity())
                .build();

        return BandAddressFindBandByUuidRestResult
                .builder()
                .street(address.getStreet())
                .neighbour(address.getNeighbour())
                .complement(address.getComplement())
                .city(getCityNameByIdProcess.execute(param))
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }
}
