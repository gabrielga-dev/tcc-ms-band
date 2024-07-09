package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.band.SaveBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.data.model.table.band.BandTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link RemoveBandProfilePictureUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class RemoveBandProfilePictureUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private SaveBandCommand saveBandCommand;
    @InjectMocks
    private RemoveBandProfilePictureUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNonExistenceException")
    void executeWhenBandIsNotFoundThenThrowsBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(BandNonExistenceException.class, () -> useCase.execute(MockConstants.STRING));

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(eq(MockConstants.STRING), eq(MockConstants.STRING));
        verify(saveBandCommand, never()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when band is found, then remove profile picture")
    void executeWhenBandIsFoundThenRemoveProfilePicture() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var mockedBand = mock(BandTable.class);
        doNothing().when(mockedBand).removeProfilePicture();
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(mockedBand));
        when(saveBandCommand.execute(any(BandTable.class))).thenReturn(new BandTable());

        Assertions.assertDoesNotThrow(() -> useCase.execute(MockConstants.STRING));

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(eq(MockConstants.STRING), eq(MockConstants.STRING));
        verify(mockedBand, atMostOnce()).removeProfilePicture();
        verify(saveBandCommand, atMostOnce()).execute(any(BandTable.class));
    }
}
