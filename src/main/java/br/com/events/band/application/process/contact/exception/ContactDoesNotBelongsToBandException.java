package br.com.events.band.application.process.contact.exception;

import br.com.events.band.infrastructure.exception.badRequest.BadRequestException;

/**
 * This exception is thrown when someone tries to delete a contact from a band but the contact does not
 * belong to the given band
 *
 * @author Gabriel Guimarães de Almeida
 */
public class ContactDoesNotBelongsToBandException extends BadRequestException {

    public ContactDoesNotBelongsToBandException() {
        super(
                "Contato não pertence à banda!",
                "Você não pode deletar este contate desta banda, pois ele não pertence à ela!"
        );
    }
}
