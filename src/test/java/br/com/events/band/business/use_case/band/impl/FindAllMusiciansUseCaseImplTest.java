package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.data.model.table.band.BandTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindAllMusiciansUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
public class FindAllMusiciansUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;

    @InjectMocks
    private FindAllMusiciansUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when person is band owner, then return musicians")
    public void executeWhenPersonIsBandOwnerThenReturnMusicians() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        bandFound.setOwnerUuid(MockConstants.STRING);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(bandFound.getAllMusicians().size(), returned.size());

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when band is not found, then throw BandNonExistenceException")
    public void executeWhenBandIsNotFoundThenThrowBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        bandFound.setOwnerUuid(MockConstants.STRING);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(bandFound.getAllMusicians().size(), returned.size());

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when person is not band owner, then throws band owner exception")
    public void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING.repeat(2));
        var bandFound = BandTableMock.build();
        bandFound.setOwnerUuid(MockConstants.STRING);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));

        Assertions.assertThrows(BandOwnerException.class, () -> useCase.execute(MockConstants.STRING));

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }
}
