package br.com.events.band.business.use_case.musician.impl;


import br.com.events.band.MockConstants;
import br.com.events.band.business.command.file.UploadFileCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.ResourceAlreadyDeactivatedException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.data.io.auth.AuthenticatedPersonMock;
import br.com.events.band.data.io.file.FileDTOMock;
import br.com.events.band.data.io.file.FileType;
import br.com.events.band.data.io.musician.request.MusicianRequestMock;
import br.com.events.band.data.io.musician.request.UpdateMusicianRequestMessageMock;
import br.com.events.band.data.io.person.response.PersonResponseMock;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
import br.com.events.band.data.model.table.musician.MusicianTypeTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link UpdateMusicianUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class UpdateMusicianUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private FindPersonMusicianCommand findPersonMusicianCommand;
    @Mock
    private FindMusicianTypeCommand findMusicianTypeCommand;
    @Mock
    private UploadFileCommand uploadFileCommand;
    @Mock
    private SaveMusicianCommand saveMusicianCommand;
    @InjectMocks
    private UpdateMusicianUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when musician is not found, then throws MusicianDoesNotExistException")
    void executeWhenMusicianIsNotFoundThenThrowsMusicianDoesNotExistException() {
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.empty());

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(MockConstants.STRING, request, mock(MultipartFile.class))
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, never()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is not the musician, then throws MusicianHasAnAccountException")
    void executeWhenMusicianIsNotTheMusicianThenThrowsMusicianHasAnAccountException() {
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var personMusician = PersonResponseMock.build();
        personMusician.setCpf(MockConstants.STRING.repeat(2));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(personMusician));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                MusicianHasAnAccountException.class,
                () -> useCase.execute(MockConstants.STRING, request, mock(MultipartFile.class))
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is the musician and profile picture is null, then update musician")
    void executeWhenPersonIsTheMusicianAndProfilePictureIsNullThenUpdateMusician() {
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var personMusician = PersonResponseMock.build();
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(personMusician));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());
        when(findMusicianTypeCommand.byUuid(anyList())).thenReturn(List.of(MusicianTypeTableMock.build()));
        when(saveMusicianCommand.execute(any(MusicianTable.class))).thenReturn(new MusicianTable());

        var request = MusicianRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, request, null)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, atMostOnce()).byUuid(anyList());
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is the musician and profile picture is not null, then update musician")
    void executeWhenPersonIsTheMusicianAndProfilePictureIsNotNullThenUpdateMusician() {
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var personMusician = PersonResponseMock.build();
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(personMusician));
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());
        when(uploadFileCommand.execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class)))
                .thenReturn(FileDTOMock.build());
        when(findMusicianTypeCommand.byUuid(anyList())).thenReturn(List.of(MusicianTypeTableMock.build()));
        when(saveMusicianCommand.execute(any(MusicianTable.class))).thenReturn(new MusicianTable());

        var request = MusicianRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, request, mock(MultipartFile.class))
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPerson();
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, atMostOnce())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, atMostOnce()).byUuid(anyList());
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

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, request, null)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when musician is not active, then throws ResourceAlreadyDeactivatedException")
    void executeWhenMusicianIsNotActiveThenThrowsResourceAlreadyDeactivatedException() {
        var musicianFound = MusicianTableMock.build();
        musicianFound.setBandThatInserted(new BandTable());
        musicianFound.getBandThatInserted().setOwnerUuid(MockConstants.STRING);
        musicianFound.setActive(false);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                ResourceAlreadyDeactivatedException.class,
                () -> useCase.execute(MockConstants.STRING, request, null)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is the band owner and profile picture is null, then update musician")
    void executeWhenPersonIsTheBandOwnerAndProfilePictureIsNullThenUpdateMusician() {
        var musicianFound = MusicianTableMock.build();
        musicianFound.setBandThatInserted(new BandTable());
        musicianFound.getBandThatInserted().setOwnerUuid(MockConstants.STRING);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var request = MusicianRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, request, null)
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, atMostOnce()).byUuid(anyList());
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute - when person is the band owner and profile picture is not null, then update musician")
    void executeWhenPersonIsTheBandOwnerAndProfilePictureIsNotNullThenUpdateMusician() {
        var musicianFound = MusicianTableMock.build();
        musicianFound.setBandThatInserted(new BandTable());
        musicianFound.getBandThatInserted().setOwnerUuid(MockConstants.STRING);
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        when(findPersonMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(uploadFileCommand.execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class)))
                .thenReturn(FileDTOMock.build());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var request = MusicianRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, request, mock(MultipartFile.class))
        );

        verify(findMusicianCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(findPersonMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(authService, never()).getAuthenticatedPerson();
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(uploadFileCommand, atMostOnce())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(findMusicianTypeCommand, atMostOnce()).byUuid(anyList());
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute (from queue) - when musician is not found, then throws MusicianDoesNotExistException")
    void executeFromQueueMusicianIsNotFoundThenThrowsMusicianDoesNotExistException() {
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());

        var request = UpdateMusicianRequestMessageMock.build();
        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(request)
        );

        verify(findMusicianCommand, atMostOnce()).byCpf(eq(MockConstants.STRING));
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
    }

    @Test
    @DisplayName("execute (from queue) - when musician is found, then update it")
    void executeFromQueueMusicianIsFoundThenUpdateIt() {
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(MusicianTableMock.build()));

        var request = UpdateMusicianRequestMessageMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(request)
        );

        verify(findMusicianCommand, atMostOnce()).byCpf(eq(MockConstants.STRING));
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
    }
}
