package br.com.events.band.domain.mapper.contact;

import br.com.events.band.domain.entity.Contact;
import br.com.events.band.domain.io.contact.createBandContact.rest.in.CreateBandContactRestForm;
import br.com.events.band.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;
import br.com.events.band.domain.io.process.contact.operate.OperateBandContactDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class holds every needed mapping process that the Create Band Contact needs
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateBandContactMapper {

    public static CreateBandContactUseCaseForm from(String uuid, CreateBandContactRestForm origial){
        return CreateBandContactUseCaseForm
                .builder()
                .bandUuid(uuid)
                .type(origial.getType())
                .content(origial.getContent())
                .build();
    }

    public static OperateBandContactDTO toDtoToValidate(CreateBandContactUseCaseForm original) {
        return OperateBandContactDTO
                .builder()
                .bandUuid(original.getBandUuid())
                .build();
    }

    public static Contact from(CreateBandContactUseCaseForm original) {
        var toReturn = new Contact();
        toReturn.setType(original.getType());
        toReturn.setContent(original.getContent());
        toReturn.setCreationDate(LocalDateTime.now());
        return toReturn;
    }
}
