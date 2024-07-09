package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.command.music.SaveMusicCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.music.MusicNonExistenceException;
import br.com.events.band.data.model.table.music.MusicTable;
import br.com.events.band.data.model.table.music.MusicTableMock;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DeactivateMusicUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeactivateMusicUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindMusicCommand findMusicCommand;
    @Mock
    private SaveMusicCommand saveMusicCommand;
    @InjectMocks
    private DeactivateMusicUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when music is not found, then throws MusicNonExistenceException")
    void executeWhenMusicIsNotFoundThenThrowsMusicNonExistenceException() {
        when(findMusicCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                MusicNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(saveMusicCommand, never()).execute(any(MusicTable.class));
    }

    @Test
    @DisplayName("execute - when person is not band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        var musicFound = MusicTableMock.build();
        musicFound.getContributingBand().setOwnerUuid(MockConstants.STRING.repeat(2));
        when(findMusicCommand.byUuid(anyString())).thenReturn(Optional.of(musicFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicCommand, never()).execute(any(MusicTable.class));
    }

    @Test
    @DisplayName("execute - when person is band owner, then deactivate music")
    void executeWhenPersonIsBandOwnerThenDeactivateMusic() {
        var musicFound = MusicTableMock.build();
        musicFound.setActive(true);
        musicFound.getContributingBand().setOwnerUuid(MockConstants.STRING);
        when(findMusicCommand.byUuid(anyString())).thenReturn(Optional.of(musicFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertDoesNotThrow(() -> useCase.execute(MockConstants.STRING));

        Assertions.assertFalse(musicFound.isActive());

        verify(findMusicCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicCommand, atMostOnce()).execute(any(MusicTable.class));
    }
}
