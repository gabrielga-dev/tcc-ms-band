package br.com.events.band.newer.business.use_case.contact;

/**
 * This interface dictates what is needed to delete a band's contact
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface DeleteBandContactUseCase {

    void execute(String bandUuid, String contactUuid);
}
