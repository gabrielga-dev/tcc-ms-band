package br.com.events.band.domain.mapper.band;

import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.entity.Contact;
import br.com.events.band.domain.entity.address.BandAddress;
import br.com.events.band.domain.io.auth.AuthenticatedPerson;
import br.com.events.band.domain.io.band.create.rest.in.AddressCreateBandRestForm;
import br.com.events.band.domain.io.band.create.rest.in.ContactCreateBandRestForm;
import br.com.events.band.domain.io.band.create.rest.in.CreateBandRestForm;
import br.com.events.band.domain.io.band.create.rest.out.CreateBandRestResult;
import br.com.events.band.domain.io.band.create.useCase.in.AddressCreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.in.ContactCreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.out.CreateBandUseCaseResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * This class holds every needed mapping method that is needed at band creation feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateBandMapper {

    public static CreateBandUseCaseForm toUseCaseForm(CreateBandRestForm bandRestForm) {
        return CreateBandUseCaseForm
            .builder()
            .name(bandRestForm.getName())
            .description(bandRestForm.getDescription())
            .address(address(bandRestForm.getAddress()))
            .contacts(bandRestForm.getContacts().stream().map(CreateBandMapper::contact).collect(Collectors.toList()))
            .build();
    }

    private static ContactCreateBandUseCaseForm contact(final ContactCreateBandRestForm contactCreateBandRestForm) {
        return ContactCreateBandUseCaseForm
            .builder()
            .type(contactCreateBandRestForm.getType())
            .content(contactCreateBandRestForm.getContent())
            .build();
    }

    private static AddressCreateBandUseCaseForm address(final AddressCreateBandRestForm address) {
        return AddressCreateBandUseCaseForm
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

    public static CreateBandRestResult toRestResult(final CreateBandUseCaseResult result) {
        return CreateBandRestResult
            .builder()
            .bandUuid(result.getBandUuid())
            .build();
    }

    public static Band toEntity(final CreateBandUseCaseForm bandUseCaseForm) {
        var band = new Band();
        band.setActive(true);
        band.setCreationDate(LocalDateTime.now());
        band.setName(bandUseCaseForm.getName());
        band.setDescription(bandUseCaseForm.getDescription());
        band.setAddress(address(band, bandUseCaseForm.getAddress()));
        band.setContacts(
            bandUseCaseForm.getContacts().stream().map(
                contact -> CreateBandMapper.contact(band, contact)
            ).collect(Collectors.toList())
        );
        band.setOwnerUuid(
            ((AuthenticatedPerson) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUuid()
        );
        return band;
    }

    private static BandAddress address(
        final Band band, final AddressCreateBandUseCaseForm address
    ) {
        var bandAddress = new BandAddress();
        bandAddress.setStreet(address.getStreet());
        bandAddress.setNeighbour(address.getNeighbour());
        bandAddress.setComplement(address.getComplement());
        bandAddress.setCity(address.getCity());
        bandAddress.setState(address.getState());
        bandAddress.setCountry(address.getCountry());
        bandAddress.setZipCode(address.getZipCode());
        bandAddress.setLatitude(address.getLatitude());
        bandAddress.setLongitude(address.getLongitude());
        bandAddress.setBand(band);
        return bandAddress;
    }

    private static Contact contact(
        final Band band, final ContactCreateBandUseCaseForm form
    ) {
        var contact = new Contact();
        contact.setBand(band);
        contact.setCreationDate(LocalDateTime.now());
        contact.setType(form.getType());
        contact.setContent(form.getContent());

        return contact;
    }

    public static CreateBandUseCaseResult toResult(final Band band) {
        return CreateBandUseCaseResult
            .builder()
            .bandUuid(band.getUuid())
            .build();
    }
}
