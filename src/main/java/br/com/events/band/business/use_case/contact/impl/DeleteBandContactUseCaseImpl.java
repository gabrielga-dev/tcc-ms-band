package br.com.events.band.business.use_case.contact.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.contact.DeleteContactCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.contact.DeleteBandContactUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the delete band's contact feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class DeleteBandContactUseCaseImpl implements DeleteBandContactUseCase {

    private final AuthService authService;
    private final FindBandCommand findBandCommand;
    private final DeleteContactCommand deleteContactCommand;

    @Override
    public void execute(String bandUuid, String contactUuid) {
        findBandCommand.byUuidAndOwnerUuid(bandUuid, authService.getAuthenticatedPersonUuid())
                .ifPresentOrElse(
                        band -> {
                            if (!band.isActive()) {
                                throw new BandNonExistenceException();
                            }
                            deleteContactCommand.execute(contactUuid);
                        },
                        () -> {
                            throw new BandNonExistenceException();
                        }
                );
    }
}
