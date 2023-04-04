package br.com.events.band.domain.mapper.contact;

import br.com.events.band.domain.entity.Contact;
import br.com.events.band.domain.io.contact.updateBandContact.rest.in.UpdateBandContactRestForm;
import br.com.events.band.domain.io.contact.updateBandContact.useCase.in.UpdateBandContactUseCaseForm;
import br.com.events.band.domain.io.process.contact.operate.OperateBandContactDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed mapping process that the Update Band Contact needs
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateBandContactMapper {

    public static UpdateBandContactUseCaseForm from(
            String bandUuid, String contactUuid, UpdateBandContactRestForm original
    ){
        return UpdateBandContactUseCaseForm
                .builder()
                .bandUuid(bandUuid)
                .contactUuid(contactUuid)
                .type(original.getType())
                .content(original.getContent())
                .build();
    }

    public static OperateBandContactDTO toDtoToValidate(UpdateBandContactUseCaseForm original) {
        return OperateBandContactDTO
                .builder()
                .bandUuid(original.getBandUuid())
                .contactUuid(original.getContactUuid())
                .build();
    }

    public static void transferData(Contact original, UpdateBandContactUseCaseForm data) {
        original.setContent(data.getContent());
        original.setType(data.getType());
    }
}
