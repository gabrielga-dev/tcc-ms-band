package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.MockConstants;
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
 * Tests for {@link ActivateMusiciansUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class ActivateMusiciansUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private FindPersonMusicianCommand findPersonMusicianCommand;
    @Mock
    private SaveMusicianCommand saveMusicianCommand;
    @InjectMocks
    private ActivateMusiciansUseCaseImpl useCase;

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
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person musician is found and user cpf is not equals to the musicians, " +
            "then throws MusicianHasAnAccountException")
    void executeWhenPersonMusicianIsFoundAndUserCpfIsNotEqualsToTheMusiciansThenThrowsMusicianHasAnAccountException() {
        var foundMusician = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(foundMusician));
        var personFound = PersonResponseMock.build();
        personFound.setCpf(MockConstants.STRING.repeat(2));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(personFound));
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
    @DisplayName("execute - when person musician is found and user cpf is equals to the musicians, " +
            "then throws MusicianHasAnAccountException")
    void executeWhenPersonMusicianIsFoundAndUserCpfIsEqualsToTheMusiciansThenActivateMusician() {
        var foundMusician = MusicianTableMock.build();
        foundMusician.setActive(false);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(foundMusician));
        var personFound = PersonResponseMock.build();
        personFound.setCpf(MockConstants.STRING);
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(personFound));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person musician is not found and user is not band owner, then " +
            "throws MusicianHasAnAccountException")
    void executeWhenPersonMusicianIsNotFoundAndUserIsNotBandOwnerThenThrowsMusicianHasAnAccountException() {
        var foundMusician = MusicianTableMock.build();
        foundMusician.setBandThatInserted(new BandTable());
        foundMusician.getBandThatInserted().setOwnerUuid(MockConstants.STRING.repeat(2));
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(foundMusician));

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
    @DisplayName("execute - when person musician is not found and user is band owner, then activate musician")
    void executeWhenPersonMusicianIsNotFoundAndUserIsBandOwnerThenActivateMusician() {
        var foundMusician = MusicianTableMock.build();
        foundMusician.setActive(false);
        foundMusician.setBandThatInserted(new BandTable());
        foundMusician.getBandThatInserted().setOwnerUuid(MockConstants.STRING);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(foundMusician));

        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());

        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }

}
