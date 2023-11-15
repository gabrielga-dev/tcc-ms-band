package br.com.events.band.newer.business.use_case.contact.impl;

import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.command.contact.DeleteContactCommand;
import br.com.events.band.newer.business.use_case.contact.DeleteBandContactUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.util.AuthUtil;
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

    private final FindBandCommand findBandCommand;
    private final DeleteContactCommand deleteContactCommand;

    @Override
    public void execute(String bandUuid, String contactUuid) {
        findBandCommand.byUuidAndOwnerUuid(bandUuid, AuthUtil.getAuthenticatedPersonUuid())
                .ifPresentOrElse(
                        band -> deleteContactCommand.execute(contactUuid),
                        () -> {
                            throw new BandNonExistenceException();
                        }
                );
    }
}
