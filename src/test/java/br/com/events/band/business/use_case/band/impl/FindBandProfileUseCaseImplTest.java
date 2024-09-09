package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.model.table.band.BandTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandProfileUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandProfileUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private BuildAddressResponseCommand buildAddressResponseCommand;

    @InjectMocks
    private FindBandProfileUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throw BandNonExistenceException")
    void executeWhenBandIsNotFoundThenThrowBandNonExistenceException() {
        when(findBandCommand.byUuid(MockConstants.STRING)).thenReturn(Optional.empty());

        Assertions.assertThrows(BandNonExistenceException.class, () -> useCase.execute(MockConstants.STRING));

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, never()).isAuthenticated();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(buildAddressResponseCommand, never()).execute(any(IAddress.class));
    }

    @Test
    @DisplayName("execute - when person can see profile, then return band profile")
    void executeWhenPersonCanSeeProfileThenReturnBandProfile() {
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuid(MockConstants.STRING)).thenReturn(Optional.of(bandFound));
        when(authService.isAuthenticated()).thenReturn(true);
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(bandFound.getUuid(), returned.getUuid());

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).isAuthenticated();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(buildAddressResponseCommand, atMostOnce()).execute(any(IAddress.class));
    }

    @Test
    @DisplayName("execute - when person is not authenticated and band is not active, " +
            "then throw BandNonExistenceException")
    void executeWhenPersonIsNotAuthenticatedAndBandIsNotActiveThenThrowBandNonExistenceException() {
        var bandFound = BandTableMock.build();
        bandFound.setActive(false);
        when(findBandCommand.byUuid(MockConstants.STRING)).thenReturn(Optional.of(bandFound));
        when(authService.isAuthenticated()).thenReturn(false);

        Assertions.assertThrows(BandNonExistenceException.class, () -> useCase.execute(MockConstants.STRING));

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).isAuthenticated();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(buildAddressResponseCommand, never()).execute(any(IAddress.class));
    }

    @Test
    @DisplayName("execute - when person is not the band owner and band is not active, " +
            "then throw BandNonExistenceException")
    void executeWhenPersonIsNotTheBandOwnerAndBandIsNotActiveThenThrowBandNonExistenceException() {
        var bandFound = BandTableMock.build();
        bandFound.setActive(false);
        when(findBandCommand.byUuid(MockConstants.STRING)).thenReturn(Optional.of(bandFound));
        when(authService.isAuthenticated()).thenReturn(true);
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING.repeat(2));

        Assertions.assertThrows(BandNonExistenceException.class, () -> useCase.execute(MockConstants.STRING));

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).isAuthenticated();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(buildAddressResponseCommand, never()).execute(any(IAddress.class));
    }
}
