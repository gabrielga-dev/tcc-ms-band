package br.com.events.band.older.domain.mapper.contact;

import br.com.events.band.older.domain.io.contact.deleteBandContact.useCase.in.DeleteBandContactUseCaseForm;
import br.com.events.band.older.domain.io.process.contact.operate.OperateBandContactDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed mapping method at the delete band contact feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeleteBandContactMapper {

    public static DeleteBandContactUseCaseForm from(String bandUuid, String contactUuid){
        return DeleteBandContactUseCaseForm
                .builder()
                .bandUuid(bandUuid)
                .contactUuid(contactUuid)
                .build();
    }

    public static OperateBandContactDTO from(DeleteBandContactUseCaseForm form){
        return OperateBandContactDTO
                .builder()
                .bandUuid(form.getBandUuid())
                .contactUuid(form.getContactUuid())
                .build();
    }
}
