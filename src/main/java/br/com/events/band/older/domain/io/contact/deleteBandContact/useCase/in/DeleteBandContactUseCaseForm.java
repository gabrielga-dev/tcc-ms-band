package br.com.events.band.older.domain.io.contact.deleteBandContact.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to delete a band's contact at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class DeleteBandContactUseCaseForm {

    private String bandUuid;
    private String contactUuid;
}
