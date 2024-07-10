package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.file.UploadFileCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandNotFoundException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianBelowAgeException;
import br.com.events.band.core.exception.musician.MusicianExistsException;
import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.file.FileDTOMock;
import br.com.events.band.data.io.file.FileType;
import br.com.events.band.data.io.musician.request.MusicianRequestMock;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
import br.com.events.band.data.model.table.musician.MusicianTypeTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateMusicianUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CreateMusicianUseCaseImplTest {


    @Mock
    private AuthService authService;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private SaveMusicianCommand saveMusicianCommand;
    @Mock
    private FindMusicianTypeCommand findMusicianTypeCommand;
    @Mock
    private UploadFileCommand uploadFileCommand;
    @Mock
    private AssociateCreatedMusicianUseCaseImpl associateCreatedMusicianUseCaseImpl;

    @InjectMocks
    private CreateMusicianUseCaseImpl useCase;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(useCase, "minimumAge", 14);
    }

    @Test
    @DisplayName("execute - when band is not found, then throws BandNotFoundException")
    void executeWhenBandIsNotFoundThenThrowsBandNotFoundException() {
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.empty());

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                BandNotFoundException.class,
                () -> useCase.execute(mock(MultipartFile.class), request, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byCpf(anyString());
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(uploadFileCommand, never()).execute(
                anyString(), anyString(), any(FileType.class), any(MultipartFile.class)
        );
        verify(associateCreatedMusicianUseCaseImpl, never()).execute(anyString(), anyString());
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        var bandFound = BandTableMock.build();
        bandFound.setActive(false);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(mock(MultipartFile.class), request, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, never()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byCpf(anyString());
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(uploadFileCommand, never()).execute(
                anyString(), anyString(), any(FileType.class), any(MultipartFile.class)
        );
        verify(associateCreatedMusicianUseCaseImpl, never()).execute(anyString(), anyString());
    }

    @Test
    @DisplayName("execute - when person is not band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        var bandFound = BandTableMock.build();
        bandFound.setOwnerUuid(MockConstants.STRING.repeat(2));
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(mock(MultipartFile.class), request, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byCpf(anyString());
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(uploadFileCommand, never()).execute(
                anyString(), anyString(), any(FileType.class), any(MultipartFile.class)
        );
        verify(associateCreatedMusicianUseCaseImpl, never()).execute(anyString(), anyString());
    }

    @Test
    @DisplayName("execute - when musician cpf is found, then throws MusicianExistsException")
    void executeWhenMusicianCpfIsFoundThenThrowsMusicianExistsException() {
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.of(musicianFound));

        var request = MusicianRequestMock.build();
        Assertions.assertThrows(
                MusicianExistsException.class,
                () -> useCase.execute(mock(MultipartFile.class), request, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(uploadFileCommand, never()).execute(
                anyString(), anyString(), any(FileType.class), any(MultipartFile.class)
        );
        verify(associateCreatedMusicianUseCaseImpl, never()).execute(anyString(), anyString());
    }

    @Test
    @DisplayName("execute - when musician has not the minimum age, then throws MusicianBelowAgeException")
    void executeWhenMusicianDoesNotHaveMinimumAgeThenThrowsMusicianBelowAgeException() {
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());

        var request = MusicianRequestMock.build();
        request.setBirthday(DateUtil.localDateTimeToMilliseconds(LocalDateTime.now()));
        Assertions.assertThrows(
                MusicianBelowAgeException.class,
                () -> useCase.execute(mock(MultipartFile.class), request, MockConstants.STRING)
        );

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveMusicianCommand, never()).execute(any(MusicianTable.class));
        verify(uploadFileCommand, never()).execute(
                anyString(), anyString(), any(FileType.class), any(MultipartFile.class)
        );
        verify(associateCreatedMusicianUseCaseImpl, never()).execute(anyString(), anyString());
    }

    @Test
    @DisplayName("execute - when profile picture is null, then save musician")
    void executeWhenProfilePictureIsNullThenSaveMusician() {
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(findMusicianTypeCommand.byUuid(anyList())).thenReturn(List.of(MusicianTypeTableMock.build()));
        when(saveMusicianCommand.execute(any(MusicianTable.class))).thenAnswer(i -> i.getArguments()[0]);
        when(associateCreatedMusicianUseCaseImpl.execute(anyString(), anyString()))
                .thenReturn(new UuidHolderDTO(MockConstants.STRING));

        var request = MusicianRequestMock.build();
        request.setBirthday(DateUtil.localDateTimeToMilliseconds(LocalDateTime.now().minusYears(100L)));
        var returned = useCase.execute(null, request, MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getUuid());

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(findMusicianTypeCommand, atMostOnce()).byUuid(anyList());
        verify(saveMusicianCommand, atMostOnce()).execute(any(MusicianTable.class));
        verify(uploadFileCommand, never()).execute(
                anyString(), anyString(), any(FileType.class), any(MultipartFile.class)
        );
        verify(associateCreatedMusicianUseCaseImpl, atMostOnce()).execute(anyString(), anyString());
    }

    @Test
    @DisplayName("execute - when profile picture is not null, then save picture and musician")
    void executeWhenProfilePictureIsNotNullThenSavePictureAndMusician() {
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findMusicianCommand.byCpf(anyString())).thenReturn(Optional.empty());
        when(findMusicianTypeCommand.byUuid(anyList())).thenReturn(List.of(MusicianTypeTableMock.build()));
        when(saveMusicianCommand.execute(any(MusicianTable.class))).thenAnswer(i -> i.getArguments()[0]);
        when(uploadFileCommand.execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class)))
                .thenReturn(FileDTOMock.build());
        when(associateCreatedMusicianUseCaseImpl.execute(anyString(), anyString()))
                .thenReturn(new UuidHolderDTO(MockConstants.STRING));

        var request = MusicianRequestMock.build();
        request.setBirthday(DateUtil.localDateTimeToMilliseconds(LocalDateTime.now().minusYears(100L)));
        var returned = useCase.execute(mock(MultipartFile.class), request, MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getUuid());

        verify(findBandCommand, atMostOnce()).byUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byCpf(anyString());
        verify(findMusicianTypeCommand, atMostOnce()).byUuid(anyList());
        verify(saveMusicianCommand, times(2)).execute(any(MusicianTable.class));
        verify(uploadFileCommand, atMostOnce()).execute(
                anyString(), anyString(), any(FileType.class), any(MultipartFile.class)
        );
        verify(associateCreatedMusicianUseCaseImpl, atMostOnce()).execute(anyString(), anyString());
    }
}
