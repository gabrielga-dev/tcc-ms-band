package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.address.CheckAddressCommand;
import br.com.events.band.business.command.band.AssignBandToPersonCommand;
import br.com.events.band.business.command.band.SaveBandCommand;
import br.com.events.band.business.command.file.UploadFileCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.band.request.BandRequestMock;
import br.com.events.band.data.io.file.FileDTOMock;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateBandUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CreateBandUseCaseImplTest {

    @Mock
    private CheckAddressCommand checkAddressCommand;
    @Mock
    private SaveBandCommand saveBandCommand;
    @Mock
    private AssignBandToPersonCommand assignBandToPersonCommand;
    @Mock
    private UploadFileCommand uploadFileCommand;
    @Mock
    private AuthService authService;

    @InjectMocks
    private CreateBandUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when profile picture is null, then save band without profile picture")
    void executeWhenProfilePictureIsNullThenSaveBandWithoutProfilePicture() {
        doNothing().when(checkAddressCommand).execute(any(IAddress.class));
        var savedBand = BandTableMock.build();
        when(saveBandCommand.execute(any(BandTable.class))).thenReturn(savedBand);
        doNothing().when(assignBandToPersonCommand).execute(anyString());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var request = BandRequestMock.build();
        var returned = useCase.execute(request, null);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(savedBand.getUuid(), returned.getUuid());

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(checkAddressCommand, atMostOnce()).execute(any(IAddress.class));
        verify(saveBandCommand, atMostOnce()).execute(any(BandTable.class));
        verify(uploadFileCommand, never())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(assignBandToPersonCommand, atMostOnce()).execute(anyString());
    }

    @Test
    @DisplayName("execute - when profile picture is not null, then save band with profile picture")
    void executeWhenProfilePictureIsNotNullThenSaveBandWithProfilePicture() {
        doNothing().when(checkAddressCommand).execute(any(IAddress.class));
        var savedBand = BandTableMock.build();
        when(saveBandCommand.execute(any(BandTable.class))).thenReturn(savedBand);
        doNothing().when(assignBandToPersonCommand).execute(anyString());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(uploadFileCommand.execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class)))
                .thenReturn(FileDTOMock.build());

        var request = BandRequestMock.build();
        var profilePicture = mock(MultipartFile.class);
        var returned = useCase.execute(request, profilePicture);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(savedBand.getUuid(), returned.getUuid());

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(checkAddressCommand, atMostOnce()).execute(any(IAddress.class));
        verify(saveBandCommand, times(2)).execute(any(BandTable.class));
        verify(uploadFileCommand, atMostOnce())
                .execute(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
        verify(assignBandToPersonCommand, atMostOnce()).execute(anyString());
    }
}
