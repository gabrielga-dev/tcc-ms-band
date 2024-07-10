package br.com.events.band.business.use_case.musician.impl;


import br.com.events.band.MockConstants;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
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
 * Tests for {@link RemoveMusicianAvatarUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class RemoveMusicianAvatarUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private FindPersonMusicianCommand findPersonMusicianCommand;
    @Mock
    private SaveMusicianCommand saveMusicianCommand;
    @InjectMocks
    private RemoveMusicianAvatarUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when musician is not found, then throws MusicianDoesNotExistException")
    void executeWhenMusicianIsNotFoundThenThrowsMusicianDoesNotExistException() {
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, never()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is not the musician, then throws MusicianHasAnAccountException")
    void executeWhenPersonIsNotTheMusicianThenThrowsMusicianHasAnAccountException() {
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var personMusician = PersonResponseMock.build();
        personMusician.setCpf(MockConstants.STRING.repeat(2));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(personMusician));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        Assertions.assertThrows(
                MusicianHasAnAccountException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is the musician, then remove profile picture")
    void executeWhenPersonIsTheMusicianThenRemoveProfilePicture() {
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var personMusician = PersonResponseMock.build();
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(personMusician));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());
        when(saveMusicianCommand.execute(any(MusicianTable.class))).thenReturn(new MusicianTable());

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING)
        );

        Assertions.assertNull(musicianFound.getProfilePictureUuid());

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is not the band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotTheBandOwnerThenThrowsBandOwnerException() {
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
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is the band owner, then remove profile picture")
    void executeWhenPersonIsTheBandOwnerThenRemoveProfilePicture() {
        var musicianFound = MusicianTableMock.build();
        musicianFound.setBandThatInserted(new BandTable());
        musicianFound.getBandThatInserted().setOwnerUuid(MockConstants.STRING);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING)
        );

        Assertions.assertNull(musicianFound.getProfilePictureUuid());

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }

}
