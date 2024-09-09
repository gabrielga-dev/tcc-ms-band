package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.address.CheckAddressCommand;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.band.SaveBandCommand;
import br.com.events.band.business.command.file.UploadFileCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandNotFoundException;
import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.band.request.UpdateBandRequestMock;
import br.com.events.band.data.io.file.FileDTO;
import br.com.events.band.data.io.file.FileType;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.band.BandTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ToggleBandActivityFlagUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class UpdateBandUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private SaveBandCommand saveBandCommand;
    @Mock
    private CheckAddressCommand checkAddressCommand;
    @Mock
    private UploadFileCommand uploadFileCommand;

    @InjectMocks
    private UpdateBandUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNotFoundException")
    void executeWhenBandIsNotFoundThenThrowsBandNotFoundException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findBandCommand.byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING))
                .thenReturn(Optional.empty());

        var request = UpdateBandRequestMock.build();
        Assertions.assertThrows(
                BandNotFoundException.class,
                () -> useCase.execute(
                        MockConstants.STRING,
                        request,
                        mock(MultipartFile.class)
                )
        );

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(anyString(), anyString());
        verify(checkAddressCommand, never()).execute(any(IAddress.class));
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(saveBandCommand, never()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        bandFound.setActive(Boolean.FALSE);
        when(findBandCommand.byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING))
                .thenReturn(Optional.of(bandFound));

        var request = UpdateBandRequestMock.build();
        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(
                        MockConstants.STRING,
                        request,
                        mock(MultipartFile.class)
                )
        );

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(anyString(), anyString());
        verify(checkAddressCommand, never()).execute(any(IAddress.class));
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(saveBandCommand, never()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when clean picture flag is on, then clear picture and save band")
    void executeWhenClearPictureFlagIsOnThenClearPictureAndSaveBand() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING))
                .thenReturn(Optional.of(bandFound));
        doNothing().when(checkAddressCommand).execute(any(IAddress.class));
        when(saveBandCommand.execute(any(BandTable.class))).thenReturn(bandFound);

        var request = UpdateBandRequestMock.build();
        request.setClearProfilePicture(true);
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(
                        MockConstants.STRING,
                        request,
                        mock(MultipartFile.class)
                )
        );
        Assertions.assertNull(bandFound.getProfilePictureUuid());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(anyString(), anyString());
        verify(checkAddressCommand, atMostOnce()).execute(any(IAddress.class));
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(saveBandCommand, atMostOnce()).execute(any(BandTable.class));
    }

    @Test
    @DisplayName("execute - when profile picture is not null, then change picture and save band")
    void executeWhenProfilePictureIsNotNullThenChangePictureAndSaveBand() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING))
                .thenReturn(Optional.of(bandFound));

        doNothing().when(checkAddressCommand).execute(any(IAddress.class));

        var fileDto = new FileDTO(MockConstants.STRING.repeat(2), MockConstants.STRING);
        when(uploadFileCommand.execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class)))
                .thenReturn(fileDto);

        when(saveBandCommand.execute(any(BandTable.class))).thenReturn(bandFound);

        var request = UpdateBandRequestMock.build();
        request.setClearProfilePicture(false);
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(
                        MockConstants.STRING,
                        request,
                        mock(MultipartFile.class)
                )
        );
        Assertions.assertEquals(fileDto.getUuid(), bandFound.getProfilePictureUuid());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(anyString(), anyString());
        verify(checkAddressCommand, atMostOnce()).execute(any(IAddress.class));
        verify(uploadFileCommand, atMostOnce())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(saveBandCommand, atMostOnce()).execute(any(BandTable.class));
    }

}
