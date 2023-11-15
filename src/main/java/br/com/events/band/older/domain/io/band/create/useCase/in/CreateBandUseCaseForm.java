package br.com.events.band.older.domain.io.band.create.useCase.in;

import br.com.events.band.older.domain.io.band.create.rest.in.ContactCreateBandRestForm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class holds every needed information about the new band at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CreateBandUseCaseForm {

    private String name;
    private String description;
    private AddressCreateBandUseCaseForm address;
    private List<ContactCreateBandRestForm> contacts;
}
