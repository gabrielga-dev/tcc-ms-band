package br.com.events.band.business.use_case.music.impl;


import br.com.events.band.MockConstants;
import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.command.music.SaveMusicCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.music.MusicNonExistenceException;
import br.com.events.band.data.io.music.request.MusicRequestMock;
import br.com.events.band.data.model.table.band.BandTable;
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
 * Tests for {@link UpdateMusicUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class UpdateMusicUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindMusicCommand findMusicCommand;
    @Mock
    private SaveMusicCommand saveMusicCommand;

    @InjectMocks
    private UpdateMusicUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when music is not found, then throws MusicNonExistenceException")
    void executeWhenMusicIsNotFoundThenThrowsMusicNonExistenceException() {
        when(findMusicCommand.byUuid(anyString())).thenReturn(Optional.empty());

        var request = MusicRequestMock.build();
        Assertions.assertThrows(
                MusicNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findMusicCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, never()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when person is not band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        var musicFound = MusicTableMock.build();
        musicFound.setContributingBand(new BandTable());
        musicFound.getContributingBand().setOwnerUuid(MockConstants.STRING.repeat(2));
        when(findMusicCommand.byUuid(anyString())).thenReturn(Optional.of(musicFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var request = MusicRequestMock.build();
        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findMusicCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when person is band owner, then update music")
    void executeWhenPersonIsBandOwnerThenUpdateMusic() {
        var musicFound = MusicTableMock.build();
        musicFound.setContributingBand(new BandTable());
        musicFound.getContributingBand().setOwnerUuid(MockConstants.STRING);
        when(findMusicCommand.byUuid(anyString())).thenReturn(Optional.of(musicFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(saveMusicCommand.execute(any(MusicTable.class))).thenReturn(musicFound);

        var request = MusicRequestMock.build();
        request.setName(MockConstants.STRING.repeat(2));
        var response = useCase.execute(MockConstants.STRING, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(musicFound.getUuid(), response.getUuid());

        verify(findMusicCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }
}
