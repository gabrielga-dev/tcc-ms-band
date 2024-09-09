package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.music.SaveMusicCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.data.io.music.request.MusicRequestMock;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.music.MusicTable;
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
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ContributeMusicUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class ContributeMusicUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private SaveMusicCommand saveMusicCommand;

    @InjectMocks
    private ContributeMusicUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandOwnerException")
    void executeWhenBandIsNotFoundThenThrowsBandOwnerException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.empty());

        var request = MusicRequestMock.build();
        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicCommand, never()).execute(any(MusicTable.class));
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        bandFound.setActive(false);
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));

        var request = MusicRequestMock.build();
        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicCommand, never()).execute(any(MusicTable.class));
    }

    @Test
    @DisplayName("execute - when band is active, then save new music")
    void executeWhenBandIsActiveThenSaveNewMusic() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));
        var savedMusic = new MusicTable();
        savedMusic.setUuid(MockConstants.STRING.repeat(2));
        when(saveMusicCommand.execute(any(MusicTable.class))).thenReturn(savedMusic);

        var request = MusicRequestMock.build();
        var returned = useCase.execute(MockConstants.STRING, request);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(savedMusic.getUuid(), returned.getUuid());

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicCommand, atMostOnce()).execute(any(MusicTable.class));
    }

}
