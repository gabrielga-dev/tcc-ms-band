package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.band.SaveBandCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.music.MusicianAlreadyLinkedToBandException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link AssociateCreatedMusicianUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class AssociateCreatedMusicianUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private SaveBandCommand saveBandCommand;
    @InjectMocks
    private AssociateCreatedMusicianUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNonExistenceException")
    void executeWhenBandIsNotFoundThenThrowsBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byCpf(anyString());
        verify(saveBandCommand, never()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        bandFound.setActive(false);
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));

        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byCpf(anyString());
        verify(saveBandCommand, never()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when musician is not found, then throws MusicianDoesNotExistException")
    void executeWhenMusicianIsNtoFoundThenThrowsMusicianDoesNotExistException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());


        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(saveBandCommand, never()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when musician is already associated with band, then throws MusicianAlreadyLinkedToBandException")
    void executeWhenMusicianIsAlreadyAssociatedToBandThenThrowsMusicianAlreadyLinkedToBandException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));
        var musicianFound = mock(MusicianTable.class);
        when(musicianFound.isAssociatedWith(any(BandTable.class))).thenReturn(true);
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(musicianFound));

        Assertions.assertThrows(
                MusicianAlreadyLinkedToBandException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(saveBandCommand, never()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when musician is not associated with band, then associate it")
    void executeWhenMusicianIsNotAssociatedToBandThenAssociateIt() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(musicianFound));
        when(saveBandCommand.execute(any(BandTable.class))).thenReturn(bandFound);

        var returned = useCase.execute(MockConstants.STRING, MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(musicianFound.getUuid(), returned.getUuid());

        verify(findBandCommand, atMostOnce()).byUuid(MockConstants.STRING);
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(saveBandCommand, atMostOnce()).execute(any(BandTable.class));
    }
}
