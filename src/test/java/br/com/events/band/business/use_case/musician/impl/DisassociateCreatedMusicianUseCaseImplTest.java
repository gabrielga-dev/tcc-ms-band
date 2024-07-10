package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.band_musician.DeleteBandMusicianAssociationCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.exception.musician.MusicianNotAssociatedWithBandException;
import br.com.events.band.data.model.table.band.BandMusicianTable;
import br.com.events.band.data.model.table.band.BandMusicianTableMock;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DisassociateCreatedMusicianUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DisassociateCreatedMusicianUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private DeleteBandMusicianAssociationCommand deleteBandMusicianAssociationCommand;
    @InjectMocks
    private DisassociateCreatedMusicianUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNonExistenceException")
    void executeWhenBandIsNotFoundThenThrowsBandNonExistenceException() {
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byUuid(anyString());
        verify(deleteBandMusicianAssociationCommand, never()).execute(any(BandMusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is not band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        var bandFound = BandTableMock.build();
        bandFound.setOwnerUuid(MockConstants.STRING.repeat(2));
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byUuid(anyString());
        verify(deleteBandMusicianAssociationCommand, never()).execute(any(BandMusicianTable.class));
    }

    @Test
    @DisplayName("execute - when musician is not found, then throws MusicianExistsException")
    void executeWhenMusicianIsNotFoundThenThrowsMusicianExistsException() {
        var bandFound = BandTableMock.build();
        bandFound.setOwnerUuid(MockConstants.STRING);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byUuid(anyString());
        verify(deleteBandMusicianAssociationCommand, never()).execute(any(BandMusicianTable.class));
    }

    @Test
    @DisplayName("execute - when musician is not associated with found band, " +
            "then throws MusicianNotAssociatedWithBandException")
    void executeWhenMusicianIsNotAssociatedWithFoundBandThenThrowsMusicianNotAssociatedWithBandException() {
        var bandFound = BandTableMock.build();
        bandFound.setOwnerUuid(MockConstants.STRING);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));

        Assertions.assertThrows(
                MusicianNotAssociatedWithBandException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byUuid(anyString());
        verify(deleteBandMusicianAssociationCommand, never()).execute(any(BandMusicianTable.class));
    }

    @Test
    @DisplayName("execute - when musician is associated with found band but association is not found, " +
            "then throws MusicianNotAssociatedWithBandException")
    void executeWhenMusicianIsAssociatedWithFoundBandButAssociationIsNotFoundThenThrowsMusicianNotAssociatedWithBandException() {
        var bandFound = mock(BandTable.class);
        when(bandFound.getOwnerUuid()).thenReturn(MockConstants.STRING);
        when(bandFound.isAssociatedWith(any(MusicianTable.class))).thenReturn(true);
        when(bandFound.getAssociation(any(MusicianTable.class))).thenReturn(Optional.empty());
        bandFound.setOwnerUuid(MockConstants.STRING);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));

        Assertions.assertThrows(
                MusicianNotAssociatedWithBandException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byUuid(anyString());
        verify(deleteBandMusicianAssociationCommand, never()).execute(any(BandMusicianTable.class));
    }

    @Test
    @DisplayName("execute - when musician is associated with found band, then disassociate them")
    void executeWhenMusicianIsAssociatedWithFoundBandThenDisassociateThem() {
        var bandFound = mock(BandTable.class);
        when(bandFound.getOwnerUuid()).thenReturn(MockConstants.STRING);
        when(bandFound.isAssociatedWith(any(MusicianTable.class))).thenReturn(true);
        var association = BandMusicianTableMock.build();
        when(bandFound.getAssociation(any(MusicianTable.class))).thenReturn(Optional.of(association));
        bandFound.setOwnerUuid(MockConstants.STRING);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        doNothing().when(deleteBandMusicianAssociationCommand).execute(any(BandMusicianTable.class));

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byUuid(anyString());
        verify(deleteBandMusicianAssociationCommand, atMostOnce()).execute(any(BandMusicianTable.class));
    }
}
