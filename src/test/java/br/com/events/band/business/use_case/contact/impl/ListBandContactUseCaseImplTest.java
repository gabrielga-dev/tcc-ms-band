package br.com.events.band.business.use_case.contact.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.data.model.table.band.BandTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ListBandContactUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class ListBandContactUseCaseImplTest {

    @Mock
    private FindBandCommand findBandCommand;

    @InjectMocks
    private ListBandContactUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNonExistenceException")
    void executeWhenBandIsNotFoundThenThrowsBandNonExistenceException() {
        when(findBandCommand.byUuid(MockConstants.STRING)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        var bandFound = BandTableMock.build();
        bandFound.setActive(Boolean.FALSE);
        when(findBandCommand.byUuid(MockConstants.STRING)).thenReturn(Optional.of(bandFound));

        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when band is active, then return contacts")
    void executeWhenBandIsActiveThenReturnContacts() {
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuid(MockConstants.STRING)).thenReturn(Optional.of(bandFound));

        var contacts = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(contacts);
        Assertions.assertFalse(contacts.isEmpty());
        Assertions.assertEquals(bandFound.getContacts().size(), contacts.size());

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
    }
}
