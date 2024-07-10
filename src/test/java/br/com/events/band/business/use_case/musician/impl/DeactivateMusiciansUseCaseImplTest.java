package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band_musician.DeleteAllBandMusicianAssociationsCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianExistsException;
import br.com.events.band.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.data.io.auth.AuthenticatedPersonMock;
import br.com.events.band.data.io.person.response.PersonResponseMock;
import br.com.events.band.data.model.table.band.BandTable;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DeactivateMusiciansUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeactivateMusiciansUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private FindPersonMusicianCommand findPersonMusicianCommand;
    @Mock
    private SaveMusicianCommand saveMusicianCommand;
    @Mock
    private DeleteAllBandMusicianAssociationsCommand deleteAllBandMusicianAssociationsCommand;
    @InjectMocks
    private DeactivateMusiciansUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when musician is not found, then throws MusicianExistsException")
    void executeWhenMusicianIsNotFoundThenThrowsMusicianExistsException() {
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                MusicianExistsException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, never()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(authService, never()).getAuthenticatedPerson();
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(deleteAllBandMusicianAssociationsCommand, never()).execute(anyString());
    }

    @Test
    @DisplayName("execute - when musician is found but person has not the same cpf, " +
            "then throws MusicianHasAnAccountException")
    void executeWhenMusicianIsFoundButPersonHasNotTheSameCpfThenThrowsMusicianExistsException() {
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var musicPerson = PersonResponseMock.build();
        musicPerson.setCpf(MockConstants.STRING.repeat(2));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(musicPerson));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        Assertions.assertThrows(
                MusicianHasAnAccountException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(deleteAllBandMusicianAssociationsCommand, never()).execute(anyString());
    }

    @Test
    @DisplayName("execute - when musician is found and person has the same cpf, then deactivate it")
    void executeWhenMusicianIsFoundAndPersonHasTheSameCpfThenDeactivateIt() {
        var musicianFound = MusicianTableMock.build();
        musicianFound.setActive(true);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var musicPerson = PersonResponseMock.build();
        musicPerson.setCpf(MockConstants.STRING);
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(musicPerson));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING)
        );

        Assertions.assertFalse(musicianFound.getActive());

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
        verify(deleteAllBandMusicianAssociationsCommand, atMostOnce()).execute(anyString());
    }

    @Test
    @DisplayName("execute - when musician is found but person is not band owner, then throws BandOwnerException")
    void executeWhenMusicianIsFoundButPersonIsNotBandOwnerThenBandOwnerException() {
        var musicianFound = MusicianTableMock.build();
        musicianFound.setBandThatInserted(new BandTable());
        musicianFound.getBandThatInserted().setOwnerUuid(MockConstants.STRING.repeat(2));
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(authService, never()).getAuthenticatedPerson();
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(deleteAllBandMusicianAssociationsCommand, never()).execute(anyString());
    }

    @Test
    @DisplayName("execute - when musician is found but person is band owner, then deactivate it")
    void executeWhenMusicianIsFoundButPersonIsBandOwnerThenDeactivateIt() {
        var musicianFound = MusicianTableMock.build();
        musicianFound.setBandThatInserted(new BandTable());
        musicianFound.getBandThatInserted().setOwnerUuid(MockConstants.STRING);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING)
        );

        Assertions.assertFalse(musicianFound.getActive());

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(authService, never()).getAuthenticatedPerson();
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
        verify(deleteAllBandMusicianAssociationsCommand, atMostOnce()).execute(anyString());
    }

}
